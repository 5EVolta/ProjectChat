package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

public class ChatServer extends Thread {

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
			if (utils.login(msg.getSender(), msg.getMsg())) {
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

		if (this.tryLogin(msg)) {
			if (!chatServerList.connect(msg.getSender(), this)) {
				this.interrupt();
			}
			this.userId = msg.getSender();
		}
	}

	public void run() {
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
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.interrupt();
		}
	}
}
