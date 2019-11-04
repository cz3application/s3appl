package com.czar.czarempire.net.packet.impl;

import com.czar.czarempire.Constants;
import com.czar.czarempire.model.World;
import com.czar.czarempire.model.player.Player;

public class WearItemPacketHandler implements Packet {

	@Override
	public void append(Player player, int id, int len) {
		if (player == null) {
			return;
		}
		int inventorySlot = player.getStreamBuffer()
				.readUnsignedWordBigEndianA();
		int wearItem = player.getStreamBuffer().readUnsignedWord();
		int wearItemAmount = player.getInventory().getAmount()[inventorySlot];
		if (inventorySlot < 0
				|| inventorySlot > player.getInventory().getId().length
				|| wearItemAmount < 1 || wearItem < 1) {
			return;
		}
		/*
		 * To avoid item dupers...
		 */
		if (player.getInventory().contains(wearItem)) {
			if (!player.getEquipment().canWear(wearItem)) {
				return;
			}
			int targetEquipmentSlot = player.getEquipment().getEquipmentSlot(
					wearItem);
			if (targetEquipmentSlot == -1) {
				player.getActionSender().msg(
						"Item not supported: " + World.item.getName(wearItem)
								+ ".");
				return;
			}
			player.getInventory().delete(wearItem, inventorySlot);
			if (targetEquipmentSlot == Constants.EQUIPMENT_WEAPON) {
				if (World.item.isTwoHanded(wearItem) && player.getEquipment().get(Constants.EQUIPMENT_SHIELD) > 0) {
					/*
					 * If player is trying to wield a two-handed weapon with a shield currently on
					 */
					boolean wieldingWeapon = player.getEquipment().get(Constants.EQUIPMENT_WEAPON) > 0 ? true : false;
					
					if (player.getInventory().add(player.getEquipment().get(Constants.EQUIPMENT_SHIELD), 1)) {
						player.getEquipment().id[Constants.EQUIPMENT_SHIELD] = -1;
						player.getEquipment().amount[Constants.EQUIPMENT_SHIELD] = 0;
					} else {
						return;
					}
				}
			}
			if (player.getEquipment().id[targetEquipmentSlot] > 0) {
				if (!player.getInventory().add(player.getEquipment().id[targetEquipmentSlot], player.getEquipment().amount[targetEquipmentSlot])) {
					return;
				}
				player.getEquipment().id[targetEquipmentSlot] = wearItem;
				player.getEquipment().amount[targetEquipmentSlot] = 1;
			} else {
				player.getEquipment().id[targetEquipmentSlot] = wearItem;
				player.getEquipment().amount[targetEquipmentSlot] = 1;
			}
			player.getEquipment().refresh();
			player.getEquipment().sendAttackTab();
			player.setAppearanceUpdateRequired(true);
			player.setUpdateRequired(true);
		}

	}

}
