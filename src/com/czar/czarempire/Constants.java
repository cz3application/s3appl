package com.czar.czarempire;

import com.czar.czarempire.model.Coordinate;
import com.czar.czarempire.util.Rank;

public class Constants {

	/**
	 * 
	 * N E T W O R K I N G
	 * 
	 */

	public static final int PORT = 43594;
	public static final int PLAYER_CAP = 256;
	public static final int NPC_CAP = 1024;
	public static final int SOCKET_CAP = PLAYER_CAP;
	public static final long LISTENER_TICK_RATE = 600;
	public static final long WORLD_TICK_RATE = 500;
	public static final long SAVE_TICK_RATE = 100;
	public static final int ITEM_CAP = 15790;
	public static final int SKILL_CAP = 23;

	/**
	 * 
	 * C O N T E N T
	 * 
	 */

	public static final String NAME = "Caesar's Empire";
	public static final double VER = 1.0;

	public static final String[] ADMINISTRATOR = { "Czar" };
	public static final String[] MODERATOR = {};

	public static final int X = 3109;
	public static final int Y = 3822;
	public static final int Z = 0;

	public static final Rank COMMAND_ITEM_RANK = Rank.ADMINISTRATOR;
	public static final Rank COMMAND_MASTER_RANK = Rank.ADMINISTRATOR;
	public static final Rank COMMAND_MUTE_RANK = Rank.MODERATOR;

	public static final Coordinate LUMBRIDGE = new Coordinate(3222, 3222);

	public static final String[] LOGIN_MESSAGE = { "Welcome to " + NAME + ".", "Enjoy your stay!" };

	public static final String[] LOGIN_MESSAGE_MODERATOR = {
			"To mute a player, use the following command: ::mute [name] [duration in hours]" };

	public static final String[] LOGIN_MESSAGE_ADMINISTRATOR = { "You own the game, have fun." };

	public static final String DIR_NPC_SPAWN = "./data/world/npc_spawn.cfg";
	public static final String DIR_ITEM_STACK = "./data/world/item/item_stack.txt";
	public static final String DIR_ITEM_2H = "./data/world/item/item_2h.txt";
	public static final String DIR_ITEM_DEFINITIONS = "./data/world/item/items.cfg";

	public static final int TAB_CHAT = 90;
	public static final int TAB_ATTACK = 99;
	public static final int TAB_STATS = 100;
	public static final int TAB_QUEST = 101;
	public static final int TAB_INVENTORY = 102;
	public static final int TAB_EQUIPMENT = 103;
	public static final int TAB_PRAYER = 104;
	public static final int TAB_SPELLBOOK = 105;
	public static final int TAB_CLANCHAT = 106;
	public static final int TAB_FRIENDS = 107;
	public static final int TAB_IGNORES = 108;
	public static final int TAB_LOGOUT = 109;
	public static final int TAB_SETTINGS = 110;
	public static final int TAB_EMOTES = 111;
	public static final int TAB_MUSIC = 112;

	public static final int INTERFACE_SCREEN = 75;
	public static final int INTERFACE_WINDOW = 548;

	public static final int SKILL_ATTACK = 0;
	public static final int SKILL_DEFENCE = 1;
	public static final int SKILL_STRENGTH = 2;
	public static final int SKILL_HITPOINTS = 3;
	public static final int SKILL_RANGED = 4;
	public static final int SKILL_PRAYER = 5;
	public static final int SKILL_MAGIC = 6;

	public static final int EQUIPMENT_HAT = 0;
	public static final int EQUIPMENT_CAPE = 1;
	public static final int EQUIPMENT_NECK = 2;
	public static final int EQUIPMENT_WEAPON = 3;
	public static final int EQUIPMENT_CHEST = 4;
	public static final int EQUIPMENT_SHIELD = 5;
	public static final int EQUIPMENT_LEGS = 7;
	public static final int EQUIPMENT_HANDS = 9;
	public static final int EQUIPMENT_FEET = 10;
	public static final int EQUIPMENT_RING = 12;
	public static final int EQUIPMENT_ARROWS = 13;

	public static final String[] SKILL_NAMES = { "attack", "defence", "strength", "constitution", "range", "prayer",
			"magic", "cooking", "woodcutting", "fletching", "fishing", "firemaking", "crafting", "smithing",
			"mining", "herblore", "agility", "thieving", "slayer", "farming", "runecrafting", "hunter",
			"construction" };

	/**
	 * 
	 * M I S C
	 * 
	 */

	public static char[] VALID_CHARACTERS = { '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9' };

	public static int[] CRC32_TABLE = { 0xff, 0x0, 0xff, 0x0, 0x0, 0x0, 0x0, 0x80, 0xfe, 0xbb, 0xa4, 0x5f, 0x0, 0x0,
			0x0, 0x0, 0x2b, 0x3d, 0x5c, 0xd8, 0x0, 0x0, 0x0, 0x0, 0xf9, 0xb4, 0x1a, 0xe1, 0x0, 0x0, 0x0, 0xfe,
			0x5c, 0xb0, 0x6b, 0xd7, 0x0, 0x0, 0x0, 0x6c, 0x5a, 0x62, 0xe0, 0x19, 0x0, 0x0, 0x0, 0x14, 0xa6, 0x84,
			0x2e, 0x77, 0x0, 0x0, 0x0, 0x54, 0xa, 0xe4, 0x31, 0x30, 0x0, 0x0, 0x0, 0x0, 0x67, 0xf7, 0x9b, 0x5a,
			0x0, 0x0, 0x0, 0x74, 0x2a, 0x13, 0x9d, 0xf8, 0x0, 0x0, 0x0, 0x19, 0xc9, 0xa3, 0x46, 0x3a, 0x0, 0x0,
			0x0, 0x3, 0x2e, 0xcb, 0xa4, 0xad, 0x0, 0x0, 0x0, 0x0, 0x1e, 0x2c, 0xdd, 0x62, 0x0, 0x0, 0x0, 0x0,
			0x81, 0xc7, 0xcc, 0x8a, 0x0, 0x0, 0x0, 0x53, 0x7, 0x8e, 0x6a, 0x3e, 0x0, 0x0, 0x0, 0x1, 0xa3, 0x8c,
			0xf6, 0x94, 0x0, 0x0, 0x0, 0x1, 0xb8, 0xf2, 0x4d, 0x21, 0x0, 0x0, 0x0, 0x0 };

	public static byte[] DIR_DELTA_X = new byte[] { -1, 0, 1, -1, 1, -1, 0, 1 };
	public static byte[] DIR_DELTA_Y = new byte[] { 1, 1, 1, 0, 0, -1, -1, -1 };

	public static byte[] PACKET_LEN = { -3, 8, -3, -3, -3, -3, -3, -3, -3, -3, -3, -1, -3, -3, -3, -3, -3, -3, -3, -3,
			-3, -3, -3, -3, -3, -3, -3, 13, -3, -3, -3, -3, -3, -3, 01, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3,
			-3, -1, -3, -3, -3, -3, -3, -1, -3, -3, -3, -3, -3, -3, -1, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3,
			-3, 8, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 8, -3, -3, -3, -3, -3, -3,
			-3, -3, -3, -3, -3, -3, -3, -3, -3, 06, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3,
			-1, 8, -3, 8, -3, -3, -3, -3, -3, -3, 8, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3,
			-3, -3, -3, -3, -3, 8, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -1, -3, -3, 9, 04, -3, 04, -3, -3, -3,
			-3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 00, -3, -3, -3, -3, -3, -3, -3, -3, -3, 02, -3,
			-3, -3, -3, -3, -1, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 04, -3, -3, -3, -3, -3, -3, -3,
			-3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 06, -3, 00, -3, -3, -3, -3, 8, -3, -3, -3, -3, -3, -3, -3,
			-3, -3, -3, -1, -3, -3, -3 };
}