package com.czar.czarempire.model.player;

import com.czar.czarempire.model.World;

public class PlayerInventory {
	private Player player;
	private int[] id = new int[28];
	private int[] amount = new int[28];

	public PlayerInventory(Player player) {
		this.player = player;
		for (int i = 0; i < 28; i++) {
			id[i] = -1;
		}
	}

	public void setId(int[] id) {
		this.id = id;
	}

	public int[] getId() {
		return id;
	}

	public void setAmount(int[] amount) {
		this.amount = amount;
	}

	public int[] getAmount() {
		return amount;
	}

	public boolean add(int item, int itemAmount) {
		if (World.item == null) {
			System.err.println("RSItem null!");
			return false;
		}
		if (item < 1) {
			return false;
		}
		int slot = getFreeSlot(id);
		if (World.item.canStack(item)) {
			if (contains(item)) {
				amount[findSlot(item)] += itemAmount;
				return true;
			} else {
				id[slot] = item;
				amount[slot] = itemAmount;
				return true;
			}
		}
		if (slot == -1) {
			player.getActionSender().msg("Inventory is full.");
			return false;
		}
		if (!World.item.canStack(item)) {
			for (int i = 0; i < itemAmount; i++) {
				int newSlot = getFreeSlot(id);
				if (newSlot > -1) {
					id[newSlot] = item;
					amount[newSlot] = 1;
				} else {
					player.getActionSender().msg("Inventory is full.");
					break;
				}
			}
		}
		refresh();
		return false;
	}

	public void delete(int item, int slot) {
		if (World.item == null) {
			System.err.println("RSItem null!");
			return;
		}
		id[slot] = -1;
		amount[slot] = 0;
		refresh();
	}

	public void add(int item) {
		add(item, 1);
	}

	public int findSlot(int val) {
		for (int i = 0; i < id.length; i++) {
			if (id[i] == val) {
				return i;
			}
		}
		return -1;
	}

	public boolean contains(int val) {
		for (int i = 0; i < id.length; i++) {
			if (id[i] == val) {
				return true;
			}
		}
		return false;
	}

	public int getFreeSlot(int val[]) {
		for (int i = 0; i < val.length; i++) {
			if (val[i] == -1) {
				return i;
			}
		}
		return -1;
	}

	public void refresh() {
		if (player == null) {
			System.err.println("Player is null!");
			return;
		}
		if (player.getActionSender() == null) {
			System.err.println("ActionSender is null!");
			return;
		}
		player.getActionSender().sendItem(149, 0, 93, id, amount);
	}

	public void replaceItem(int inventorySlot, int i) {
		id[inventorySlot] = i;
		amount[inventorySlot] = 1;
		refresh();
	}

}
