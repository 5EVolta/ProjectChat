package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import server.gui.ControllerInterface;

public class ChatServer extends Thread {

	private DataOutputStream out;
	private BufferedReader in;
	private ChatServerList chatServerList;
	private String id;
	private LinkedBlockingQueue<Message> messageQueue;
	private ControllerInterface contrInterf;

	public ChatServer(Socket socket, ChatServerList chatServerList,ControllerInterface contrInterf) {		
		if (socket == null) {
			throw new NullPointerException("Socket can't be null");
		}
		if (chatServerList == null) {
			throw new NullPointerException("ChatServerList can't be null");
		}
		this.chatServerList = chatServerList;
		this.contrInterf = contrInterf;
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		messageQueue = new LinkedBlockingQueue<Message>();
		Runnable r = new Runnable() {

			public void run() {

				while (true) {
					Message msg;
					try {
						msg = messageQueue.take(); // blocks if no messages are
													// available
					} catch (InterruptedException e1) {
						return;
					}
					try {
						out.writeBytes(msg.getFullString() + "\n");
						
						String msgToController = (msg.getFullString() + " sent");
						System.out.println(msgToController);				 // TODO: remove
						contrInterf.addMessageToTextArea(msgToController);
						
					} catch (IOException e) {
						e.printStackTrace();
						interrupt();
					}
				}
			}
		};
		new Thread(r).start();
	}

	public void addMessage(Message message) {
		boolean isSuccessful;
		do {
			isSuccessful = messageQueue.offer(message);
		} while (!isSuccessful);
	}

	@Override
	public void interrupt() {
		chatServerList.disconnect(this.id);
		super.interrupt();
	}

	public void run() {
		// Registration on ChatServerList
		String msgToController="new ChatServer running";
		contrInterf.addMessageToTextArea(msgToController);
		System.out.println(msgToController);
		String str = "";
		try {
			do {
				str = in.readLine();
			} while (str == null);

			Message msg = new Message(str);
			// register returns false if registration fails
			if (!chatServerList.connect(msg.getSender(), this)) {
				this.interrupt();
			}
			this.id = msg.getSender();
			// Reads messages from client
			while (true) {
				str = in.readLine();
				if (str != null) {
					if (!str.equals("")) {
						chatServerList.submit(new Message(str), this.id);
						msgToController = str + " submitted to ChatServerList";
						contrInterf.addMessageToTextArea(msgToController);
						System.out.println(msgToController); 					// TODO:remove
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.interrupt();
		}
	}
}
