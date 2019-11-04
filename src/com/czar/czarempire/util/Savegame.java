package com.czar.czarempire.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import com.czar.czarempire.model.player.Player;
import com.czar.czarempire.net.Stream;

public class Savegame {
	/**
	 * Byte buffer for storing bytes to be loaded or saved.
	 */
	private final Stream stream = new Stream(2000, 2000);

	/**
	 * Loads a character file.
	 * 
	 * @param player
	 *                     The player to save.
	 */
	public void loadCharacter(Player player) {
		stream.inOffset = 0;
		try {
			FileInputStream in = new FileInputStream("./data/characters/" + player.getName() + ".txt");
			in.read(stream.inBuffer);
			in.close();
			in = null;
		} catch (final Exception e) {
			return;
		}
		BufferedReader r;
		try {
			r = new BufferedReader(new FileReader("./data/characters/" + player.getName() + ".txt"));
			String line;
			try {
				while ((line = r.readLine()) != null && !line.equals("null")) {
					if (line.startsWith("password:")) {
						player.setPassword(line.substring(9));
					} else if (line.startsWith("rights:")) {
						player.getRank().set(Integer.parseInt(line.substring(7)));
					} else if (line.startsWith("absx:")) {
						player.getTeleportTo().setX(Integer.parseInt(line.substring(5)));
					} else if (line.startsWith("absy:")) {
						player.getTeleportTo().setY(Integer.parseInt(line.substring(5)));
					}
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves a character file.
	 * 
	 * @param player
	 *                     The player to save.
	 */
	public void saveCharacter(Player player) throws Exception {
		if (player == null) {
			return;
		}
		final String newline = System.getProperty("line.separator");
		stream.outOffset = 0;
		stream.writeString2("username:" + player.getName());
		stream.writeString2(newline);
		stream.writeString2("password:" + player.getPassword());
		stream.writeString2(newline);
		stream.writeString2("rights:" + player.getRank().get());
		stream.writeString2(newline);
		stream.writeString2("absx:" + player.getAbs().getX());
		stream.writeString2(newline);
		stream.writeString2("absy:" + player.getAbs().getY());
		stream.writeString2(newline);
		stream.writeString2("height:" + player.getAbs().getZ());
		stream.writeString2(newline);
		stream.writeString2("null");
		FileOutputStream out = new FileOutputStream("./data/characters/" + player.getName() + ".txt");
		out.write(stream.outBuffer, 0, stream.outOffset);
		out.flush();
		out.close();
		out = null;
	}
}