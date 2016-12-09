package server;

import server.controller.Controller;
import server.view.View;

public class ServerMain {
	
	public static void main(String arg[]){
		
		ChatServerList list = new ChatServerList();
		ServerConnection conServ = new ServerConnection(4000, list);
		
		Controller contr = new Controller(conServ);
		View view = new View(contr);
		contr.setView(view);
		
		list.addPropertyChangeListener(view);
		
		//conServ.start();
	}

}
