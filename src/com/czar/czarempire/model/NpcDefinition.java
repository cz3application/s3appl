package com.czar.czarempire.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.czar.czarempire.Constants;

public class NpcDefinition {

	public NpcDefinition() {
		loadSpawnList();
		System.out.println();
		System.out.println("Spawn list loaded.");
	}

	private boolean loadSpawnList() {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader(Constants.DIR_NPC_SPAWN));
		} catch (final FileNotFoundException fileex) {
			System.err.println(Constants.DIR_NPC_SPAWN + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (final IOException ioexception) {
			System.err.println(Constants.DIR_NPC_SPAWN + ": error loading file.");
			return false;
		}
		while ((EndOfFile == false) && (line != null)) {
			line = line.trim();
			final int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("spawn")) {
					World.addNpc(Integer.parseInt(token3[0]), Integer.parseInt(token3[1]),
							Integer.parseInt(token3[2]), Integer.parseInt(token3[3]));
				}
			} else {
				if (line.equals("[ENDOFSPAWNLIST]")) {
					try {
						characterfile.close();
					} catch (final IOException ioexception) {
					}
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (final IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (final IOException ioexception) {
		}
		return false;
	}

}
