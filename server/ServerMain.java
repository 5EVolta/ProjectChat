package server;

import server.gui.ControllerInterface;
import server.gui.View;

public class ServerMain {

	public static void main(String arg[]){
		View gui = new View();
		//ServerConnection conServ = new ServerConnection(Integer.parseInt(arg[0]));	
		ServerConnection conServ = new ServerConnection(4000);
		ControllerInterface contrInterf = new ControllerInterface(gui);
		ChatServerList list = new ChatServerList();
		
		conServ.wait(list);
	}

}
