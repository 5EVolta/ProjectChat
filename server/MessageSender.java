package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import server.gui.ControllerInterface;

public class MessageSender extends Thread {

	private PrintWriter out = null;
	private LinkedBlockingQueue<Message> messageQueue;
	private ControllerInterface contrInterf;

	public MessageSender(Socket socket, ControllerInterface contrInterf) throws IOException {
		if (socket == null) {
			throw new NullPointerException("Socket can't be null");
		}
		out = new PrintWriter(socket.getOutputStream());
		messageQueue = new LinkedBlockingQueue<>();
		
		this.contrInterf = contrInterf;
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

				String msgToController = (msg.getFullString() + " sent");
				System.out.println(msgToController); // TODO: remove
				contrInterf.addMessageToTextArea(msgToController);
		}
	}
	
	public void addMessage(Message message){
		boolean isSuccessful;
		do {
			isSuccessful = messageQueue.offer(message);
		} while (!isSuccessful);
	}
}
