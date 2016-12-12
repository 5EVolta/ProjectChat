package server;

import server.controller.Controller;
import server.model.ChatServerList;
import server.model.ServerConnection;
import server.view.View;

public class ServerMain {
	
	public static void main(String arg[]){
		
		ChatServerList list = new ChatServerList();
		ServerConnection conServ = new ServerConnection(list);
		
		Controller contr = new Controller(conServ);
		View view = new View(contr);
		contr.setView(view);
		
		list.addPropertyChangeListener(view);
	}

}
