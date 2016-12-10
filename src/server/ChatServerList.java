package server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServerList {

	private PropertyChangeSupport pcs;
	private ConcurrentHashMap<String, ChatServer> chatServerList;

	public ChatServerList() {
		pcs = new PropertyChangeSupport(this);
		this.chatServerList = new ConcurrentHashMap<String, ChatServer>();
	}

	// Adds the new user to the user list
	public boolean connect(String ID, ChatServer chatserver) {
		boolean response = true;

		if (chatServerList.containsKey(ID)) {
			response = false;
		}

		else {
			chatServerList.put(ID, chatserver);
			pcs.firePropertyChange("chatServerList", null, chatserver);
			System.out.println(ID + " registered on ChatServerList"); // TODO: remove
		}

		return response;
	}

	public void disconnect(String ID) {
		pcs.firePropertyChange("chatServerList", chatServerList.get(ID), null);
		chatServerList.remove(ID);
		System.out.println(ID + " disconnected from ChatServerList"); // TODO: remove
	}

	// Passes the message to the reciever's socket
	public void submit(Message message) {
		String recp = message.getRecipient();
		if (chatServerList.containsKey(recp)) {
			ChatServer recipient = chatServerList.get(recp);
			recipient.addMessage(message);
			System.out.println(message.getFullString() + " submitted to " + recp + " from ChatServerList");
		}
	}
	
	public void stop(){
		for (Map.Entry<String, ChatServer> entry : chatServerList.entrySet()){
			entry.getValue().interrupt();
		}
		chatServerList.clear();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}

}
