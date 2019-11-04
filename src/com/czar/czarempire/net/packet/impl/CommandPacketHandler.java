package com.czar.czarempire.net.packet.impl;

import com.czar.czarempire.Constants;
import com.czar.czarempire.model.Coordinate;
import com.czar.czarempire.model.World;
import com.czar.czarempire.model.player.Player;

public class CommandPacketHandler implements Packet {

	public void dump(Player player) {
		/*
		 * Used primarily for dumping all configs in a procedure, but removed.
		 */
		player.getActionSender().setConfig(496, 2);
		boolean dumpConfigProcedure = false;
		/*
		 * TODO Add a way to narrow down the dump, so filter only configs 400-500
		 * instead of starting from 0.
		 */
		if (dumpConfigProcedure) {
			// player.getActionSender().setConfig(i, 0);
			// player.getActionSender().setConfig(i, 0);
			// player.getActionSender().setConfig(i, 0);
			for (int i = 495; i < 499; i++) {
				player.getActionSender().msg("Trying config " + i + ":");
				player.getActionSender().setConfig(i, 0);
				// try {
				// wait(1000);
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// dump(player);
			}
		}
	}

	@Override
	public void append(Player player, int id, int len) {
		if (player == null) {
			return;
		}
		String command = player.getStreamBuffer().readString();
		String[] commandSplit = command.split(" ");

		if (command.startsWith("item")) {
			if (player.getRank().get() >= Constants.COMMAND_ITEM_RANK.get()) {
				int itemId = Integer.parseInt(commandSplit[1]);
				int itemAmount = 1;
				if (itemId > Constants.ITEM_CAP) {
					player.getActionSender().msg("Invalid item index!");
					player.getActionSender().msg(
							"Syntax: ::item [index] [amount] or just ::item [index] if one item only.");
					return;
				}
				if (commandSplit.length > 2) {
					itemAmount = Integer.parseInt(commandSplit[2]);
				}
				player.getInventory().add(itemId, itemAmount);
			} else {
				player.getActionSender()
						.msg("Sorry, only " + Constants.COMMAND_ITEM_RANK.toString().toLowerCase()
								+ "s can perform this command.");
				return;
			}
		}

		if (command.startsWith("c")) {
			dump(player);
		}

		if (command.startsWith("tele")) {
			int x = Integer.parseInt(commandSplit[1]);
			int y = Integer.parseInt(commandSplit[2]);
			player.getActionSender().msg("Tele!");
			// player.setAbs(new Coordinate(x, y));
			player.setTeleportTo(new Coordinate(x, y));
		}

		if (command.startsWith("coords")) {

			player.getActionSender().msg("X:" + player.getAbs().getX() + ":" + player.getAbs().getY() + ".");
		}

		if (command.startsWith("pickup")) {
			if (player.getRank().get() >= Constants.COMMAND_ITEM_RANK.get()) {
				String itemName = commandSplit[1];
				player.getActionSender().msg(itemName);
				int itemId = World.item.getId(itemName.replaceAll("_", " "));
				int itemAmount = 1;
				if (itemId > Constants.ITEM_CAP) {
					player.getActionSender().msg("Invalid item index!");
					player.getActionSender().msg(
							"Syntax: ::item [index] [amount] or just ::item [index] if one item only.");
					return;
				}
				if (commandSplit.length > 2) {
					itemAmount = Integer.parseInt(commandSplit[2]);
				}
				player.getActionSender().msg(itemId + ".");
				player.getInventory().add(itemId, itemAmount);
			} else {
				player.getActionSender()
						.msg("Sorry, only " + Constants.COMMAND_ITEM_RANK.toString().toLowerCase()
								+ "s can perform this command.");
				return;
			}
		}

		if (command.startsWith("mute")) {
			if (player.getRank().get() >= Constants.COMMAND_MUTE_RANK.get()) {
				String playerToMute = commandSplit[1];
				String duration = commandSplit[2];
				int durationNumber = 0;
				if (duration.equalsIgnoreCase("perm")) {
					durationNumber = -2;
				} else {
					durationNumber = Integer.parseInt(duration);
				}
				player.getActionSender().msg("Attempting to mute " + playerToMute + "...");
				if (playerToMute.length() > 0 && playerToMute.length() < 13) {
					if (World.playerOnline(playerToMute)) {
						Player target = World.getPlayer(playerToMute);
						target.getInformation().setMuteDuration(durationNumber);
						target.getActionSender().msg("You have been muted by " + player.getName() + "!");
						player.getActionSender().msg("Successfully muted " + playerToMute + ".");
					} else {
						player.getActionSender().msg("Unable to find " + playerToMute + ".");
					}
				}
			} else {
				player.getActionSender()
						.msg("Sorry, only " + Constants.COMMAND_MUTE_RANK.toString().toLowerCase()
								+ "s can perform this command.");
				return;
			}
		}

		if (command.equalsIgnoreCase("master")) {
			if (player.getRank().get() >= Constants.COMMAND_MUTE_RANK.get()) {
				for (int i = 0; i < Constants.SKILL_CAP; i++) {
					player.getSkills().changeLevel(i, 99);
				}
				player.getSkills().refresh();
			} else {
				player.getActionSender()
						.msg("Sorry, only " + Constants.COMMAND_MASTER_RANK.toString().toLowerCase()
								+ "s can perform this command.");
				return;
			}
		}
	}

}
