package server;

import java.util.concurrent.ConcurrentHashMap;

public class ChatServerList {

	private ConcurrentHashMap<String, ChatServer> listaChatServer;

	public ChatServerList() {
		this.listaChatServer = new ConcurrentHashMap<String, ChatServer>();
	}

	// Adds the new user to the user list
	public boolean connect(String ID, ChatServer chatserver) {
		boolean response = true;

		if (listaChatServer.containsKey(ID)) {
			response = false;
		}

		else {
			listaChatServer.put(ID, chatserver);
			System.out.println(ID + " registered on ChatServerList"); // TODO: remove
		}

		return response;
	}

	public void disconnect(String ID) {
		listaChatServer.remove(ID);
		System.out.println(ID + " disconnected from ChatServerList"); // TODO: remove
	}

	// Passes the message to the reciever's socket
	public void submit(Message message) {
		String recp = message.getRecipient();
		if (listaChatServer.containsKey(recp)) {
			ChatServer recipient = listaChatServer.get(recp);
			recipient.addMessage(message);
			System.out.println(message.getFullString() + " submitted to " + recp + " from ChatServerList");
		}
	}

}
