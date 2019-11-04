package com.czar.czarempire.model.player;

import com.czar.czarempire.Constants;
import com.czar.czarempire.model.World;

public class PlayerEquipment {
	public Player player;
	public int[] id = new int[14];
	public int[] amount = new int[14];
	private boolean[] multipleRequirements = new boolean[6];

	public PlayerEquipment(Player player) {
		this.player = player;
		for (int i = 0; i < 14; i++) {
			id[i] = -1;
		}
	}

	public int get(int slot) {
		return id[slot];
	}

	public int getRequiredAttackLevel(int itemId) {
		String itemName = World.item.getName(itemId);
		itemName.replaceAll("_", " ");
		itemName.toLowerCase();
		String[] nameSplit = itemName.split(" ");
		String weaponMetal = nameSplit[0];
		String weaponType = "";
		if (nameSplit.length > 1) {
			weaponType = nameSplit[1];
		}
		if (weaponType.contains("dagger") || weaponType.contains("claws") || weaponType.contains("sword")
				|| weaponType.contains("axe") || weaponType.contains("spear") || weaponType.contains("scimitar")
				|| weaponType.contains("mace") || weaponType.contains("warhammer")
				|| weaponType.contains("halberd") || weaponType.contains("defender")) {
			if (weaponMetal.equalsIgnoreCase("blurite")) {
				return 1;
			}
			if (weaponMetal.equalsIgnoreCase("bronze")) {
				return 1;
			}
			if (weaponMetal.equalsIgnoreCase("iron")) {
				return 1;
			}
			if (weaponMetal.equalsIgnoreCase("steel")) {
				return 5;
			}
			if (weaponMetal.equalsIgnoreCase("black")) {
				return 10;
			}
			if (weaponMetal.equalsIgnoreCase("white")) {
				return 10;
			}
			if (weaponMetal.equalsIgnoreCase("mithril")) {
				return 20;
			}
			if (weaponMetal.equalsIgnoreCase("adamant")) {
				return 30;
			}
			if (weaponMetal.equalsIgnoreCase("rune")) {
				return 40;
			}
			if (weaponMetal.equalsIgnoreCase("granite")) {
				return 50;
			}
			if (weaponMetal.equalsIgnoreCase("dragon")) {
				return 60;
			}
		}
		if (itemName.contains("godsword")) {
			return 75;
		}
		if (itemName.equalsIgnoreCase("barrelchest anchor")) {
			return 60;
		}
		if (itemName.equalsIgnoreCase("TokTz-Xil-Ak")) {
			return 60;
		}
		if (itemName.equalsIgnoreCase("TzHaar-Ket-Em")) {
			return 60;
		}
		if (itemName.equalsIgnoreCase("Toktz-Mej-Tal")) {
			return 60;
		}
		if (itemName.equalsIgnoreCase("Abyssal whip")) {
			return 70;
		}
		if (itemName.equalsIgnoreCase("Saradomin sword")) {
			return 70;
		}
		if (itemName.equalsIgnoreCase("Zamorakian Spear")) {
			return 70;
		}
		if (itemName.contains("Ahrim") && itemName.contains("staff")) {
			return 70;
		}
		if (itemName.contains("Dharok") && itemName.contains("greataxe")) {
			return 70;
		}
		if (itemName.contains("Guthan") && itemName.contains("spear")) {
			return 70;
		}
		if (itemName.contains("Torag") && itemName.contains("hammers")) {
			return 70;
		}
		if (itemName.contains("Verac") && itemName.contains("flail")) {
			return 70;
		}
		if (itemName.contains("castlewars sword 1")) {
			return 5;
		}
		if (itemName.equalsIgnoreCase("Ancient mace")) {
			return 15;
		}
		if (itemName.equalsIgnoreCase("Excalibur")) {
			return 20;
		}
		if (itemName.equalsIgnoreCase("cw2")) {
			return 20;
		}
		if (itemName.contains("battlestaff")) {
			return 30;
		}
		if (itemName.equalsIgnoreCase("cw3")) {
			return 30;
		}
		if (itemName.equalsIgnoreCase("fremmy blade")) {
			return 30;
		}
		if (itemName.contains("Mystic") && itemName.endsWith(" staff")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Brine sabre")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("cw4")) {
			return 40;
		}
		if (itemName.contains("void ")) {
			return 42;
		}
		if (itemName.equalsIgnoreCase("Ancient Staff")) {
			return 50;
		}
		if (itemName.equalsIgnoreCase("Iban's Staff")) {
			return 50;
		}
		if (itemName.equalsIgnoreCase("Granite Maul")) {
			return 50;
		}
		/*
		 * Salamanders
		 */
		if (itemName.equalsIgnoreCase("Swamp lizard")) {
			return 30;
		}
		if (itemName.equalsIgnoreCase("Orange salamander")) {
			return 50;
		}
		if (itemName.equalsIgnoreCase("Red salamander")) {
			return 60;
		}
		if (itemName.equalsIgnoreCase("Black salamander")) {
			return 70;
		}
		return -1;
	}

	public int getRequiredStrengthLevel(int itemId) {
		String itemName = World.item.getName(itemId);
		itemName.replaceAll("_", " ");
		itemName.toLowerCase();
		String[] nameSplit = itemName.split(" ");
		String weaponMetal = nameSplit[0];
		String weaponType = "";
		if (nameSplit.length > 1) {
			weaponType = nameSplit[1];
		}
		if (weaponType.contains("halberd")) {
			if (weaponMetal.equalsIgnoreCase("black")) {
				return 5;
			}
			if (weaponMetal.equalsIgnoreCase("white")) {
				return 5;
			}
			if (weaponMetal.equalsIgnoreCase("mithril")) {
				return 10;
			}
			if (weaponMetal.equalsIgnoreCase("adamant")) {
				return 15;
			}
			if (weaponMetal.equalsIgnoreCase("rune")) {
				return 20;
			}
			if (weaponMetal.equalsIgnoreCase("dragon")) {
				return 30;
			}
		}
		if (itemName.contains("void ")) {
			return 42;
		}
		if (itemName.equalsIgnoreCase("TzHaar-Ket-Om")) {
			return 60;
		}
		if (itemName.contains("Dharok") && itemName.contains("greataxe")) {
			return 70;
		}
		if (itemName.contains("Torag") && itemName.contains("hammers")) {
			return 70;
		}
		if (itemName.equalsIgnoreCase("Black mask")) {
			return 20;
		}
		if (itemName.contains("Granite ")) {
			return 50;
		}
		return -1;
	}

	public int getRequiredDefenceLevel(int itemId) {
		String itemName = World.item.getName(itemId);
		itemName.replaceAll("_", " ");
		itemName.toLowerCase();
		String[] nameSplit = itemName.split(" ");
		String weaponMetal = nameSplit[0];
		String weaponType = "";
		if (nameSplit.length > 1) {
			weaponType = nameSplit[1];
		}
		if (itemName.equalsIgnoreCase("skeletal gloves")) {
			return 1;
		}
		if (itemName.equalsIgnoreCase("skeletal boots")) {
			return 1;
		}
		if (itemName.equalsIgnoreCase("Hardleather body")) {
			return 10;
		}
		if (itemName.contains("void ")) {
			return 42;
		}
		if (itemName.contains("elemental")) {
			return 1;
		}
		if (itemName.contains("enchanted ") || itemName.contains("mystic ")) {
			return 20;
		}
		if (itemName.contains("infinity ")) {
			return 25;
		}
		if (itemName.contains("Third-Age robe")) {
			return 30;
		}
		if (itemName.contains("Third-Age amulet")) {
			return 30;
		}
		if (itemName.contains("Splitbark ")) {
			return 40;
		}
		if (itemName.contains("Skeletal ") && !itemName.contains("gloves") && !itemName.contains("boots")) {
			return 40;
		}
		if (itemName.contains("Lunar ")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Anti-dragonfire shield")) {
			return 1;
		}
		if (itemName.equalsIgnoreCase("Spined gloves")) {
			return 1;
		}
		if (itemName.equalsIgnoreCase("Spined boots")) {
			return 1;
		}
		if (itemName.equalsIgnoreCase("Rock-shell gloves")) {
			return 1;
		}
		if (itemName.equalsIgnoreCase("Rock-shell boots")) {
			return 1;
		}
		if (itemName.equalsIgnoreCase("Khazard")) {
			return 1;
		}
		if (itemName.equalsIgnoreCase("Spiny helmet")) {
			return 5;
		}
		if (itemName.contains("cw1")) {
			return 5;
		}
		if (itemName.equalsIgnoreCase("Black mask")) {
			return 10;
		}
		if (itemName.equalsIgnoreCase("Studded body")) {
			return 20;
		}
		if (itemName.contains("Yak-hide")) {
			return 20;
		}
		if (itemName.equalsIgnoreCase("Mirror shield")) {
			return 20;
		}
		if (itemName.contains("cw2")) {
			return 20;
		}
		if (itemName.equalsIgnoreCase("Fremennik round shield")) {
			return 25;
		}
		if (itemName.contains("Frog-leather")) {
			return 25;
		}
		if (itemName.equalsIgnoreCase("Fremennik shield")) {
			return 30;
		}
		if (itemName.equalsIgnoreCase("Fremennik helm")) {
			return 30;
		}
		if (itemName.equalsIgnoreCase("Gold helmet")) {
			return 30;
		}
		if (itemName.equalsIgnoreCase("Ram skull helmet")) {
			return 30;
		}
		if (itemName.contains("cw3")) {
			return 20;
		}
		if (itemName.contains("Snakeskin")) {
			return 20;
		}
		if (itemName.contains("D'hide") && itemName.contains("body")) {
			return 40;
		}
		if (itemName.contains("Zamorak") || itemName.contains("Saradomin") || itemName.contains("Guthix")) {
			if (itemName.contains("Coif")) {
				return 40;
			}
		}
		if (itemName.contains("Spined") && !itemName.contains("gloves") && !itemName.contains("boots")) {
			return 40;
		}
		if (itemName.contains("Rock-shell") && !itemName.contains("gloves") && !itemName.contains("boots")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Fighter torso")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Penance gloves")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Penance skirt")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Runner boots")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Healer hat")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Fighter hat")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Ranger hat")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Runner hat")) {
			return 40;
		}
		if (itemName.contains("cw4")) {
			return 40;
		}
		if (itemName.contains("void")) {
			return 42;
		}
		if (itemName.contains("Third-Age range")) {
			return 45;
		}
		if (itemName.equalsIgnoreCase("Berserker helm")) {
			return 45;
		}
		if (itemName.equalsIgnoreCase("Warrior helm")) {
			return 45;
		}
		if (itemName.equalsIgnoreCase("Archer helm")) {
			return 45;
		}
		if (itemName.equalsIgnoreCase("Farseer helm")) {
			return 45;
		}
		if (itemName.equalsIgnoreCase("Dwarven helmet")) {
			return 50;
		}
		if (itemName.equalsIgnoreCase("Helm of Neitiznot")) {
			return 55;
		}
		if (itemName.equalsIgnoreCase("TokTz-Ket-Xil")) {
			return 60;
		}
		if (weaponType.contains("body") || weaponType.contains("platelegs") || weaponType.contains("shield")
				|| weaponType.contains("helm") || weaponType.contains("skirt") || weaponType.contains("boots")
				|| weaponType.contains("defender")) {
			if (weaponMetal.equalsIgnoreCase("bronze")) {
				return 1;
			}
			if (weaponMetal.equalsIgnoreCase("iron")) {
				return 1;
			}
			if (weaponMetal.equalsIgnoreCase("steel")) {
				return 5;
			}
			if (weaponMetal.equalsIgnoreCase("black")) {
				return 10;
			}
			if (weaponMetal.equalsIgnoreCase("white")) {
				return 10;
			}
			if (weaponMetal.equalsIgnoreCase("mithril")) {
				return 20;
			}
			if (itemName.equalsIgnoreCase("Initiate")) {
				return 20;
			}
			if (weaponMetal.equalsIgnoreCase("adamant")) {
				return 30;
			}
			if (itemName.equalsIgnoreCase("Proselyte")) {
				return 30;
			}
			if (weaponMetal.equalsIgnoreCase("rune")) {
				return 40;
			}
			if (weaponMetal.equalsIgnoreCase("granite")) {
				return 50;
			}
			if (weaponMetal.equalsIgnoreCase("dragon")) {
				return 60;
			}
			if (itemName.equalsIgnoreCase("Third-Age")) {
				return 65;
			}
			if (itemName.equalsIgnoreCase("Bandos")) {
				return 65;
			}
			if (itemName.equalsIgnoreCase("Armadyl")) {
				return 70;
			}
			if (itemName.equalsIgnoreCase("Dragonfire")) {
				return 75;
			}
			if (itemName.contains("Crystal")) {
				return 70;
			}
			if (itemName.contains("Ahrim")) {
				return 70;
			}
			if (itemName.contains("Dharok")) {
				return 70;
			}
			if (itemName.contains("Guthan")) {
				return 70;
			}
			if (itemName.contains("Torag")) {
				return 70;
			}
			if (itemName.contains("Verac")) {
				return 70;
			}
			if (itemName.contains("Karil")) {
				return 70;
			}
		}
		return -1;
	}

	public String getQuestRequirement(int itemId) {
		String itemName = World.item.getName(itemId);
		itemName.replaceAll("_", " ");
		itemName.toLowerCase();
		if (itemName.equalsIgnoreCase("Rune platebody") || itemName.equalsIgnoreCase("Green d'hide body")) {
			return "Dragon Slayer";
		}
		if (itemName.contains("initiate")) {
			return "Recruitment Drive";
		}
		if (itemName.equalsIgnoreCase("Ram skull helmet")) {
			return "Rag and Bone Man";
		}
		if (itemName.contains("proselyte")) {
			return "Slug Menace";
		}
		if (itemName.equalsIgnoreCase("Helm of Neitiznot")) {
			return "Fremennik Isles";
		}
		if (itemName.equalsIgnoreCase("Berserker helm")) {
			return "Fremennik Trials";
		}
		if (itemName.equalsIgnoreCase("Warrior helm")) {
			return "Fremennik Trials";
		}
		if (itemName.equalsIgnoreCase("Archer helm")) {
			return "Fremennik Trials";
		}
		if (itemName.equalsIgnoreCase("Farseer helm")) {
			return "Fremennik Trials";
		}
		if (itemName.contains("Spined") && !itemName.contains("gloves") && !itemName.contains("boots")) {
			return "Fremennik Trials";
		}
		if (itemName.contains("Rock-shell") && !itemName.contains("gloves") && !itemName.contains("boots")) {
			return "Fremennik Trials";
		}
		if (itemName.equalsIgnoreCase("Dwarven Helmet")) {
			return "Grim Tales";
		}
		if (itemName.equalsIgnoreCase("Ancient mace")) {
			return "Another Slice of H.A.M.";
		}
		if (itemName.equalsIgnoreCase("Iban's staff")) {
			return "Underground Pass";
		}
		return "";
	}

	public int getRequiredRangedLevel(int itemId) {
		String itemName = World.item.getName(itemId);
		itemName.replaceAll("_", " ");
		itemName.toLowerCase();
		String[] nameSplit = itemName.split(" ");
		String weaponMetal = nameSplit[0];
		String weaponType = "";
		if (nameSplit.length > 1) {
			weaponType = nameSplit[1];
		}
		if (itemName.equalsIgnoreCase("Shortbow") || itemName.equalsIgnoreCase("Longbow")) {
			return 1;
		}
		if (weaponType.equalsIgnoreCase("Shortbow") || weaponType.equalsIgnoreCase("Longbow")) {
			if (weaponMetal.equalsIgnoreCase("Oak")) {
				return 5;
			}
			if (weaponMetal.equalsIgnoreCase("Willow")) {
				return 20;
			}
			if (weaponMetal.equalsIgnoreCase("Maple")) {
				return 30;
			}
			if (weaponMetal.equalsIgnoreCase("Yew")) {
				return 40;
			}
			if (weaponMetal.equalsIgnoreCase("Magic")) {
				return 50;
			}
		}
		if (itemName.equalsIgnoreCase("Dark bow")) {
			return 60;
		}
		if (itemName.equalsIgnoreCase("Ogre bow")) {
			return 30;
		}
		if (itemName.equalsIgnoreCase("Ogre composite")) {
			return 30;
		}
		if (itemName.contains("Crystal") && itemName.contains("bow")) {
			return 70;
		}
		/*
		 * Thrown weapons
		 */
		if (weaponType.equalsIgnoreCase("dart") || weaponType.equalsIgnoreCase("javelin")
				|| weaponType.equalsIgnoreCase("thrownaxe") || weaponType.equalsIgnoreCase("knife")) {
			if (weaponMetal.equalsIgnoreCase("Bronze")) {
				return 1;
			}
			if (weaponMetal.equalsIgnoreCase("Iron")) {
				return 1;
			}
			if (weaponMetal.equalsIgnoreCase("Steel")) {
				return 5;
			}
			if (weaponMetal.equalsIgnoreCase("Black")) {
				return 10;
			}
			if (weaponMetal.equalsIgnoreCase("Mithril")) {
				return 20;
			}
			if (weaponMetal.equalsIgnoreCase("Adamant")) {
				return 30;
			}
			if (weaponMetal.equalsIgnoreCase("Rune")) {
				return 40;
			}
			if (weaponMetal.equalsIgnoreCase("Dragon")) {
				return 60;
			}
		}
		if (itemName.equalsIgnoreCase("Chinchompa")) {
			return 45;
		}
		if (itemName.equalsIgnoreCase("Red chinchompa")) {
			return 60;
		}
		if (itemName.equalsIgnoreCase("TokTz-Xil-Ul")) {
			return 60;
		}
		/*
		 * Armour
		 */
		if (itemName.contains("Leather")) {
			return 1;
		}
		if (itemName.startsWith("Spined")) {
			if (itemName.contains("gloves") || itemName.contains("boots")) {
				return 1;
			} else {
				return 40;
			}
		}
		if (itemName.equalsIgnoreCase("Archer helm")) {
			return 1;
		}
		if (itemName.contains("Studded")) {
			return 20;
		}
		if (itemName.equalsIgnoreCase("Coif")) {
			return 20;
		}
		if (itemName.contains("Frog-leather")) {
			return 25;
		}
		if (itemName.contains("Snakeskin")) {
			return 30;
		}
		if (itemName.equalsIgnoreCase("Ranger boots")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Robin hood hat")) {
			return 40;
		}
		if (itemName.startsWith("Green") && itemName.contains("hide")) {
			return 40;
		}
		if (itemName.startsWith("Blue") && itemName.contains("hide")) {
			return 50;
		}
		if (itemName.startsWith("Red") && itemName.contains("hide")) {
			return 60;
		}
		if (itemName.startsWith("Black") && itemName.contains("hide")) {
			return 70;
		}
		if (itemName.contains("Third-Age range")) {
			return 65;
		}
		if (itemName.contains("Zamorak") || itemName.contains("Saradomin") || itemName.contains("Guthix")) {
			if (itemName.contains("coif")) {
				return 70;
			}
		}
		if (itemName.contains("Karil")) {
			return 70;
		}
		if (itemName.contains("Armadyl")) {
			if (weaponType.equalsIgnoreCase("helmet") || weaponType.equalsIgnoreCase("chestplate")
					|| weaponType.equalsIgnoreCase("chainskirt")) {
				return 70;
			}
		}
		/*
		 * Crossbows
		 */
		if (itemName.equalsIgnoreCase("Crossbow")) {
			return 1;
		}
		if (weaponType.equalsIgnoreCase("crossbow")) {
			if (weaponMetal.equalsIgnoreCase("Pheonix")) {
				return 1;
			}
			if (weaponMetal.equalsIgnoreCase("Bronze")) {
				return 1;
			}
			if (weaponMetal.equalsIgnoreCase("Blurite")) {
				return 16;
			}
			if (weaponMetal.equalsIgnoreCase("Iron")) {
				return 26;
			}
			if (weaponMetal.equalsIgnoreCase("Dorgeshuun")) {
				return 28;
			}
			if (weaponMetal.equalsIgnoreCase("Steel")) {
				return 31;
			}
			if (weaponMetal.equalsIgnoreCase("Black")) {
				return 33;
			}
			if (weaponMetal.equalsIgnoreCase("Mithril")) {
				return 36;
			}
			if (weaponMetal.equalsIgnoreCase("Adamant")) {
				return 46;
			}
			if (weaponMetal.equalsIgnoreCase("Hunters'")) {
				return 50;
			}
			if (weaponMetal.equalsIgnoreCase("Rune")) {
				return 61;
			}
			if (weaponMetal.equalsIgnoreCase("Karil's")) {
				return 70;
			}
		}
		/*
		 * Salamanders
		 */
		if (itemName.equalsIgnoreCase("Swamp lizard")) {
			return 30;
		}
		if (itemName.equalsIgnoreCase("Orange salamander")) {
			return 50;
		}
		if (itemName.equalsIgnoreCase("Red salamander")) {
			return 60;
		}
		if (itemName.equalsIgnoreCase("Black salamander")) {
			return 70;
		}
		/*
		 * Other
		 */
		if (itemName.equalsIgnoreCase("Ava's attractor")) {
			return 30;
		}
		if (itemName.contains("void")) {
			return 42;
		}
		if (itemName.equalsIgnoreCase("Ava's accumulator")) {
			return 50;
		}
		if (itemName.equalsIgnoreCase("Broad arrows")) {
			return 50;
		}
		if (itemName.equalsIgnoreCase("Broad-tipped bolts")) {
			return 50;
		}
		return -1;
	}

	public int getRequiredPrayerLevel(int itemId) {
		String itemName = World.item.getName(itemId);
		itemName.replaceAll("_", " ");
		itemName.toLowerCase();
		String[] nameSplit = itemName.split(" ");
		String weaponMetal = nameSplit[0];
		String weaponType = "";
		if (nameSplit.length > 1) {
			weaponType = nameSplit[1];
		}
		if (itemName.contains("Initiate")) {
			return 10;
		}
		if (itemName.contains("Proselyte")) {
			return 20;
		}
		if (itemName.contains("Zamorak") || itemName.contains("Saradomin") || itemName.contains("Guthix")) {
			if (itemName.contains("robe")) {
				return 20;
			}
			if (itemName.contains("cloak") || itemName.contains("mitre")) {
				return 40;
			}
			if (itemName.contains("stole") || itemName.contains("crozier")) {
				return 60;
			}
		}
		if (itemName.contains("void")) {
			return 22;
		}
		if (itemName.equalsIgnoreCase("Ancient mace")) {
			return 25;
		}
		return -1;
	}

	public int getRequiredMagicLevel(int itemId) {
		String itemName = World.item.getName(itemId);
		itemName.replaceAll("_", " ");
		itemName.toLowerCase();
		if (itemName.equalsIgnoreCase("Farseer helm")) {
			return 1;
		}
		if (itemName.contains("Skeletal")) {
			if (itemName.contains("gloves") || itemName.contains("boots")) {
				return 1;
			} else {
				return 40;
			}
		}
		if (itemName.equalsIgnoreCase("Wizard boots")) {
			return 1;
		}
		if (itemName.equalsIgnoreCase("Mind helmet") || itemName.equalsIgnoreCase("Mind shield")) {
			return 30;
		}
		if (itemName.contains("Mystic")) {
			if (itemName.contains("robe") || itemName.contains("hat") || itemName.contains("gloves")
					|| itemName.contains("boots")) {
				return 40;
			}
		}
		if (itemName.contains("Enchanted") || itemName.contains("Splitbark")) {
			if (itemName.contains("hat") || itemName.contains("top") || itemName.contains("robe")
					|| itemName.contains("gauntlets") || itemName.contains("boots")) {
				return 40;
			}
		}
		if (itemName.contains("Zamorak") || itemName.contains("Saradomin") || itemName.contains("Guthix")) {
			if (itemName.contains("cloak") || itemName.contains("mitre")) {
				return 40;
			}
		}
		if (itemName.contains("void")) {
			return 42;
		}
		if (itemName.contains("Infinity")) {
			if (itemName.contains("hat") || itemName.contains("top") || itemName.contains("bottom")
					|| itemName.contains("boots") || itemName.contains("gloves")) {
				return 50;
			}
		}
		if (itemName.contains("Zamorak") || itemName.contains("Saradomin") || itemName.contains("Guthix")) {
			if (itemName.contains("cape")) {
				return 60;
			}
		}
		if (itemName.contains("Third-Age")) {
			if (itemName.contains("mage hat") || itemName.contains("robe") || itemName.contains("amulet")) {
				return 65;
			}
		}
		if (itemName.startsWith("Lunar")) {
			if (itemName.contains("helm") || itemName.contains("torso") || itemName.contains("gloves")
					|| itemName.contains("legs") || itemName.contains("boots") || itemName.contains("cape")) {
				return 65;
			}
		}
		if (itemName.startsWith("Ahrim")) {
			return 70;
		}
		if (itemName.equalsIgnoreCase("Staff") || itemName.startsWith("Staff of")
				|| itemName.equalsIgnoreCase("Magic staff")) {
			return 1;
		}
		if (itemName.contains("battlestaff")) {
			return 30;
		}
		if (itemName.contains("mystic")) {
			return 40;
		}
		if (itemName.equalsIgnoreCase("Slayer's staff")) {
			return 50;
		}
		if (itemName.equalsIgnoreCase("Iban's staff")) {
			return 50;
		}
		if (itemName.equalsIgnoreCase("Ancient staff")) {
			return 50;
		}
		if (itemName.contains("Zamorak") || itemName.contains("Saradomin") || itemName.contains("Guthix")) {
			if (itemName.contains("staff")) {
				return 60;
			}
		}
		if (itemName.equalsIgnoreCase("TokTz-Mej-Tal")) {
			return 60;
		}
		/*
		 * Books and wands
		 */
		if (itemName.equalsIgnoreCase("Beginner wand")) {
			return 45;
		}
		if (itemName.equalsIgnoreCase("Apprentice wand")) {
			return 50;
		}
		if (itemName.equalsIgnoreCase("Teacher wand")) {
			return 55;
		}
		if (itemName.equalsIgnoreCase("Master wand")) {
			return 60;
		}
		if (itemName.equalsIgnoreCase("Mage's book")) {
			return 60;
		}
		/*
		 * Salamanders
		 */
		if (itemName.equalsIgnoreCase("Swamp lizard")) {
			return 30;
		}
		if (itemName.equalsIgnoreCase("Orange salamander")) {
			return 50;
		}
		if (itemName.equalsIgnoreCase("Red salamander")) {
			return 60;
		}
		if (itemName.equalsIgnoreCase("Black salamander")) {
			return 70;
		}
		return -1;
	}

	public int getRequiredSlayerLevel(int itemId) {
		return -1;
	}

	public boolean isFullhat(int i) {
		return false;
	}

	public boolean isFullbody(int i) {
		return false;
	}

	public boolean isFullmask(int i) {
		return false;
	}

	public int getStandAnimation() {
		String weaponName = World.item.getName(id[3]);
		if (weaponName.contains("godsword") || weaponName.equalsIgnoreCase("Saradomin sword")
				|| weaponName.contains("2h")) {
			return 7047;
		}
		if (weaponName.contains("whip")) {
			return 1832;
		}
		return 0x328;
	}

	public int getRunAnimation() {
		String weaponName = World.item.getName(id[3]);
		switch (id[3]) {
		}
		if (weaponName.contains("godsword") || weaponName.equalsIgnoreCase("Saradomin sword")
				|| weaponName.contains("2h")) {
			return 7039;
		}
		if (weaponName.contains("whip")) {
			return 1661;
		}
		return 0x338;
	}

	public int getTurnAnimation() {
		String weaponName = World.item.getName(id[3]);
		switch (id[3]) {
		}
		if (weaponName.contains("godsword") || weaponName.equalsIgnoreCase("Saradomin sword")
				|| weaponName.contains("2h")) {
			return 7040;
		}
		return 0x337;
	}

	public int getWalkAnimation() {
		String weaponName = World.item.getName(id[3]);
		if (weaponName.contains("godsword") || weaponName.equalsIgnoreCase("Saradomin sword")
				|| weaponName.contains("2h")) {
			return 7046;
		}
		if (weaponName.contains("whip")) {
			return 1660;
		}
		return 0x333;
	}

	public int getTurn180Animation() {
		String weaponName = World.item.getName(id[3]);
		switch (id[3]) {
		}
		if (weaponName.contains("godsword") || weaponName.equalsIgnoreCase("Saradomin sword")
				|| weaponName.contains("2h")) {
			return 7045;
		}
		return 0x334;
	}

	public int getClockwiseTurn90Animation() {
		String weaponName = World.item.getName(id[3]);
		switch (id[3]) {
		}
		if (weaponName.contains("godsword") || weaponName.equalsIgnoreCase("Saradomin sword")
				|| weaponName.contains("2h")) {
			return 7044;
		}
		return 0x335;
	}

	public int getAntiClockwiseTurn90Animation() {
		String weaponName = World.item.getName(id[3]);
		switch (id[3]) {
		}
		if (weaponName.contains("godsword") || weaponName.equalsIgnoreCase("Saradomin sword")
				|| weaponName.contains("2h")) {
			return 7043;
		}
		return 0x336;
	}

	public void sendAttackTab() {
		player.getActionSender().sendTab(player.getEquipment().getAttackInterface(), Constants.TAB_ATTACK);
		sendAttackTabText();
	}

	public int getAttackInterface() {
		final String weapon = World.item.getName(id[3]);
		if (id[Constants.EQUIPMENT_WEAPON] == -1) {
			return 92;
		} else if (weapon.equals("Abyssal whip")) {
			return 93;
		} else if (weapon.equals("Granite maul") || weapon.equals("Tzhaar-ket-om")
				|| weapon.equals("Torags hammers")) {
			return 76;
		} else if (weapon.equals("Veracs flail") || weapon.endsWith("mace")) {
			return 88;
		} else if (weapon.endsWith("crossbow") || weapon.endsWith(" c'bow")) {
			return 79;
		} else if (weapon.endsWith("bow") || weapon.endsWith("bow full") || weapon.equals("Seercull")) {
			return 77;
		} else if (weapon.startsWith("Staff") || weapon.endsWith("staff") || weapon.equals("Toktz-mej-tal")) {
			return 90;
		} else if (weapon.endsWith("dart") || weapon.endsWith("knife") || weapon.endsWith("thrownaxe")
				|| weapon.equals("Toktz-xil-ul")) {
			return 91;
		} else if (weapon.endsWith("dagger") || weapon.endsWith("dagger(s)") || weapon.endsWith("dagger(+)")
				|| weapon.endsWith("dagger(p)")) {
			return 89;
		} else if (weapon.endsWith("pickaxe")) {
			return 83;
		} else if (weapon.endsWith("axe") || weapon.endsWith("battleaxe")) {
			return 75;
		} else if (weapon.endsWith("halberd")) {
			return 84;
		} else if (weapon.endsWith("spear") || weapon.equals("Guthans warspear")) {
			return 85;
		} else if (weapon.endsWith("claws")) {
			return 78;
		} else if (weapon.endsWith("2h sword") || weapon.endsWith("godsword") || weapon.equals("Saradomin sword")) {
			return 81;
		} else {
			return 82;
		}
	}

	public void sendAttackTabText() {
		String weaponName = World.item.getName(id[Constants.EQUIPMENT_WEAPON]);
		player.getActionSender().sendString(weaponName, getAttackInterface(), 0);
	}

	public boolean isWeapon(int itemId) {
		String itemName = World.item.getName(itemId).toLowerCase().replaceAll("_", " ");
		String[] weapons = { "Mace", "Scimitar", "Sword", "Axe", "Spear", "Dagger", "Bow", "Knife", "Staff",
				"Flail", " hammers", "Warhammer", "Dart", "Javelin", "Halberd", "c'bow", "Maul" };
		String[] items = { "Abyssal whip" };
		for (String weapon : weapons) {
			if (itemName.contains(weapon.toLowerCase())) {
				return true;
			}
		}
		for (String item : items) {
			if (itemName.equalsIgnoreCase(item)) {
				return true;
			}
		}
		return false;
	}

	public boolean isHat(int itemId) {
		String itemName = World.item.getName(itemId).toLowerCase().replaceAll("_", " ");
		String[] hats = { "partyhat" };
		String[] items = { "Hat" };
		for (String hat : hats) {
			if (itemName.contains(hat.toLowerCase())) {
				return true;
			}
		}
		for (String item : items) {
			if (itemName.equalsIgnoreCase(item)) {
				return true;
			}
		}
		return false;
	}

	public boolean isShield(int itemId) {
		String itemName = World.item.getName(itemId).toLowerCase().replaceAll("_", " ");
		String[] shields = { "defender", "shield" };
		String[] items = { "" };
		for (String shield : shields) {
			if (itemName.contains(shield.toLowerCase())) {
				return true;
			}
		}
		for (String item : items) {
			if (itemName.equalsIgnoreCase(item)) {
				return true;
			}
		}
		return false;
	}

	public int getEquipmentSlot(int wearItem) {
		if (isHat(wearItem)) {
			return Constants.EQUIPMENT_HAT;
		}
		if (isWeapon(wearItem)) {
			return Constants.EQUIPMENT_WEAPON;
		}
		if (isShield(wearItem)) {
			return Constants.EQUIPMENT_SHIELD;
		}
		return -1;
	}

	public int getRequirement(int skill, int wearItem) {
		switch (skill) {
		case Constants.SKILL_ATTACK:
			return getRequiredAttackLevel(wearItem);
		case Constants.SKILL_DEFENCE:
			return getRequiredDefenceLevel(wearItem);
		case Constants.SKILL_STRENGTH:
			return getRequiredStrengthLevel(wearItem);
		case Constants.SKILL_RANGED:
			return getRequiredRangedLevel(wearItem);
		case Constants.SKILL_PRAYER:
			return getRequiredPrayerLevel(wearItem);
		case Constants.SKILL_MAGIC:
			return getRequiredMagicLevel(wearItem);
		}
		return -1;
	}

	public int getRequirementCount(int wearItem) {
		int number = 0;
		if (getRequiredAttackLevel(wearItem) > 0) {
			number++;
			multipleRequirements[Constants.SKILL_ATTACK] = true;
		}
		if (getRequiredDefenceLevel(wearItem) > 0) {
			number++;
			multipleRequirements[Constants.SKILL_DEFENCE] = true;
		}
		if (getRequiredStrengthLevel(wearItem) > 0) {
			number++;
			multipleRequirements[Constants.SKILL_STRENGTH] = true;
		}
		if (getRequiredRangedLevel(wearItem) > 0) {
			number++;
			multipleRequirements[Constants.SKILL_RANGED] = true;
		}
		if (getRequiredPrayerLevel(wearItem) > 0) {
			number++;
			multipleRequirements[Constants.SKILL_PRAYER] = true;
		}
		if (getRequiredMagicLevel(wearItem) > 0) {
			number++;
			multipleRequirements[Constants.SKILL_MAGIC] = true;
		}
		return number;
	}

	public boolean canWear(int wearItem) {
		String message1 = "", message2 = "";
		int firstReq = -1, secondReq = -1;
		if (getRequirementCount(wearItem) > 1) {
			if (firstReq == -1) {
				for (int i = 0; i < multipleRequirements.length; i++) {
					if (multipleRequirements[i]) {
						firstReq = i;
						break;
					}
				}
			}
			if (secondReq == -1) {
				for (int i = 0; i < multipleRequirements.length; i++) {
					if (multipleRequirements[i]) {
						if (i != firstReq) {
							secondReq = i;
							break;
						}
					}
				}
			}
			if (getRequirement(firstReq, wearItem) == getRequirement(secondReq, wearItem)) {
				if (player.getSkills().getLevel(firstReq) < getRequirement(firstReq, wearItem)
						|| player.getSkills().getLevel(secondReq) < getRequirement(secondReq, wearItem)) {
					/*
					 * We need to organize the message strings!
					 */
					String firstRequirement = player.getSkills().getSkillName(firstReq);
					String secondRequirement = player.getSkills().getSkillName(secondReq);
					int levelRequirement = getRequirement(firstReq, wearItem);
					/*
					 * Much neater version =D
					 */
					message1 = "You are not a high enough level to use this item.";
					message2 = "You need to have " + firstRequirement + " and " + secondRequirement
							+ " levels of " + levelRequirement + ".";
					player.getActionSender().msg(message1);
					player.getActionSender().msg(message2);
					return false;
				}
			} else {
				if (player.getSkills().getLevel(firstReq) < getRequirement(firstReq, wearItem)
						|| player.getSkills().getLevel(secondReq) < getRequirement(secondReq, wearItem)) {
					/*
					 * We need to organize the message strings!
					 */
					String a = (player.getSkills().getSkillName(firstReq).startsWith("a") ? "an" : "a");
					String firstRequirement = "" + a + " " + player.getSkills().getSkillName(firstReq)
							+ " level of " + getRequirement(firstReq, wearItem) + "";
					String secondRequirement = "" + a + " " + player.getSkills().getSkillName(secondReq)
							+ " level of " + getRequirement(secondReq, wearItem) + "";
					/*
					 * Organized...
					 */
					message1 = "You are not a high enough level to use this item.";
					message2 = "You need to have " + firstRequirement + " and " + secondRequirement + ".";
					player.getActionSender().msg(message1);
					player.getActionSender().msg(message2);
					return false;
				}
			}
		} else {
			if (!getQuestRequirement(wearItem).isEmpty()) {
				player.getActionSender().msg("You have not earned the right to wear this yet.");
				player.getActionSender()
						.msg("You need to complete the " + getQuestRequirement(wearItem) + " quest first.");
				return false;
			}
			if (player.getSkills().getLevel(Constants.SKILL_ATTACK) < getRequiredAttackLevel(wearItem)) {
				player.getActionSender().msg("You are not a high enough level to use this item.");
				player.getActionSender().msg("You need to have an attack level of "
						+ getRequiredAttackLevel(wearItem) + " to equip this item.");
				return false;
			}
			if (player.getSkills().getLevel(Constants.SKILL_STRENGTH) < getRequiredStrengthLevel(wearItem)) {
				player.getActionSender().msg("You are not a high enough level to use this item.");
				player.getActionSender().msg("You need to have a strength level of "
						+ getRequiredAttackLevel(wearItem) + " to equip this item.");
				return false;
			}
			if (player.getSkills().getLevel(Constants.SKILL_DEFENCE) < getRequiredAttackLevel(wearItem)) {
				player.getActionSender().msg("You are not a high enough level to use this item.");
				player.getActionSender().msg("You need to have a defence level of "
						+ getRequiredAttackLevel(wearItem) + " to equip this item.");
				return false;
			}
		}
		return true;
	}

	public void add(int slot, int item) {
		id[slot] = item;
		amount[slot] = 1;
		refresh();
	}

	public void add(int slot, int wearItem, int wearItemAmount) {
		id[slot] = wearItem;
		amount[slot] += wearItemAmount;
		refresh();
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
		player.getActionSender().sendItem(387, 28, 94, id, amount);
	}

}
