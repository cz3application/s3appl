package com.czar.czarempire.model.player.update.impl;

import com.czar.czarempire.Constants;
import com.czar.czarempire.model.Entity;
import com.czar.czarempire.model.EntityUpdateMask;
import com.czar.czarempire.model.player.Player;
import com.czar.czarempire.net.Stream;
import com.czar.czarempire.util.Text;

public class AppearanceMask implements EntityUpdateMask {

	/*
	 * Entire appearance block for drawing the player and equipment, color, clothes.
	 */
	@Override
	public void appendMask(Entity e, Stream str) {
		if (str == null) {
			return;
		}
		Player p = (Player) e;
		Stream playerProps = new Stream(1, 100);
		playerProps.writeByte(p.getAppearance().gender);
		playerProps.writeByte(p.getAppearance().skullIcon);
		playerProps.writeByte(p.getAppearance().prayerIcon);
		for (int i = 0; i < 4; i++) {
			if (p.getEquipment().id[i] > 0) {
				playerProps.writeWord(0x200 + p.getEquipment().id[i]);
			} else {
				playerProps.writeByte(0);
			}
		}
		if (p.getEquipment().id[Constants.EQUIPMENT_CHEST] > 0) {
			playerProps.writeWord(0x200 + p.getEquipment().id[Constants.EQUIPMENT_CHEST]);
		} else {
			playerProps.writeWord(0x100 + p.getAppearance().look[2]); // Torso
		}
		if (p.getEquipment().id[Constants.EQUIPMENT_SHIELD] > 0) {
			playerProps.writeWord(0x200 + p.getEquipment().id[Constants.EQUIPMENT_SHIELD]);
		} else {
			playerProps.writeByte(0);
		}
		if (!p.getEquipment().isFullbody(p.getEquipment().id[Constants.EQUIPMENT_CHEST])) {
			playerProps.writeWord(0x100 + p.getAppearance().look[3]); // pArms
		} else {
			playerProps.writeByte(0);
		}
		if (p.getEquipment().id[Constants.EQUIPMENT_LEGS] > 0) {
			playerProps.writeWord(0x200 + p.getEquipment().id[Constants.EQUIPMENT_LEGS]);
		} else {
			playerProps.writeWord(0x100 + p.getAppearance().look[5]); // pLegs
		}
		if (!p.getEquipment().isFullhat(p.getEquipment().id[Constants.EQUIPMENT_HAT])
				&& !p.getEquipment().isFullmask(p.getEquipment().id[Constants.EQUIPMENT_HAT])) {
			playerProps.writeWord(0x100 + p.getAppearance().look[0]); // pHead
		} else {
			playerProps.writeByte(0);
		}
		if (p.getEquipment().id[Constants.EQUIPMENT_HANDS] > 0) {
			playerProps.writeWord(0x200 + p.getEquipment().id[Constants.EQUIPMENT_HANDS]);
		} else {
			playerProps.writeWord(0x100 + p.getAppearance().look[4]); // pHands
		}
		if (p.getEquipment().id[Constants.EQUIPMENT_FEET] > 0) {
			playerProps.writeWord(0x200 + p.getEquipment().id[Constants.EQUIPMENT_FEET]);
		} else {
			playerProps.writeWord(0x100 + p.getAppearance().look[6]); // pFeet
		}
		if (!p.getEquipment().isFullmask(p.getEquipment().id[Constants.EQUIPMENT_HAT])) {
			playerProps.writeWord(0x100 + p.getAppearance().look[1]); // pBeard
		} else {
			playerProps.writeByte(0);
		}
		for (int j = 0; j < 5; j++) {
			playerProps.writeByte(p.getAppearance().color[j]);
		}
		playerProps.writeWord(p.getEquipment().getStandAnimation());
		playerProps.writeWord(p.getEquipment().getTurnAnimation());
		playerProps.writeWord(p.getEquipment().getWalkAnimation());
		playerProps.writeWord(p.getEquipment().getTurn180Animation());
		playerProps.writeWord(p.getEquipment().getClockwiseTurn90Animation());
		playerProps.writeWord(p.getEquipment().getAntiClockwiseTurn90Animation());
		playerProps.writeWord(p.getEquipment().getRunAnimation());
		playerProps.writeQWord(Text.stringToLong(p.getName()));
		playerProps.writeByte(p.getCombatLevel());
		playerProps.writeWord(0);
		str.writeByteA(playerProps.outOffset);
		str.writeBytes_reverse(playerProps.outBuffer, playerProps.outOffset, 0);
		playerProps = null;
	}

}
