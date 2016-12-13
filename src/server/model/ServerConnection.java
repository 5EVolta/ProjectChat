package server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//TODO: use a thread pls boi
public class ServerConnection {

	private int serverPort = -1;
	private ChatServerList list;
	private Runnable serverConnector;
	private Thread running; 
	

	public ServerConnection(ChatServerList list) {
		if (list == null) {
			throw new IllegalArgumentException("ChatServerList can't be null");
		}
		this.list = list;
		
		serverConnector = () -> {
			System.out.println("new ServerConnector running!"); //TODO: remove
			ServerSocket serverSock = null;
			try {
				serverSock = new ServerSocket(this.serverPort);
				Socket sock;
				while(true){
					sock = serverSock.accept();
					new ChatServer(sock, list).start();
				}
			} catch (IOException e) {
				e.printStackTrace();
				try {
					serverSock.close();
				} catch (IOException | NullPointerException e1) {
					e1.printStackTrace();
				}
			}
		};
	}

	public ServerConnection(int serverPort, ChatServerList list) {
		this(list);
		this.setPort(serverPort);
		this.serverPort = serverPort;
	}

	public void start() {
		if(running == null || !running.isInterrupted()){
			running = new Thread(serverConnector);
			running.start();
		}
	}

	public void stop() {
		if(running != null){
			running.interrupt();
			running = null;
			System.out.println("ServerConnector stopped");
		}
	}
	
	private void setPort(int serverPort) {
		if (serverPort < 1024 || serverPort > 65535) {
			throw new IllegalArgumentException("Invalid port number");
		}
		this.serverPort = serverPort;
	}

	public void changePort(int serverPort) {
		if (this.running != null && this.running.isAlive()) {
			throw new IllegalStateException("serverPort can't be changed when ConnectionServer is running");
		}
		this.setPort(serverPort);
	}
	
	public boolean portIsSet(){
		return serverPort != -1;
	}
}
