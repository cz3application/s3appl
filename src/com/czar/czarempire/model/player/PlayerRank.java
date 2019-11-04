package com.czar.czarempire.model.player;

import com.czar.czarempire.util.Rank;

public class PlayerRank {

	private int rank;

	public void set(Rank rank) {
		this.rank = rank.get();
	}

	public void set(int rank) {
		this.rank = rank;
	}

	public int get() {
		return rank;
	}

}
