package com.czar.czarempire;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.czar.czarempire.model.World;

public class Server {
	private World world;

	public static void main(String[] args) {
		System.out.println("- Arthius framework -");
		System.out.print("Launching server...");
		new Server();
	}

	public Server() {
		bind();
		world = new World(this);
	}

	public void bind() {
		System.out.print("ready!");
		System.out.println();
		System.out.print("Launching listener...");
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
				listen(), 0, Constants.LISTENER_TICK_RATE,
				TimeUnit.MILLISECONDS);
		System.out.print("ready!");
	}

	public Runnable listen() {
		return new Runnable() {
			@Override
			public void run() {
				try {
					ServerSocket listener = new ServerSocket(Constants.PORT);
					System.out.println();
					System.out.println("Listener bound to port "
							+ Constants.PORT + ".");
					System.out.println();
					System.out.println();
					while (true) {
						Socket socket = null;
						if (listener != null) {
							socket = listener.accept();
						} else {
							System.err
									.println("NO LISTENER!! FFFFFFUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
						}
						System.out.println("Available connection from: "
								+ socket.getInetAddress().getHostAddress());
						world.registerConnection(socket);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		};
	}

}
