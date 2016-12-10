package server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection {

	private ServerSocket serverSock = null;
	private Socket clientSock = null;
	private int serverPort = 4000;
	private ChatServerList list;
	private boolean isRunning;

	public ServerConnection(ChatServerList list){
		if(list == null){
			throw new IllegalArgumentException("ChatServerList can't be null");
		}
		this.list = list;
	}
	
	public ServerConnection(int serverPort, ChatServerList list) {
		this(list);
		this.setPort(serverPort);
		this.serverPort = serverPort;
	}
	
	private void setPort(int serverPort){
		if (serverPort < 1024 || serverPort > 65535){
			throw new IllegalArgumentException("Invalid port number");
		}
		this.serverPort = serverPort;
	}

	public void start() {

		// Istanzia il server socket
		try {
			serverSock = new ServerSocket(serverPort);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ChatServer cs = null;
		isRunning = true;
		while (isRunning) {
			try {
				System.out.println("Server in attesa sulla porta " + serverPort);
				clientSock = serverSock.accept();
				cs = new ChatServer(clientSock, list);
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
	
	public void stop(){
		this.isRunning = false;
		try {
			this.serverSock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changePort(int serverPort){
		if(this.isRunning){
			throw new IllegalStateException("serverPort can't be changed when ConnectionServer is running");
		}
		this.setPort(serverPort);
	}
}
