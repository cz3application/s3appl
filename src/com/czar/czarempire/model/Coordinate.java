package com.czar.czarempire.model;

public class Coordinate {

	private int x, y, z;

	public Coordinate(int x, int y, int z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	public Coordinate(int x, int y) {
		setX(x);
		setY(y);
	}

	public void reset() {
		setX(-1);
		setY(-1);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

}