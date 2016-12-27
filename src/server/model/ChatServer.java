package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.InvalidParameterException;
import java.util.Date;

import server.model.credentials.UserUtility;
import server.model.msg.Message;

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

	public void run() {
		// pcs.firePropertyChange("ChatServer", null, this);
		try {
			Message loginMessage = this.getFirstMessage();
			this.tryLogin(loginMessage);
			this.connectToChatServerList();
			
			System.out.println("ChatServer: " + this.getUserId() + " init completed!");
			
			while (true) {
				readMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.interrupt();
		}
	}
	
	private Message getFirstMessage() throws IOException {
		String str = "";
		long terminInMillis = new Date().getTime() + 60000;
		Date termin = new Date(terminInMillis);
		do {
			str = in.readLine();
		} while (str == null && new Date().before(termin));
		Message msg = new Message(str);
		this.userId = msg.getSender();
		return msg;		
	}
	
	private void tryLogin(Message msg) {
		if (msg.isLoginMessage()) {
			UserUtility utils = UserUtility.getInstance();
			if (!utils.login(msg.getSender(), msg.getMsg())) {
				System.out.println("Login failed");
				this.interrupt();
			}
		}else{
			throw new InvalidParameterException("Login failed");
		}
	}
	
	private void connectToChatServerList(){
		try{
		this.chatServerList.connect(this.getUserId(), this);
		}catch(InvalidParameterException e){
			e.printStackTrace();
			this.interrupt();
		}
	}

	private void readMessage() throws IOException {
		Message msg;
		String str = in.readLine();
		if (str != null && !str.equals("")) {
			msg = new Message(str);
			if (msg.isValid() && msg.getSender().equals(this.userId)) {
				chatServerList.submit(msg);
				pcs.firePropertyChange("receivedMessage", null, msg);
				System.out.println("Received " + msg.getFullString());
			}
		}
	}
	
	@Override
	public void interrupt() {
		System.out.println(this.getUserId() + " interrupted");
		chatServerList.disconnect(this.userId);
		mSender.interrupt();
		super.interrupt();
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public void addMessage(Message message) {
		if (!mSender.isInterrupted()) {
			mSender.addMessage(message);
		} else {
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
