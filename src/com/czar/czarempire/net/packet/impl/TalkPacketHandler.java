package com.czar.czarempire.net.packet.impl;

import com.czar.czarempire.model.player.Player;
import com.czar.czarempire.util.Text;

public class TalkPacketHandler implements Packet {

	@Override
	public void append(Player player, int id, int len) {
		if (player == null) {
			return;
		}
		if (player.getInformation().getMuteDuration() > 0 || player.getInformation().isPermanentlyMuted()) {
			if (player.getInformation().isPermanentlyMuted()) {
				player.getActionSender().msg("You have been permanently muted due to breaking a rule.");
			} else {
				player.getActionSender().msg("You have been temporarily muted due to breaking a rule.");
				player.getActionSender().msg("This mute will remain for a further "
						+ player.getInformation().getMuteDuration() + " hours.");
				player.getActionSender().msg("To prevent further mutes please read the rules.");
			}
			return;
		}
		player.getInformation().setChatEffects(player.getStreamBuffer().readUnsignedWord());
		player.getInformation().setChat(
				Text.decryptPlayerChat(player.getStreamBuffer(), player.getStreamBuffer().readUnsignedByte()));
		player.setChatUpdateRequired(true);
		player.setUpdateRequired(true);
	}

}
