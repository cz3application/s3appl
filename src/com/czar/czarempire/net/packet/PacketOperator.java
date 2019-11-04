package com.czar.czarempire.net.packet;

import java.util.HashMap;

import com.czar.czarempire.Constants;
import com.czar.czarempire.model.World;
import com.czar.czarempire.model.player.Player;
import com.czar.czarempire.net.packet.impl.*;

public class PacketOperator {
	private HashMap<Integer, Packet> packets;

	public PacketOperator() {
		packets = new HashMap<Integer, Packet>();

		WalkPacketHandler walk = new WalkPacketHandler();
		packets.put(11, walk);
		packets.put(46, walk);
		packets.put(59, walk);

		CommandPacketHandler command = new CommandPacketHandler();
		packets.put(202, command);

		TalkPacketHandler talk = new TalkPacketHandler();
		packets.put(52, talk);

		WearItemPacketHandler wear = new WearItemPacketHandler();
		packets.put(124, wear);
	}

	public void parse() {
		int available = 0;
		int id = 0;
		int len = 0;
		for (Player player : World.players) {
			if (player == null || !player.isLoggedIn() || player.disconnected[0]) {
				// ignore
				continue;
			}
			try {
				for (int i = 0; i < 50; i++) {
					available = player.getSocket().getInputStream().available();
					if (available < 1) {
						// no bytes
						break;
					}
					id = player.getSocket().getInputStream().read() & 0xff;
					available--;
					if (id < 0 || id > 255) {
						// malformed id
						System.err.println("Malformed packet id: " + id + "!");
						break;
					}
					len = Constants.PACKET_LEN[id];
					if (len == -1) {
						if (available > 0) {
							len = player.getSocket().getInputStream().read() & 0xff;
							available--;
						} else {
							break;
						}
					} else if (len == -2) {
						len = player.getStreamBuffer().read2Bytes();
					} else if (len == -3) {
						// guess length
						len = available;
					}
					if (len > available) {
						break;
					}
					if (len >= 500) {
						break;
					}
					player.getStreamBuffer().inOffset = 0;
					player.getSocket().getInputStream().read(player.getStreamBuffer().inBuffer, 0, len);
					Packet packet = packets.get(id);
					if (packet != null) {
						packet.append(player, id, len);
					}
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

}
