package com.czar.czarempire.model.player;

public class PlayerAppearance {

	public PlayerAppearance() {
		look[1] = 10;
		look[2] = 18;
		look[3] = 26;
		look[4] = 33;
		look[5] = 36;
		look[6] = 42;
	}

	public int gender;
	public int skullIcon = -1;
	public int prayerIcon = -1;
	public int[] color = new int[5];
	public int[] look = new int[7];

}
