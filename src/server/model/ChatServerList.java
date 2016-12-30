package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import server.model.msg.Message;

public class ChatServerList {
	private PropertyChangeSupport pcs;
	private PropertyChangeListener listener;
	private ConcurrentHashMap<String, ChatServer> chatServerList;

	public ChatServerList() {
		pcs = new PropertyChangeSupport(this);
		this.chatServerList = new ConcurrentHashMap<String, ChatServer>();
	}

	// Adds the new user to the user list
	public void connect(String ID, ChatServer chatserver) {

		if (chatServerList.containsKey(ID)) {
			throw new InvalidParameterException("ChatServer with the same userId already connected");
		} else {
			chatServerList.put(ID, chatserver);
			pcs.firePropertyChange("chatServerList", null, chatserver);
			System.out.println(ID + " connected on ChatServerList"); // TODO: remove
			chatserver.addPropertyChangeListener(listener);
		}
	}

	public void disconnect(String ID) {
		if (ID != null) {
			pcs.firePropertyChange("chatServerList", chatServerList.get(ID), null);
			chatServerList.remove(ID);
		}
	}

	// Passes the message to the reciever's socket
	public void submit(Message message) {
		String recp = message.getRecipient();
		if (message.isValid()) {
			ChatServer recipient = chatServerList.get(recp);
			recipient.addMessage(message);
			System.out.println(message.getFullString() + " submitted to " + recp + " from ChatServerList"); // TODO:
																											// remove
		}
	}

	public void stop() {
		for (Map.Entry<String, ChatServer> entry : chatServerList.entrySet()) {
			entry.getValue().interrupt();
		}
		chatServerList.clear();
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
		this.listener = listener;
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

}
