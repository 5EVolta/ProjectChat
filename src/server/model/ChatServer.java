package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

public class ChatServer extends Thread {

	private PropertyChangeSupport pcs;
	private MessageSender mSender = null;
	private BufferedReader in;
	private ChatServerList chatServerList;
	private String userId;

	public ChatServer(Socket socket, ChatServerList chatServerList) {
		if (socket == null) {
			throw new NullPointerException("Socket can't be null");
		}
		if (chatServerList == null) {
			throw new NullPointerException("ChatServerList can't be null");
		}
		this.chatServerList = chatServerList;
		this.pcs = new PropertyChangeSupport(this);

		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.mSender = new MessageSender(socket);
			mSender.start();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public void addMessage(Message message) {
		if (mSender.isAlive()) {
			mSender.addMessage(message);
		} else {
			this.interrupt();
		}
	}

	public String getUserId() {
		return this.userId;
	}

	@Override
	public void interrupt() {
		chatServerList.disconnect(this.userId);
		mSender.interrupt();
		super.interrupt();
	}

	private boolean tryLogin(Message msg) {
		if (msg.isLoginMessage()) {
			UserUtility utils = UserUtility.getInstance();
			if (!utils.login(msg.getSender(), msg.getMsg())) {
				this.interrupt();
			}
		}
		return msg.isLoginMessage();
	}

	private void init() throws IOException {
		String str = "";
		long terminInMillis = new Date().getTime() + 60000;
		Date termin = new Date(terminInMillis);
		do {
			str = in.readLine();
		} while (str == null && new Date().before(termin));
		Message msg = new Message(str);

		this.userId = msg.getSender();
		
		if (this.tryLogin(msg)) {
			if (!chatServerList.connect(msg.getSender(), this)) {
				this.interrupt();
			}
		}
		System.out.println("ChatServer: " + this.getUserId() + " init completed!");
	}

	public void run() {
		//pcs.firePropertyChange("ChatServer", null, this);
		Message msg;
		String str;
		try {
			this.init();
			/** Reads messages from client */
			while (true) {
				str = in.readLine();
				if (str != null && !str.equals("")) {
					msg = new Message(str);
					if (msg.isValid() && msg.getSender().equals(this.userId)) {
						chatServerList.submit(msg);
						pcs.firePropertyChange("receivedMessage", null, msg);
						System.out.println("Received " + msg.getFullString());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.interrupt();
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
		mSender.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
		mSender.removePropertyChangeListener(listener);
	}
}
