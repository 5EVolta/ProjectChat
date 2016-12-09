package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatServer extends Thread {

	private MessageSender mSender = null;
	private BufferedReader in;
	private ChatServerList chatServerList;
	private String id;

	public ChatServer(Socket socket, ChatServerList chatServerList) {
		if (socket == null) {
			throw new NullPointerException("Socket can't be null");
		}
		if (chatServerList == null) {
			throw new NullPointerException("ChatServerList can't be null");
		}
		this.chatServerList = chatServerList;

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
		this.mSender.addMessage(message);
	}

	@Override
	public void interrupt() {
		chatServerList.disconnect(this.id);
		mSender.interrupt();
		super.interrupt();
	}

	public void run() {
		// Registration on ChatServerList;
		System.out.println("new ChatServer running");
		String str = "";
		try {
			do {
				str = in.readLine();
			} while (str == null);

			Message msg = new Message(str);
			// register returns false if connection fails
			if (!msg.isValid() || !chatServerList.connect(msg.getSender(), this)) {
				this.interrupt();
			}
			this.id = msg.getSender();
			// Reads messages from client
			while (true) {
				str = in.readLine();
				if (str != null && !str.equals("")) {
					msg = new Message(str);
					if (msg.isValid() && msg.getSender().equals(this.id)) {
						chatServerList.submit(msg);
						
						System.out.println(str + " submitted to ChatServerList"); // TODO:remove
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.interrupt();
		}
	}
}
