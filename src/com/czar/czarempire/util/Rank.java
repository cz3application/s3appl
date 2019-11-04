package com.czar.czarempire.util;

public enum Rank {
	REGULAR_PLAYER(0), MODERATOR(1), ADMINISTRATOR(2);

	int rank = 0;

	Rank(int rank) {
		this.rank = rank;
	}

	public int get() {
		return rank;
	}

	public int set(Rank level) {
		return rank = level.rank;
	}

	public int set(int id) {
		return rank = id;
	}
}