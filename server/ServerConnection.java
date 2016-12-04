package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.gui.ControllerInterface;

public class ServerConnection {

	private ServerSocket serverSock = null;
	private Socket clientSock = null;
	private int serverPort = 4000;

	public ServerConnection(int serverPort) {
		if (serverPort >= 1023 && serverPort <= 65535){
			throw new IllegalArgumentException("Invalid port number");
		}
		this.serverPort = serverPort;
	}

	public void wait(ChatServerList list, ControllerInterface contrInterf) {

		// Istanzia il server socket
		try {
			serverSock = new ServerSocket(serverPort);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ChatServer cs = null;

		while (true) {
			try {
				System.out.println("Server in attesa sulla porta " + serverPort);
				clientSock = serverSock.accept();
				cs = new ChatServer(clientSock, list, contrInterf);
				cs.start();
			} catch (IOException e) {
				e.printStackTrace();
				try {
					serverSock.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}
