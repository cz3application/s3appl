package com.czar.czarempire.model.npc;

import com.czar.czarempire.Constants;
import com.czar.czarempire.model.Entity;
import com.czar.czarempire.model.npc.update.NpcUpdate;
import com.czar.czarempire.model.player.Player;
import com.czar.czarempire.net.Stream;

public class Npc extends Entity {
	private NpcUpdate update;

	public Npc(int slot, int clientIndex) {
		setIndex(slot);
		setClientIndex(clientIndex);
		setUpdate(new NpcUpdate(this));
	}

	public int getCombatLevel() {
		return combatLevel;
	}

	@Override
	public void handleMovement() {
	}

	public void update(Player player) {
		update.updateNpc(player, player.getStreamBuffer());
	}

	public void setUpdate(NpcUpdate update) {
		this.update = update;
	}

	public NpcUpdate getUpdate() {
		return update;
	}

	public void updateMovement(Stream str) {
		/*
		 * Check if NPC is running.
		 */
		if (getRunDirection() == -1) {
			/*
			 * They are not, check if walking.
			 */
			if (getWalkDirection() == -1) {
				/*
				 * They are not walking, check if NPC needs updating.
				 */
				if (isUpdateRequired()) {
					str.writeBits(1, 1);
					str.writeBits(2, 0);
				} else {
					/*
					 * No update or movement is required
					 */
					str.writeBits(1, 0);
				}
			} else {
				/*
				 * They are walking, update is required
				 */
				str.writeBits(1, 1);
				/*
				 * NPC is walking in a single tile
				 */
				str.writeBits(2, 1);
				/*
				 * Write direction
				 */
				str.writeBits(3, getWalkDirection());
				/*
				 * Write update flag
				 */
				str.writeBits(1, isUpdateRequired() ? 1 : 0);
			}
		} else {
			/*
			 * They are running, indicate update is required.
			 */
			str.writeBits(1, 1);
			/*
			 * NPC is running two tiles
			 */
			str.writeBits(2, 2);
			/*
			 * Write directions
			 */
			str.writeBits(3, getWalkDirection());
			str.writeBits(3, getRunDirection());
			/*
			 * Write update flag
			 */
			str.writeBits(1, isUpdateRequired() ? 1 : 0);
		}
	}

}
