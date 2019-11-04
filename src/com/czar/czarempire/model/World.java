package com.czar.czarempire.model;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.czar.czarempire.Constants;
import com.czar.czarempire.Server;
import com.czar.czarempire.model.npc.Npc;
import com.czar.czarempire.model.player.Player;
import com.czar.czarempire.net.packet.LoginHandler;
import com.czar.czarempire.net.packet.PacketOperator;
import com.czar.czarempire.util.Savegame;

public class World {
	public Server server;
	public static Player[] players = new Player[Constants.PLAYER_CAP];
	public static Npc[] npcs = new Npc[Constants.NPC_CAP];
	public static Savegame savegame = new Savegame();
	public static PacketOperator packetOperator = new PacketOperator();
	public static RSItem item;
	public static NpcDefinition npcDef;

	public World(Server server) {
		this.server = server;

		// launch it bebe.
		launch();

		// launch items
		item = new RSItem();

		// load npc definitions
		npcDef = new NpcDefinition();

		// WORLD READY!!
		System.out.println();
		System.out.print("Launching world...");
		System.out.print("ready!");
		System.out.println();
	}

	public void launch() {

		// launch tickers
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
				tick(), 0, Constants.WORLD_TICK_RATE, TimeUnit.MILLISECONDS);
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
				savegame(), 0, Constants.SAVE_TICK_RATE, TimeUnit.MILLISECONDS);
		// player ticker
		tick().run();
		// load lists etc..

		// end of loading
	}

	public Runnable tick() {
		return new Runnable() {
			@Override
			public void run() {
				connectPlayers();
				packetOperator.parse();
				for (Player player : players) {
					if (player == null || !player.isLoggedIn()) {
						continue;
					}
					if (player.disconnected[0] && player.disconnected[1]) {
						removePlayer(player.getIndex());
						continue;
					}
					player.handleMovement();
					player.tick();
				}
				for (Player player : players) {
					if (player == null || !player.isLoggedIn()) {
						continue;
					}
					player.update();
					for (Npc npc : npcs) {
						if (npc == null) {
							continue;
						}
						npc.update(player);
					}
				}
				for (Player p : players) {
					if (p == null || !p.isLoggedIn()) {
						continue;
					}
					p.clearUpdateFlags();
					try {
						if (p.getStreamBuffer().outOffset > 0) {
							p.getSocket()
									.getOutputStream()
									.write(p.getStreamBuffer().outBuffer, 0,
											p.getStreamBuffer().outOffset);
						}
						p.getStreamBuffer().outOffset = 0;
						p.getSocket().getOutputStream().flush();
					} catch (final Exception e) {
						p.disconnected[0] = true;
					}
				}
				for (Npc n : npcs) {
					if (n == null) {
						continue;
					}
					rebuildNPCs();
				}
			}

		};
	}

	public void connectPlayers() {
		LoginHandler login = null;
		for (Player player : players) {
			if (player == null || player.isLoggedIn()) {
				continue;
			}
			if (login == null) {
				login = new LoginHandler();
			}
			login.login(player);
		}
	}

	public void removePlayer(int id) {
		if (players[id] == null) {
			return;
		}
		if (players[id].isLoggedIn()) {
			try {
				savegame.saveCharacter(players[id]);
			} catch (final Exception e) {
			}
		}
		players[id].end();
		players[id] = null;
	}

	public void registerConnection(java.net.Socket s) {
		int slot = -1, i = 1;
		do {
			if (players[i] == null) {
				slot = i;
				break;
			}
			i++;
			if (i >= Constants.PLAYER_CAP)
				i = 0;
		} while (i <= Constants.PLAYER_CAP);
		Player newClient = new Player(s, slot);
		if (slot == -1) {
			return;
		}
		players[slot] = newClient;
	}

	public Runnable savegame() {
		return new Runnable() {
			@Override
			public void run() {
				for (Player player : players) {
					if (player == null || !player.isLoggedIn()) {
						continue;
					}
					try {
						savegame.saveCharacter(player);
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}
		};
	}

	// TODO : Fix player count method !!
	public int getPlayerCount() {
		// for (Player player : players) {
		// if (player == null) {
		// continue;
		// }
		// if (!player.isLoggedIn()) {
		// continue;
		// }
		//
		// }
		return -1;
	}

	public static boolean playerOnline(String username) {
		for (Player player : players) {
			if (player == null) {
				continue;
			}
			if (!player.isLoggedIn()) {
				continue;
			}
			if (player.getName().equalsIgnoreCase(username)) {
				return true;
			}
		}
		return false;
	}

	public static Player getPlayer(String username) {
		for (Player player : players) {
			if (player == null) {
				continue;
			}
			if (!player.isLoggedIn()) {
				continue;
			}
			if (player.getName().equalsIgnoreCase(username)) {
				return player;
			}
		}
		return null;
	}

	public static void rebuildNPCs() {
		for (Player p : players) {
			if (p == null) {
				continue;
			}
			p.rebuildNpcList = true;
		}
	}

	public static void addNpc(int clientIndex, int x, int y, int z) {
		int slot = -1;
		for (int i = 0; i < npcs.length; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			return;
		}
		Npc npc = npcs[slot] = new Npc(slot, clientIndex);
		npc.setAbs(new Coordinate(x, y, z));
		npcs[slot] = npc;
	}

}
