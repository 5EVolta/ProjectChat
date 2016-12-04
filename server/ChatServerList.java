package server;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import server.gui.ControllerInterface;

public class ChatServerList {

	private ConcurrentHashMap<String, ChatServer> listaChatServer;
	private ControllerInterface contrInterf;

	public ChatServerList(ControllerInterface contrInterf) {
		this.contrInterf = contrInterf;
		this.listaChatServer = new ConcurrentHashMap<String, ChatServer>();
	}

	public boolean connect(String ID, ChatServer chatserver) { // Adds the new													// user to the
																// user list
		boolean response = true;

		if (listaChatServer.containsKey(ID)) {
			response = false;
		}

		else {
			listaChatServer.put(ID, chatserver);
			String msg = ID + " registered on ChatServerList";
			System.out.println(msg); // TODO: remove
			contrInterf.addMessageToTextArea(msg);
			contrInterf.addConnectionToList(ID);
		}

		return response;
	}

	public void disconnect(String ID) {
		listaChatServer.remove(ID);
		String msg = ID + " disconnected from ChatServerList";
		System.out.println(msg); // TODO: remove
		contrInterf.addMessageToTextArea(msg);
		contrInterf.removeConnectionFromList(ID);
	}

	public void submit(Message message, String ID) { // Passes the message to
														// the reciever's socket
		if (listaChatServer.containsKey(message.getRecipient())) {
			ChatServer receiver = listaChatServer.get(message.getRecipient());
			receiver.addMessage(message);
			String msg = message.getFullString() + " submitted to " + ID + " from ChatServerList";
			System.out.println(msg);
			contrInterf.addMessageToTextArea(msg);
		}
	}

}
