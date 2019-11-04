package com.czar.czarempire.net.packet;

import com.czar.czarempire.Constants;
import com.czar.czarempire.model.World;
import com.czar.czarempire.model.player.Player;
import com.czar.czarempire.util.Rank;
import com.czar.czarempire.util.Text;

public class LoginHandler {

	// login, duh!
	public void login(Player player) {
		if (player == null) {
			System.err.println("Player == null");
			return;
		}
		if (player.getStreamBuffer() == null) {
			System.err.println("getStreamBuffer == null");
			return;
		}
		int returnCode = 2;
		if (player.getLoginStage() < -1) {
			updateServer(player);
		} else if (player.getLoginStage() == 0) {
			try {
				if (!player.getActionSender().skip(player, 2)) {
					return;
				}
			} catch (final Exception e) {
				return;
			}
			final int connectionType = player.getStreamBuffer().readUnsignedByte();
			if (connectionType == 15) {
				updateServer(player);
				player.setLoginStage(-5);
				return;
			}
			if (connectionType != 14) {
				player.setLoginStage(-1);
				return;
			}
			player.getStreamBuffer().readUnsignedByte();
			player.getStreamBuffer().writeByte(0);
			player.getStreamBuffer().writeQWord(0);
			player.getActionSender().flush(player);
			player.setLoginStage(player.getLoginStage() + 1);
		} else if (player.getLoginStage() == 1) {
			try {
				if (!player.getActionSender().skip(player, 2)) {
					return;
				}
			} catch (final Exception e) {
				return;
			}
			final int loginType = player.getStreamBuffer().readUnsignedByte();
			if (loginType != 16 && loginType != 18 && loginType != 14) {
				player.setLoginStage(-1);
				return;
			}
			player.setLoginStage(player.getLoginStage() + 1);
		} else if (player.getLoginStage() == 2) {
			final int loginPacketSize = player.getStreamBuffer().readUnsignedByte();
			final int loginEncryptPacketSize = loginPacketSize - (36 + 1 + 1 + 2);
			if (loginEncryptPacketSize <= 0) {
				player.setLoginStage(-1);
				return;
			}
			try {
				if (!player.getActionSender().skip(player, loginPacketSize)) {
					return;
				}
			} catch (final Exception e) {
				return;
			}
			final int clientVersion = player.getStreamBuffer().readDWord();
			if (clientVersion != 474) {
				player.setLoginStage(-1);
				return;
			}
			player.getStreamBuffer().readUnsignedByte();
			for (int i = 0; i < 24; i++) {
				player.getStreamBuffer().readUnsignedByte();
			}
			for (int i = 0; i < 16; i++) {
				player.getStreamBuffer().readDWord();
			}
			final int encryption = player.getStreamBuffer().readUnsignedByte();
			if (encryption != 10) {
				player.setLoginStage(-1);
				return;
			}
			player.getStreamBuffer().readQWord();
			player.getStreamBuffer().readQWord();
			player.setName(Text.longToString(player.getStreamBuffer().readQWord()).toLowerCase()
					.replaceAll("_", " ").trim());
			System.out.println();
			System.out.print("Connecting player: " + player.getName() + ":");
			if (player.getName() == null) {
				System.err.println("Player name is null!");
				player.setLoginStage(-1);
				player.setName(null);
				return;
			}
			for (int i = 0; i < player.getName().length(); i++) {
				final Character c = new Character(player.getName().charAt(i));
				if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c)) {
					player.setLoginStage(-1);
					player.setName(null);
					return;
				}
			}
			// CHECK IF PLAYER IS ONLINE!! :: TODO ::
			// THEN CHECK IF BANNED!! :: TODO ::
			String password = player.getStreamBuffer().readString();
			System.out.print(password + "...");
			if (password == null) {
				player.setLoginStage(-1);
				return;
			}
			for (int i = 0; i < password.length(); i++) {
				final Character c = new Character(password.charAt(i));
				if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c)) {
					player.setLoginStage(-1);
					return;
				}
			}
			World.savegame.loadCharacter(player);
			if (password != null && player.getPassword() != null && player.getPassword() != ""
					&& !player.getPassword().equalsIgnoreCase(password)) {
				returnCode = 3;
			} else {
				player.setPassword(password);
			}
			for (int i = 0; i < Constants.ADMINISTRATOR.length; i++) {
				if (player.getName().equalsIgnoreCase(Constants.ADMINISTRATOR[i])) {
					player.getRank().set(Rank.ADMINISTRATOR);
				}
			}
			for (int i = 0; i < Constants.MODERATOR.length; i++) {
				if (player.getName().equalsIgnoreCase(Constants.MODERATOR[i])) {
					player.getRank().set(Rank.MODERATOR);
				}
			}
			player.getActionSender().register(returnCode);
		}
	}

	private void updateServer(Player player) {
		if (player == null) {
			return;
		}
		try {
			if (player.getLoginStage() == 0) {
				if (!player.getActionSender().skip(player, 3)) {
					return;
				}
				player.getStreamBuffer().writeByte(0);
				player.getActionSender().flush(player);
			} else if (player.getLoginStage() == -5) {
				if (!player.getActionSender().skip(player, 8)) {
					return;
				}
				for (final int uKey : Constants.CRC32_TABLE) {
					player.getStreamBuffer().writeByte(uKey);
				}
				player.getActionSender().flush(player);
				player.setLoginStage(-1);
			}
		} catch (final Exception exception) {
		}
	}

}
