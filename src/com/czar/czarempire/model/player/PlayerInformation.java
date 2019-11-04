package com.czar.czarempire.model.player;

public class PlayerInformation {
	private Player player;
	private String chat;
	private int muteDuration;
	private int chatEffects;

	public PlayerInformation(Player player) {
		this.setPlayer(player);
	}

	public void setMuteDuration(int muteDuration) {
		this.muteDuration = muteDuration;
	}

	public int getMuteDuration() {
		return muteDuration;
	}

	public boolean isPermanentlyMuted() {
		if (muteDuration == -2) {
			return true;
		}
		return false;
	}

	public void setChatEffects(int chatEffects) {
		this.chatEffects = chatEffects;
	}

	public int getChatEffects() {
		return chatEffects;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	public String getChat() {
		return chat;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *                     the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

}
