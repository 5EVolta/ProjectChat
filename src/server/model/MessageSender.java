package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageSender extends Thread {

	private PropertyChangeSupport pcs;
	private PrintWriter out = null;
	private LinkedBlockingQueue<Message> messageQueue;

	public MessageSender(Socket socket) throws IOException {
		if (socket == null) {
			throw new NullPointerException("Socket can't be null");
		}
		pcs = new PropertyChangeSupport(this);
		out = new PrintWriter(socket.getOutputStream(), true);
		messageQueue = new LinkedBlockingQueue<>();
	}

	public void run() {

		while (true) {
			Message msg;
			try {
				msg = messageQueue.take(); // blocks if no messages are
											// available
			} catch (InterruptedException e1) {
				e1.printStackTrace();
				return;
			}
			out.println(msg.getFullString());
			pcs.firePropertyChange("sentMessage", null, msg);
			System.out.println(msg.getFullString() + " sent");
		}
	}

	public void addMessage(Message message) {
		boolean isSuccessful;
		do {
			isSuccessful = messageQueue.offer(message);
		} while (!isSuccessful);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
