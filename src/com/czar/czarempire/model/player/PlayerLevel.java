package com.czar.czarempire.model.player;

import com.czar.czarempire.Constants;

public class PlayerLevel {
	private Player player;
	private int[] level = new int[Constants.SKILL_CAP];
	private int[] xp = new int[Constants.SKILL_CAP];

	public PlayerLevel(Player player) {
		this.player = player;
		for (int i = 0; i < Constants.SKILL_CAP; i++) {
			level[i] = 1;
			xp[i] = 0;
		}
		level[Constants.SKILL_HITPOINTS] = 10;
		xp[Constants.SKILL_HITPOINTS] = 1154;
	}

	public int getLevel(int skill) {
		return level[skill];
	}

	public int getXp(int skill) {
		return xp[skill];
	}

	public int setLevel(int skill, int lvl) {
		return level[skill] = lvl;
	}

	public int setXp(int skill, int amount) {
		return xp[skill] = amount;
	}

	public void changeLevel(int skill, int lvl) {
		level[skill] = lvl;
		xp[skill] = getXPForLevel(lvl);
	}

	public void refresh() {
		for (int i = 0; i < Constants.SKILL_CAP; i++) {
			refresh(i);
		}
	}

	public void refresh(int skill) {
		player.getActionSender().sendSkill(skill);
	}

	public int getCombatLevel() {
		double hitpoints = getLevelForXP(3);
		double defence = getLevelForXP(1);
		double prayer = getLevelForXP(5);
		double attack = getLevelForXP(0);
		double strength = getLevelForXP(2);
		double range = getLevelForXP(4);
		double magic = getLevelForXP(6);
		double meleeFormula = (0.25 * ((1.30 * (attack + strength)) + defence
				+ hitpoints + (0.50 * prayer) + (0.50)));
		double rangeFormula = (0.25 * ((1.30 * (1.50 * range)) + defence
				+ hitpoints + (0.50 * prayer) + (0.50)));
		double magicFormula = (0.25 * ((1.30 * (1.50 * magic)) + defence
				+ hitpoints + (0.50 * prayer) + (0.50)));
		double combat = 0;
		if ((meleeFormula >= magicFormula) && (meleeFormula >= rangeFormula)) {
			combat = meleeFormula;
		}
		if ((rangeFormula >= meleeFormula) && (rangeFormula >= magicFormula)) {
			combat = rangeFormula;
		}
		if ((magicFormula >= meleeFormula) && (magicFormula >= rangeFormula)) {
			combat = magicFormula;
		}
		return ((int) Math.floor(combat));
	}

	public int getLevelForXP(int skillId) {
		int exp = xp[skillId];
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl < 100; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if ((output - 1) >= exp) {
				return lvl;
			}
		}
		return 99;
	}

	public int getXPForLevel(int level) {
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int) Math.floor(points / 4);
		}
		return 0;
	}

	public String getSkillName(int skillId) {
		return Constants.SKILL_NAMES[skillId];
	}

}
