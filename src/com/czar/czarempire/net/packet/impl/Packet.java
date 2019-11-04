package com.czar.czarempire.net.packet.impl;

import com.czar.czarempire.model.player.Player;

public interface Packet {

	void append(Player player, int id, int len);

}
