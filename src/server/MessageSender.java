package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageSender extends Thread {

	private PrintWriter out = null;
	private LinkedBlockingQueue<Message> messageQueue;

	public MessageSender(Socket socket) throws IOException {
		if (socket == null) {
			throw new NullPointerException("Socket can't be null");
		}
		out = new PrintWriter(socket.getOutputStream());
		messageQueue = new LinkedBlockingQueue<>();
	}

	public void run() {

		while (true) {
			Message msg;
			try {
				msg = messageQueue.take(); // blocks if no messages are
											// available
			} catch (InterruptedException e1) {
				return;
			}
			out.println(msg.getFullString());
			System.out.println(msg.getFullString() + " sent"); // TODO: remove
		}
	}

	public void addMessage(Message message) {
		boolean isSuccessful;
		do {
			isSuccessful = messageQueue.offer(message);
		} while (!isSuccessful);
	}
}
