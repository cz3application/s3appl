package com.czar.czarempire.model.player;

import java.net.Socket;

import com.czar.czarempire.model.Entity;
import com.czar.czarempire.model.World;
import com.czar.czarempire.model.npc.Npc;
import com.czar.czarempire.model.player.update.PlayerMovement;
import com.czar.czarempire.model.player.update.PlayerUpdate;
import com.czar.czarempire.net.ActionSender;
import com.czar.czarempire.net.Stream;

public class Player extends Entity {
	private Socket socket;
	private String password;
	private boolean loggedIn;
	private int loginStage;
	private PlayerRank rank;
	private ActionSender actionSender;
	private PlayerMovement movement;
	private PlayerUpdate update;
	private PlayerAppearance appearance;
	private PlayerEquipment equipment;
	private PlayerInventory inventory;
	private PlayerInformation information;
	private PlayerLevel skills;
	private Stream streamBuffer = new Stream(5000, 5000);
	public boolean[] disconnected = new boolean[2];
	public int playerListSize;
	public Player[] playerList = new Player[World.players.length];
	public byte[] playersInList = new byte[World.players.length];
	public Npc[] npcList = new Npc[World.npcs.length];
	public byte[] npcsInList = new byte[World.npcs.length];
	public int npcListSize = 0;
	public boolean rebuildNpcList;

	public Player(Socket socket2, int socketId) {
		setSocket(socket2);
		setIndex(socketId);
		setRank(new PlayerRank());
		setAppearance(new PlayerAppearance());
		setUpdate(new PlayerUpdate());
		setMovement(new PlayerMovement(this));
		setActionSender(new ActionSender(this));
		setEquipment(new PlayerEquipment(this));
		setInventory(new PlayerInventory(this));
		setInformation(new PlayerInformation(this));
		setSkills(new PlayerLevel(this));
	}

	public void tick() {
		if (disconnected[0]) {
			disconnected[1] = true;
		}
	}

	@Override
	public void handleMovement() {
		getMovement().handle();
	}

	public void update() {
		getUpdate().updatePlayer(this, getStreamBuffer());
	}

	@Override
	public int getCombatLevel() {
		return getSkills().getCombatLevel();
	}

	public void end() {
		setSocket(null);
		setRank(null);
		setAppearance(null);
		setUpdate(null);
		setMovement(null);
		setActionSender(null);
		setEquipment(null);
		setInventory(null);
		setInformation(null);
		clearUpdateFlags();
		getAbs().reset();
		getTeleportTo().reset();
		setRegionX(-1);
		setRegionY(-1);
		setLocalX(-1);
		setLocalY(-1);
		System.out.println("Player ended.");
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public Stream getStreamBuffer() {
		return streamBuffer;
	}

	public void setLoginStage(int loginStage) {
		this.loginStage = loginStage;
	}

	public int getLoginStage() {
		return loginStage;
	}

	public void setRank(PlayerRank rank) {
		this.rank = rank;
	}

	public PlayerRank getRank() {
		if (rank == null) {
			rank = new PlayerRank();
		}
		return rank;
	}

	public void setActionSender(ActionSender actionSender) {
		this.actionSender = actionSender;
	}

	public ActionSender getActionSender() {
		return actionSender;
	}

	public void setMovement(PlayerMovement movement) {
		this.movement = movement;
	}

	public PlayerMovement getMovement() {
		if (movement == null) {
			System.err.println("Movement is null");
			movement = new PlayerMovement(this);
		}
		return movement;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setUpdate(PlayerUpdate update) {
		this.update = update;
	}

	public PlayerUpdate getUpdate() {
		if (update == null) {
			System.err.println("Update is null");
			update = new PlayerUpdate();
		}
		return update;
	}

	public void setAppearance(PlayerAppearance appearance) {
		this.appearance = appearance;
	}

	public PlayerAppearance getAppearance() {
		return appearance;
	}

	public void setEquipment(PlayerEquipment equipment) {
		this.equipment = equipment;
	}

	public PlayerEquipment getEquipment() {
		return equipment;
	}

	public void setInventory(PlayerInventory inventory) {
		this.inventory = inventory;
	}

	public PlayerInventory getInventory() {
		if (inventory == null) {
			System.err.println("PlayerInventory is null");
			inventory = new PlayerInventory(this);
		}
		return inventory;
	}

	public void setInformation(PlayerInformation information) {
		this.information = information;
	}

	public PlayerInformation getInformation() {
		return information;
	}

	public void setSkills(PlayerLevel skills) {
		this.skills = skills;
	}

	public PlayerLevel getSkills() {
		return skills;
	}

}
