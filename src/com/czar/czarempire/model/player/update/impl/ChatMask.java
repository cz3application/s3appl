package com.czar.czarempire.model.player.update.impl;

import com.czar.czarempire.model.Entity;
import com.czar.czarempire.model.EntityUpdateMask;
import com.czar.czarempire.model.player.Player;
import com.czar.czarempire.net.Stream;
import com.czar.czarempire.util.Text;

public class ChatMask implements EntityUpdateMask {

	@Override
	public void appendMask(Entity e, Stream str) {
		if (str == null) {
			return;
		}
		Player p = (Player) e;
		str.writeWordBigEndianA(p.getInformation().getChatEffects());
		str.writeByteA(p.getRank().get());
		Stream chatStream = new Stream(256, 256);
		chatStream.writeByte(p.getInformation().getChat().length());
		int encryptedBytes = Text.encryptClientString(chatStream.outBuffer, 0, chatStream.outOffset,
				p.getInformation().getChat().length(), p.getInformation().getChat().getBytes());
		chatStream.outOffset += encryptedBytes;
		str.writeByteS(chatStream.outOffset);
		str.writeBytes(chatStream.outBuffer, chatStream.outOffset, 0);
		chatStream = null;
	}

}
