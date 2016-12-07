package server;

import server.gui.ControllerInterface;
import server.gui.Interface;

public class ServerMain {

	public static void main(String arg[]){
		Interface gui = new Interface();
		//ServerConnection conServ = new ServerConnection(Integer.parseInt(arg[0]));	
		ServerConnection conServ = new ServerConnection(4000);
		ControllerInterface contrInterf = new ControllerInterface(gui);
		ChatServerList list = new ChatServerList(contrInterf);
		
		conServ.wait(list, contrInterf);
	}

}
