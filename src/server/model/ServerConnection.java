package server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection implements Runnable {
	private int serverPort = -1;
	private ServerSocket serverSock;
	private ChatServerList list;
	private Thread running;
	
	public ServerConnection(int serverPort, ChatServerList list) {
		this(list);
		this.setPort(serverPort);
		this.serverPort = serverPort;
	}

	public ServerConnection(ChatServerList list) {
		if (list == null) {
			throw new IllegalArgumentException("ChatServerList can't be null");
		}
		this.list = list;
	}

	public void run() {
		System.out.println("new ServerConnector running!"); // TODO: remove
		try {
			this.acceptConnections();
			serverSock.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void acceptConnections() throws IOException {
		this.serverSock = new ServerSocket(this.serverPort);
		Socket sock;
		while (true) {
			sock = serverSock.accept();
			new ChatServer(sock, list).start();
		}
	}

	public void start() {
		if ((running == null || running.isInterrupted()) && portIsSet()) {
			running = new Thread(this);
			running.start();
		} else{
			throw new IllegalStateException("ServerConnection can't be started port isn't set");
		}
	}

	public void stop() {
		if (running != null && !running.isInterrupted()) {
			running.interrupt();
			System.out.println("ServerConnector stopped");
		}
	}

	public void setPort(int serverPort) {
		if (this.running != null && !this.running.isInterrupted()) {
			throw new IllegalStateException("serverPort can't be changed when ConnectionServer is running");
		}
		if (serverPort < 1024 || serverPort > 65535) {
			throw new IllegalArgumentException("Invalid port number");
		}
		this.serverPort = serverPort;
	}

	public boolean portIsSet() {
		return serverPort != -1;
	}
}
