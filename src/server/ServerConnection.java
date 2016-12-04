package server;

import java.io.*;
import java.net.*;

import gui.ControllerInterface;

public class ServerConnection {

	private ServerSocket serverSock = null;
	private Socket clientSock = null;
	private ServerPort serverPort = null;
	private boolean active;
	
	public ServerConnection(ServerPort serverPort){
		this.serverPort=serverPort;
		active=false;
	}
	
	public void wait(ChatServerList list,ControllerInterface contrInterf) {
		
		//Istanzia il server socket
		try {
			serverSock = new ServerSocket(serverPort.getCurrentPort());
		} catch (IOException e1) {e1.printStackTrace();}
		
		ChatServer cs = null;
		
		while(true) {
			try {
				System.out.println("Server in attesa sulla porta "+serverPort.getCurrentPort());
				clientSock = serverSock.accept();
				cs = new ChatServer(clientSock, list,contrInterf);
				cs.start();
			} catch (IOException e) {
				e.printStackTrace();
				try {
					serverSock.close();
				} catch (IOException ioe) {ioe.printStackTrace();}
			}
		}
	}
}
