package server;

import gui.*;

public class MainServer {

	public static void main(String argv[]){
		ServerPort serverPort  = new ServerPort();
		Interface gui = new Interface();
		ServerConnection conServ = new ServerConnection(serverPort);	
		ControllerInterface contrInterf = new ControllerInterface(gui);
		ChatServerList list = new ChatServerList(contrInterf);
		
		conServ.wait(list,contrInterf);
	}

}
