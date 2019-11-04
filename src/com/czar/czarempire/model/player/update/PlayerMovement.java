package com.czar.czarempire.model.player.update;

import com.czar.czarempire.Constants;
import com.czar.czarempire.model.player.Player;
import com.czar.czarempire.net.Stream;

public class PlayerMovement {
	private Player player;

	public PlayerMovement(Player p) {
		this.player = p;
	}

	private boolean running;
	public int wQueueWritePtr, wQueueReadPtr;
	public int walkingQueueSize = 50;
	public int[] walkingQueueX = new int[walkingQueueSize];
	public int[] walkingQueueY = new int[walkingQueueSize];
	public int[] walkingQueue = new int[walkingQueueSize];

	public void handle() {
		player.setMapRegionChanged(false);
		player.setDidTeleport(false);
		player.setWalkDirection(-1);
		player.setRunDirection(-1);
		if (player.getTeleportTo().getX() != -1
				&& player.getTeleportTo().getY() != -1) {
			player.setMapRegionChanged(true);
			if (player.getRegionX() != -1 && player.getRegionY() != -1) {
				int relX = player.getTeleportTo().getX() - player.getRegionX()
						* 8;
				int relY = player.getTeleportTo().getY() - player.getRegionY()
						* 8;
				if (relX >= 2 * 8 && relX < 11 * 8 && relY >= 2 * 8
						&& relY < 11 * 8) {
					player.setMapRegionChanged(false);
				}
			}
			if (player.didRegionChange()) {
				player.setRegionX((player.getTeleportTo().getX() >> 3));
				player.setRegionY((player.getTeleportTo().getY() >> 3));
			}
			player.setLocalX(player.getTeleportTo().getX() - 8
					* (player.getRegionX() - 6));
			player.setLocalY(player.getTeleportTo().getY() - 8
					* (player.getRegionY() - 6));
			player.getAbs().setX(player.getTeleportTo().getX());
			player.getAbs().setY(player.getTeleportTo().getY());
			reset();
			player.getTeleportTo().reset();
			player.setDidTeleport(true);
		} else {
			player.setWalkDirection(getNextWalkingDirection());
			if (player.getWalkDirection() == -1) {
				return;
			}
			if (isRunning()) {
				player.setRunDirection(getNextWalkingDirection());
			}
			if (player.getLocalX() < 2 * 8) {
				player.setMapRegionChanged(true);
			} else if (player.getLocalX() >= 11 * 8) {
				player.setMapRegionChanged(true);
			}
			if (player.getLocalY() < 2 * 8) {
				player.setMapRegionChanged(true);
			} else if (player.getLocalY() >= 11 * 8) {
				player.setMapRegionChanged(true);
			}
			if (player.didRegionChange()) {
				player.getTeleportTo().setX(player.getAbs().getX());
				player.getTeleportTo().setY(player.getAbs().getY());
			}
		}
	}

	public void reset() {
		if (walkingQueueX == null || walkingQueueY == null) {
			return;
		}
		walkingQueueX[0] = player.getLocalX();
		walkingQueueY[0] = player.getLocalY();
		walkingQueue[0] = -1;
		wQueueReadPtr = wQueueWritePtr = 1;
	}

	public int getNextWalkingDirection() {
		if (wQueueReadPtr == wQueueWritePtr) {
			return -1;
		}
		int dir = walkingQueue[wQueueReadPtr++];
		player.setLocalX(player.getLocalX() + Constants.DIR_DELTA_X[dir]);
		player.setLocalY(player.getLocalY() + Constants.DIR_DELTA_Y[dir]);
		player.getAbs().setX(
				player.getAbs().getX() + Constants.DIR_DELTA_X[dir]);
		player.getAbs().setY(
				player.getAbs().getY() + Constants.DIR_DELTA_Y[dir]);
		return dir;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void updateThisPlayerMovement(Player p, Stream str) {
		if (p == null || str == null) {
			return;
		}
		if (player.didRegionChange()) {
			player.getActionSender().sendRegion();
		}
		if (player.didTeleport()) {
			player.getActionSender().teleport();
			return;
		}
		if (player.getWalkDirection() == -1) {
			player.getActionSender().noMovement();
		} else {
			player.getActionSender().updateMovement();
		}
	}

	public void updatePlayerMovement(Stream str) {
		if (player == null || str == null) {
			return;
		}
		if (player.getWalkDirection() == -1) {
			if (player.isUpdateRequired()) {
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
		} else if (player.getRunDirection() == -1) {
			str.writeBits(1, 1);
			str.writeBits(2, 1);
			str.writeBits(3, player.getWalkDirection());
			str.writeBits(1, player.isUpdateRequired() ? 1 : 0);
		} else {
			str.writeBits(1, 1);
			str.writeBits(2, 2);
			str.writeBits(3, player.getWalkDirection());
			str.writeBits(3, player.getRunDirection());
			str.writeBits(1, player.isUpdateRequired() ? 1 : 0);
		}
	}

	public void addStepToWalkingQueue(int x, int y, Player p) {
		if (p == null) {
			return;
		}
		int diffX = x - walkingQueueX[wQueueWritePtr - 1], diffY = y
				- walkingQueueY[wQueueWritePtr - 1];
		int dir = calculateDirection(diffX, diffY);
		if (wQueueWritePtr >= walkingQueueSize) {
			return;
		}
		if (dir != -1) {
			walkingQueueX[wQueueWritePtr] = x;
			walkingQueueY[wQueueWritePtr] = y;
			walkingQueue[wQueueWritePtr++] = dir;
		}
	}

	public void addToWalkingQueue(Player p, int x, int y) {
		int diffX = x - walkingQueueX[wQueueWritePtr - 1], diffY = y
				- walkingQueueY[wQueueWritePtr - 1];
		int max = Math.max(Math.abs(diffX), Math.abs(diffY));
		for (int i = 0; i < max; i++) {
			if (diffX < 0) {
				diffX++;
			} else if (diffX > 0) {
				diffX--;
			}
			if (diffY < 0) {
				diffY++;
			} else if (diffY > 0) {
				diffY--;
			}
			addStepToWalkingQueue(x - diffX, y - diffY, p);
		}
	}

	public int calculateDirection(int dx, int dy) {
		if (dx < 0) {
			if (dy < 0) {
				return 5;
			} else if (dy > 0) {
				return 0;
			} else {
				return 3;
			}
		} else if (dx > 0) {
			if (dy < 0) {
				return 7;
			} else if (dy > 0) {
				return 2;
			} else {
				return 4;
			}
		} else {
			if (dy < 0) {
				return 6;
			} else if (dy > 0) {
				return 1;
			} else {
				return -1;
			}
		}
	}

}
