package com.czar.czarempire.net.packet.impl;

import com.czar.czarempire.model.player.Player;

public class WalkPacketHandler implements Packet {

	@Override
	public void append(Player player, int id, int len) {
		if (player == null) {
			return;
		}
		if (id == 11) {
			len -= 14;
		}
		player.getMovement().reset();
		int path = (len - 5) / 2;
		int[] x = new int[path];
		int[] y = new int[path];
		for (int i = 0; i < path; i++) {
			x[i] = player.getStreamBuffer().readSignedByte();
			y[i] = player.getStreamBuffer().readSignedByteS();
		}
		int firstX = player.getStreamBuffer().readUnsignedWordA()
				- (player.getRegionX() - 6) * 8;
		int firstY = player.getStreamBuffer().readUnsignedWordBigEndian()
				- (player.getRegionY() - 6) * 8;
		boolean run = player.getStreamBuffer().readUnsignedByte() == 1 ? true
				: false;
		player.getMovement().setRunning(run);
		player.getMovement().addToWalkingQueue(player, firstX, firstY);
		for (int i = 0; i < path; i++) {
			x[i] += firstX;
			y[i] += firstY;
			player.getMovement().addToWalkingQueue(player, x[i], y[i]);
		}
	}

}
