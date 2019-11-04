package com.czar.czarempire.model.npc.update;

import java.util.HashMap;

import com.czar.czarempire.model.EntityUpdateMask;
import com.czar.czarempire.model.World;
import com.czar.czarempire.model.npc.Npc;
import com.czar.czarempire.model.player.Player;
import com.czar.czarempire.net.Stream;

public class NpcUpdate {
	private Npc npc;
	private HashMap<Integer, EntityUpdateMask> updateMasks;
	private Stream streamBuffer = new Stream(5000, 5000);

	public NpcUpdate(Npc npc) {
		this.npc = npc;
		updateMasks = new HashMap<Integer, EntityUpdateMask>();
	}

	public void updateNpc(Player player, Stream str) {
		if (player == null || str == null) {
			return;
		}
		getStreamBuffer().outOffset = 0;
		byte[] newNpcIds = new byte[World.npcs.length];
		str.createFrameVarSizeWord(146);
		str.initBitAccess();
		str.writeBits(8, player.npcListSize);
		int size = player.npcListSize;
		player.npcListSize = 0;
		for (int i = 0; i < size; i++) {
			if (player.npcList[i] != null) {
				if (player.npcList[i].withinDistance(player, player.npcList[i])
						&& !player.rebuildNpcList) {
					player.npcList[i].updateMovement(str);
					player.npcList[i].getUpdate().appendUpdateMasks(
							getStreamBuffer());
					player.npcList[player.npcListSize++] = player.npcList[i];
				} else {
					player.npcsInList[player.npcList[i].getIndex()] = 0;
					str.writeBits(1, 1);
					str.writeBits(2, 3);
				}
			}
		}
		for (Npc n : World.npcs) {
			if (n == null || !player.withinDistance(player, n)
					|| player.npcsInList[n.getIndex()] == 1) {
				continue;
			}
			newNpcIds[n.getIndex()] = 1;
			addNpc(player, n, str);
		}
		player.rebuildNpcList = false;
		if (getStreamBuffer().outOffset >= 3) {
			str.writeBits(15, 32767);
		}
		str.finishBitAccess();
		if (getStreamBuffer().outOffset > 0) {
			str.writeBytes(getStreamBuffer().outBuffer,
					getStreamBuffer().outOffset, 0);
		}
		str.endFrameVarSizeWord();
	}

	public void addNpc(Player player, Npc n, Stream stream) {

		// player.npcsInList[n.getIndex()] = 1;
		// player.npcList[player.npcListSize++] = n;
		stream.writeBits(15, n.getIndex());

		int y = n.getAbs().getY() - player.getAbs().getY();
		int x = n.getAbs().getX() - player.getAbs().getX();

		stream.writeBits(5, x);
		stream.writeBits(14, n.getClientIndex());
		stream.writeBits(5, y);
		stream.writeBits(1, n.isUpdateRequired() ? 1 : 0);
		stream.writeBits(3, 0);
		stream.writeBits(1, 1);
		// if (y < 0) {
		// y += 32;
		// }
		// if (x < 0) {
		// x += 32;
		// }
		// appendUpdateMasks(getStreamBuffer());
	}

	private void appendUpdateMasks(Stream str) {
		if (str == null) {
			return;
		}
		if (!npc.isUpdateRequired()) {
			return;
		}
		int maskData = 0;
		str.writeByte(maskData);
	}

	public void setStreamBuffer(Stream streamBuffer) {
		this.streamBuffer = streamBuffer;
	}

	public Stream getStreamBuffer() {
		return streamBuffer;
	}

}
