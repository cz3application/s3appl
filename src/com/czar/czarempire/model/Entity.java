package com.czar.czarempire.model;

public abstract class Entity {

	private String name;
	protected int combatLevel;
	private int index;
	private int clientIndex;
	private boolean mapRegionChanged;
	private boolean didTeleport;
	private int walkDirection, runDirection;
	private Coordinate abs = new Coordinate(0, 0, 0);
	private Coordinate teleportTo = new Coordinate(0, 0, 0);
	private int regionX, regionY;
	private int localX, localY;
	private boolean updateRequired;
	private boolean appearanceUpdateRequired;
	private boolean chatUpdateRequired;

	public abstract void handleMovement();

	public void clearUpdateFlags() {
		setUpdateRequired(false);
		setAppearanceUpdateRequired(false);
		setChatUpdateRequired(false);
	}

	public boolean withinDistance(Entity p, Entity npc) {
		if (npc != null && p != null) {
			if (p.getAbs().getZ() != npc.getAbs().getZ()) {
				return false;
			}
			final int deltaX = npc.getAbs().getX() - p.getAbs().getX(),
					deltaY = npc.getAbs().getY() - p.getAbs().getY();
			return (deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16);
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract int getCombatLevel();

	public void setCombatLevel(int combatLevel) {
		this.combatLevel = combatLevel;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getClientIndex() {
		return clientIndex;
	}

	public void setClientIndex(int clientIndex) {
		this.clientIndex = clientIndex;
	}

	public void setMapRegionChanged(boolean mapRegionChanged) {
		this.mapRegionChanged = mapRegionChanged;
	}

	public boolean didRegionChange() {
		return mapRegionChanged;
	}

	public void setDidTeleport(boolean didTeleport) {
		this.didTeleport = didTeleport;
	}

	public boolean didTeleport() {
		return didTeleport;
	}

	public void setWalkDirection(int walkDirection) {
		this.walkDirection = walkDirection;
	}

	public int getWalkDirection() {
		return walkDirection;
	}

	public void setRunDirection(int runDirection) {
		this.runDirection = runDirection;
	}

	public int getRunDirection() {
		return runDirection;
	}

	public void setRegionX(int regionX) {
		this.regionX = regionX;
	}

	public int getRegionX() {
		return regionX;
	}

	public void setRegionY(int regionY) {
		this.regionY = regionY;
	}

	public int getRegionY() {
		return regionY;
	}

	public void setLocalX(int localX) {
		this.localX = localX;
	}

	public int getLocalX() {
		return localX;
	}

	public void setLocalY(int localY) {
		this.localY = localY;
	}

	public int getLocalY() {
		return localY;
	}

	public void setAbs(Coordinate abs) {
		this.abs = abs;
	}

	public Coordinate getAbs() {
		return abs;
	}

	public void setTeleportTo(Coordinate teleportTo) {
		this.teleportTo = teleportTo;
	}

	public Coordinate getTeleportTo() {
		return teleportTo;
	}

	public boolean setUpdateRequired(boolean updateRequired) {
		this.updateRequired = updateRequired;
		return updateRequired;
	}

	public boolean isUpdateRequired() {
		return updateRequired;
	}

	public void setAppearanceUpdateRequired(boolean appearanceUpdateRequired) {
		this.appearanceUpdateRequired = appearanceUpdateRequired;
	}

	public boolean isAppearanceUpdateRequired() {
		return appearanceUpdateRequired;
	}

	public void setChatUpdateRequired(boolean chatUpdateRequired) {
		this.chatUpdateRequired = chatUpdateRequired;
	}

	public boolean isChatUpdateRequired() {
		return chatUpdateRequired;
	}

}
