package com.czar.czarempire.model.player.update;

import java.util.HashMap;

import com.czar.czarempire.model.EntityUpdateMask;
import com.czar.czarempire.model.World;
import com.czar.czarempire.model.player.Player;
import com.czar.czarempire.model.player.update.impl.*;
import com.czar.czarempire.net.Stream;

public class PlayerUpdate {

	private HashMap<Integer, EntityUpdateMask> updateMasks;
	public AppearanceMask appearanceMask;
	public ChatMask chatMask;

	public PlayerUpdate() {
		updateMasks = new HashMap<Integer, EntityUpdateMask>();
		/*
		 * Appearance update mask 0x10
		 */
		appearanceMask = new AppearanceMask();
		updateMasks.put(0x10, appearanceMask);
		/*
		 * Chat update mask 0x80
		 */
		chatMask = new ChatMask();
		updateMasks.put(0x80, chatMask);
	}

	public void updatePlayer(Player p, Stream str) {
		if (p == null || str == null) {
			return;
		}
		final byte[] newPlayerIds = new byte[World.players.length];
		p.getMovement().updateThisPlayerMovement(p, str);
		str.writeBits(8, p.playerListSize);
		final int size = p.playerListSize;
		p.playerListSize = 0;
		for (int i = 0; i < size; i++) {
			if (p.playerList[i] == null || !withinDistance(p, p.playerList[i]) || p.playerList[i].didTeleport()) {
				if (p.playerList[i] != null) {
					p.playersInList[p.playerList[i].getIndex()] = 0;
				}
				str.writeBits(1, 1);
				str.writeBits(2, 3);
			} else {
				p.playerList[i].getMovement().updatePlayerMovement(str);
				p.playerList[p.playerListSize++] = p.playerList[i];
			}
		}
		for (final Player p2 : World.players) {
			if (p2 == null || p2.getIndex() == p.getIndex() || !p2.isLoggedIn()) {
				continue;
			}
			if (p.playersInList[p2.getIndex()] == 1 || !withinDistance(p, p2)) {
				continue;
			}
			newPlayerIds[p.playerListSize] = 1;
			addNewPlayer(p, p2, str);
		}
		str.writeBits(11, 2047);
		str.finishBitAccess();
		appendPlayerUpdateMasks(p, str);
		for (int i = 0; i < p.playerListSize; i++) {
			final Player p2 = p.playerList[i];
			if (newPlayerIds[i] == 1) {
				final boolean appearanceFlag = p2.isAppearanceUpdateRequired();
				final boolean updateFlag = p2.isUpdateRequired();
				p2.setAppearanceUpdateRequired(true);
				p2.setUpdateRequired(true);
				appendPlayerUpdateMasks(p2, str);
				p2.setAppearanceUpdateRequired(appearanceFlag);
				p2.setUpdateRequired(updateFlag);
			} else {
				appendPlayerUpdateMasks(p.playerList[i], str);
			}
		}
		str.endFrameVarSizeWord();
	}

	private void appendPlayerUpdateMasks(Player p, Stream str) {
		if (p == null || str == null) {
			return;
		}
		if (!p.isUpdateRequired()) {
			return;
		}
		int maskData = 0;
		if (p.isAppearanceUpdateRequired()) {
			maskData |= 0x10;
		}
		if (p.isChatUpdateRequired()) {
			maskData |= 0x80;
		}
		writeMask(str, maskData);
		EntityUpdateMask mask = updateMasks.get(maskData);
		if (p.isAppearanceUpdateRequired()) {
			mask.appendMask(p, str);
		}
		if (p.isChatUpdateRequired()) {
			mask.appendMask(p, str);
		}
	}

	private void writeMask(Stream str, int maskData) {
		if (str == null) {
			return;
		}
		if (maskData >= 0x100) {
			maskData |= 0x20;
			str.writeByte(maskData & 0xFF);
			str.writeByte(maskData >> 8);
		} else {
			str.writeByte(maskData);
		}
	}

	/**
	 * Checks if a player is within distance of another player.
	 * 
	 * @param p
	 *                       The Player to update for.
	 * @param otherPlr
	 *                       The player to see if is within distance to.
	 */
	private boolean withinDistance(Player p, Player otherPlr) {
		if (otherPlr == null || p == null) {
			return false;
		}
		if (p.getAbs().getZ() != otherPlr.getAbs().getZ()) {
			return false;
		}
		final int deltaX = otherPlr.getAbs().getX() - p.getAbs().getX(),
				deltaY = otherPlr.getAbs().getY() - p.getAbs().getY();
		return (deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16);
	}

	/**
	 * Tell the client to add a new player.
	 * 
	 * @param p
	 *                  The Player to update for.
	 * @param p2
	 *                  The player to add.
	 * @param str
	 *                  The stream to write the bytes to.
	 */
	public void addNewPlayer(Player p, Player p2, Stream str) {
		if (p == null || p2 == null || str == null) {
			return;
		}
		p.playersInList[p2.getIndex()] = 1;
		p.playerList[p.playerListSize++] = p2;
		str.writeBits(11, p2.getIndex());
		str.writeBits(1, 1);
		str.writeBits(3, 0);
		int z = p2.getAbs().getX() - p.getAbs().getX();
		if (z < 0) {
			z += 32;
		}
		str.writeBits(5, z);
		str.writeBits(1, 1);
		z = p2.getAbs().getY() - p.getAbs().getY();
		if (z < 0) {
			z += 32;
		}
		str.writeBits(5, z);
	}

}
