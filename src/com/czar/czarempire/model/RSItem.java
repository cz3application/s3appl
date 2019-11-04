package com.czar.czarempire.model;

import java.io.BufferedReader;
import java.io.FileReader;

import com.czar.czarempire.Constants;

public class RSItem {

	public ItemDefinition[] item = new ItemDefinition[Constants.ITEM_CAP];
	private boolean[] canStack = new boolean[Constants.ITEM_CAP];
	private boolean[] twoHanded = new boolean[Constants.ITEM_CAP];

	public RSItem() {
		System.out.println("Loading RSItem...");
		// Loading definitions
		System.out.print("Loading item definitions...");
		loadDefinitions();
		System.out.print("complete!");
		System.out.println();
		// Loading stackables
		System.out.print("Loading stackable items...");
		loadStacks();
		System.out.print("complete!");
		System.out.println();
		// Loading twohanded weapons
		System.out.print("Loading two-handed weapons...");
		loadTwoHanded();
		System.out.print("complete!");
		// Finished loading items
		System.out.println();
		System.out.print("RSItem ready!");
	}

	public void loadStacks() {
		int itemId;
		String name;
		try {
			BufferedReader in = new BufferedReader(new FileReader(Constants.DIR_ITEM_STACK));
			while ((name = in.readLine()) != null) {
				itemId = Integer.parseInt(name);
				if (itemId != -1) {
					if (canStack != null) {
						canStack[itemId] = true;
					}
				}
			}
			in.close();
			in = null;
		} catch (final Exception e) {
			System.out.println("Error loading stackable list.");
		}
	}

	public void loadTwoHanded() {
		int itemId;
		String name;
		try {
			BufferedReader in = new BufferedReader(new FileReader(Constants.DIR_ITEM_2H));
			while ((name = in.readLine()) != null) {
				itemId = Integer.parseInt(name);
				if (itemId != -1) {
					if (twoHanded != null) {
						twoHanded[itemId] = true;
					}
				}
			}
			in.close();
			in = null;
		} catch (final Exception e) {
			System.out.println("Error loading stackable list.");
		}
	}

	private boolean loadDefinitions() {
		String line = "", token = "", token2 = "", token2_2 = "", token3[] = new String[10];
		BufferedReader list = null;
		try {
			list = new BufferedReader(new FileReader(Constants.DIR_ITEM_DEFINITIONS));
			line = list.readLine().trim();
		} catch (final Exception e) {
			System.err.println("Error loading item list.");
			return false;
		}
		while (line != null) {
			final int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot).trim();
				token2 = line.substring(spot + 1).trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("item")) {
					final int[] bonuses = new int[12];
					for (int i = 0; i < 12; i++) {
						if (token3[(6 + i)] != null) {
							bonuses[i] = Integer.parseInt(token3[(6 + i)]);
						} else {
							break;
						}
					}
					create(Integer.parseInt(token3[0]), token3[1].replaceAll("_", " "),
							token3[2].replaceAll("_", " "), Integer.parseInt(token3[4]),
							Integer.parseInt(token3[4]), Integer.parseInt(token3[6]), bonuses);
				}
			} else {
				if (line.equals("[ENDOFITEMLIST]")) {
					try {
						list.close();
					} catch (final Exception exception) {
					}
					list = null;
					return true;
				}
			}
			try {
				line = list.readLine().trim();
			} catch (final Exception exception1) {
				try {
					list.close();
				} catch (final Exception exception) {
				}
				list = null;
				return true;
			}
		}
		return false;
	}

	public boolean canStack(int itemId) {
		return canStack[itemId];
	}

	public void create(int ItemId, String ItemName, String ItemDescription, int ShopValue, int LowAlch, int HighAlch,
			int Bonuses[]) {
		if (ItemId > Constants.ITEM_CAP) {
			System.err.println("item_cap too low!");
			return;
		}
		item[ItemId] = new ItemDefinition(ItemId, ItemName, ItemDescription, ShopValue, LowAlch, Bonuses);
	}

	public String getName(int id) {
		if (id == -1 || id >= Constants.ITEM_CAP) {
			return new String("Unarmed");
		}
		if (item[id] != null) {
			return (item[id].itemName);
		}
		return "-2";
	}

	public int getId(String name) {
		name.replaceAll("_", " ");
		name.toLowerCase();
		for (int i = 0; i < 12000; i++) {
			if (item[i] != null) {
				item[i].itemName.replaceAll("_", " ");
				item[i].itemName.toLowerCase();
				if (name.equalsIgnoreCase(item[i].itemName)) {
					return i;
				}
			}
		}
		return -1;
	}

	public boolean isTwoHanded(int wearItem) {
		return twoHanded[wearItem];
	}

}
