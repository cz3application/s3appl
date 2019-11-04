package com.czar.czarempire.net;

import java.io.IOException;

import com.czar.czarempire.Constants;
import com.czar.czarempire.model.player.Player;

public class ActionSender {
	private Player player;

	public ActionSender(Player player) {
		this.player = player;
	}

	public void sendRegion() {
		if (player.getStreamBuffer() == null || player.disconnected[0]) {
			return;
		}
		player.getStreamBuffer().createFrameVarSizeWord(61);
		player.getStreamBuffer().writeWordBigEndianA(player.getLocalY());
		player.getStreamBuffer().writeByte(player.getAbs().getZ());
		player.getStreamBuffer().writeWord(player.getRegionX());
		boolean bool_33_ = false;
		if ((((player.getRegionX() / 8) == 48) || ((player.getRegionX() / 8) == 49))
				&& ((player.getRegionY() / 8) == 48)) {
			bool_33_ = true;
		}
		if (((player.getRegionX() / 8) == 48) && ((player.getRegionY() / 8) == 148)) {
			bool_33_ = true;
		}
		// int i_28_ = 0;
		for (int i_34_ = (player.getRegionX() - 6) / 8; i_34_ <= ((player.getRegionX() + 6) / 8); i_34_++) {
			for (int i = (player.getRegionY() - 6) / 8; i <= ((player.getRegionY() + 6) / 8); i++) {
				// // final int i_36_ = i_35_ + (i_34_ * 256);
				// if (!bool_33_
				// || ((i_35_ != 49) && (i_35_ != 149) && (i_35_ != 147)
				// && (i_34_ != 50) && ((i_34_ != 49) || (i_35_ !=
				// 47)))) {
				// // final int[] mapData =
				// World.mapData.getData(i_36_); TODO
				int[] mapData = new int[4];
				player.getStreamBuffer().writeDWordBigEndian(mapData[0]);
				player.getStreamBuffer().writeDWordBigEndian(mapData[1]);
				player.getStreamBuffer().writeDWordBigEndian(mapData[2]);
				player.getStreamBuffer().writeDWordBigEndian(mapData[3]);
				// i_28_++;
				// }
			}
		}
		player.getStreamBuffer().writeWordA(player.getLocalX());
		player.getStreamBuffer().writeWord(player.getRegionY());
		player.getStreamBuffer().endFrameVarSizeWord();

	}

	public void sendPane(int pane) {
		if (player == null || player.getStreamBuffer() == null || player.disconnected[0]) {
			return;
		}
		player.getStreamBuffer().createFrame(251);
		player.getStreamBuffer().writeWordBigEndian(pane);
	}

	public void msg(String element) {
		if (player == null || player.getStreamBuffer() == null || player.disconnected[0]) {
			return;
		}
		player.getStreamBuffer().createFrame(209);
		player.getStreamBuffer().writeByte(element.length() + 1);
		player.getStreamBuffer().writeString(element);
	}

	public void teleport() {
		player.getStreamBuffer().createFrameVarSizeWord(150);
		player.getStreamBuffer().initBitAccess();
		player.getStreamBuffer().writeBits(1, 1);
		player.getStreamBuffer().writeBits(2, 3);
		player.getStreamBuffer().writeBits(1, (player.isUpdateRequired()) ? 1 : 0);
		player.getStreamBuffer().writeBits(1, player.didTeleport() ? 1 : 0);
		player.getStreamBuffer().writeBits(7, player.getLocalX());
		player.getStreamBuffer().writeBits(2, player.getAbs().getZ());
		player.getStreamBuffer().writeBits(7, player.getLocalY());
	}

	public void noMovement() {
		player.getStreamBuffer().createFrameVarSizeWord(150);
		player.getStreamBuffer().initBitAccess();
		if (player.isUpdateRequired()) {
			player.getStreamBuffer().writeBits(1, 1);
			player.getStreamBuffer().writeBits(2, 0);
		} else {
			player.getStreamBuffer().writeBits(1, 0);
		}
	}

	public void updateMovement() {
		player.getStreamBuffer().createFrameVarSizeWord(150);
		player.getStreamBuffer().initBitAccess();
		player.getStreamBuffer().writeBits(1, 1);
		if (player.getRunDirection() == -1) {
			player.getStreamBuffer().writeBits(2, 1);
			player.getStreamBuffer().writeBits(3, player.getWalkDirection());
			if (player.isUpdateRequired()) {
				player.getStreamBuffer().writeBits(1, 1);
			} else {
				player.getStreamBuffer().writeBits(1, 0);
			}
		} else {
			player.getStreamBuffer().writeBits(2, 2);
			player.getStreamBuffer().writeBits(3, player.getWalkDirection());
			player.getStreamBuffer().writeBits(3, player.getRunDirection());
			if (player.isUpdateRequired()) {
				player.getStreamBuffer().writeBits(1, 1);
			} else {
				player.getStreamBuffer().writeBits(1, 0);
			}
		}
	}

	public void sendInterface(int interfaceId, int childId, int windowId, boolean overlay) {
		if (player == null || player.getStreamBuffer() == null || player.disconnected[0]) {
			return;
		}
		player.getStreamBuffer().createFrame(17);
		player.getStreamBuffer().writeByteA(overlay ? 1 : 0);
		player.getStreamBuffer().writeWordBigEndian(interfaceId);
		player.getStreamBuffer().writeWord(childId);
		player.getStreamBuffer().writeWord(windowId);
	}

	public void setConfig(int itemID, int value) {
		player.getStreamBuffer().createFrame(253);
		player.getStreamBuffer().writeWord(itemID);
		player.getStreamBuffer().writeByteA(value);
	}

	public void sendTab(int interfaceId, int childId) {
		sendInterface(interfaceId, childId, 548, true);
	}

	public void sendLogin() {
		player.handleMovement();
		sendRegion();
		sendPane(548);
		sendTabs();
		player.getInventory().refresh();
		System.out.print("success!");
		System.out.println();

		switch (player.getRank().get()) {
		case 0:
			/*
			 * Regular player
			 */
			for (int i = 0; i < Constants.LOGIN_MESSAGE.length; i++) {
				msg(Constants.LOGIN_MESSAGE[i]);
			}
			break;
		case 1:
			/*
			 * Moderator
			 */
			for (int i = 0; i < Constants.LOGIN_MESSAGE.length; i++) {
				msg(Constants.LOGIN_MESSAGE[i]);
			}
			for (int i = 0; i < Constants.LOGIN_MESSAGE_MODERATOR.length; i++) {
				msg(Constants.LOGIN_MESSAGE_MODERATOR[i]);
			}
			break;
		case 2:
			/*
			 * Administrator
			 */
			for (int i = 0; i < Constants.LOGIN_MESSAGE.length; i++) {
				msg(Constants.LOGIN_MESSAGE[i]);
			}
			for (int i = 0; i < Constants.LOGIN_MESSAGE_ADMINISTRATOR.length; i++) {
				msg(Constants.LOGIN_MESSAGE_ADMINISTRATOR[i]);
			}
			break;
		}

		player.getSkills().refresh();
		player.getEquipment().refresh();
	}

	public void sendTabs() {
		sendTab(192, Constants.TAB_SPELLBOOK);
		sendTab(137, Constants.TAB_CHAT);

		sendTab(320, Constants.TAB_STATS);
		sendTab(274, Constants.TAB_QUEST);
		sendTab(149, Constants.TAB_INVENTORY);
		sendTab(387, Constants.TAB_EQUIPMENT);
		sendTab(271, Constants.TAB_PRAYER);

		sendTab(589, Constants.TAB_CLANCHAT);
		sendTab(550, Constants.TAB_FRIENDS);
		sendTab(551, Constants.TAB_IGNORES);
		sendTab(182, Constants.TAB_LOGOUT);
		sendTab(261, Constants.TAB_SETTINGS);
		sendTab(464, Constants.TAB_EMOTES);
		sendTab(239, Constants.TAB_MUSIC);
		player.getEquipment().sendAttackTab();
	}

	public void sendItem(int interfaceId, int childId, int length, int[] items, int[] itemsN) {
		if (player == null || player.getStreamBuffer() == null) {
			return;
		}
		player.getStreamBuffer().createFrameVarSizeWord(119);
		player.getStreamBuffer().writeDWord(interfaceId << 16 | childId);
		player.getStreamBuffer().writeWord(length);
		player.getStreamBuffer().writeWord(items.length);
		for (int i = 0; i < items.length; i++) {
			if (itemsN[i] > 254) {
				player.getStreamBuffer().writeByteC(255);
				player.getStreamBuffer().writeDWordBigEndian(itemsN[i]);
			} else {
				player.getStreamBuffer().writeByteC(itemsN[i]);
			}
			player.getStreamBuffer().writeWordBigEndianA(items[i] + 1);
		}
		player.getStreamBuffer().endFrameVarSizeWord();
	}

	public void register(int returnCode) {
		player.getStreamBuffer().writeByte(2);
		if (returnCode != 2) {
			flush(player);
			return;
		}
		player.getStreamBuffer().writeByte(2);
		player.getStreamBuffer().writeByte(0);
		player.getStreamBuffer().writeByte(0);
		player.getStreamBuffer().writeByte(player.getIndex());
		player.getStreamBuffer().writeByte(0);
		if (player.getTeleportTo().getX() < 1 && player.getTeleportTo().getY() < 1) {
			player.setTeleportTo(Constants.LUMBRIDGE);
		}
		player.getActionSender().sendLogin();
		flush(player);
		player.setLoggedIn(true);
		player.setAppearanceUpdateRequired(true);
		player.setUpdateRequired(true);
	}

	public void flush(Player player) {
		if (player == null) {
			return;
		}
		try {
			player.getSocket().getOutputStream().write(player.getStreamBuffer().outBuffer, 0,
					player.getStreamBuffer().outOffset);
			player.getStreamBuffer().outOffset = 0;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public boolean skip(Player player, int skip) throws IOException {
		if (player == null) {
			return false;
		}
		if (skip >= 500) {
			return false;
		}
		if (player.getSocket().getInputStream().available() < skip) {
			return false;
		}
		player.getStreamBuffer().inOffset = 0;
		player.getSocket().getInputStream().read(player.getStreamBuffer().inBuffer, 0, skip);
		return true;
	}

	public void sendString(String text, int interfaceId, int childId) {
		if (player == null) {
			return;
		}
		if (player.getStreamBuffer() == null || player.disconnected[0]) {
			return;
		}
		player.getStreamBuffer().createFrameVarSizeWord(231);
		player.getStreamBuffer().writeDWord_v2(interfaceId << 16 | childId);
		player.getStreamBuffer().writeString(text);
		player.getStreamBuffer().endFrameVarSizeWord();
	}

	public void sendSkill(int lvlId) {
		if (player == null || player.getStreamBuffer() == null || player.disconnected[0]) {
			return;
		}
		player.getStreamBuffer().createFrame(196);
		player.getStreamBuffer().writeByte(lvlId);
		player.getStreamBuffer().writeDWord_v2(player.getSkills().getXp(lvlId));
		player.getStreamBuffer().writeByte(player.getSkills().getLevel(lvlId));
	}

}
