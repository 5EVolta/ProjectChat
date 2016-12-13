package client;

import java.io.*;
import java.net.Socket;

public class Comunica {
	Socket socket;
	String mess;
	DataOutputStream outVersoServer;

	public Comunica(Socket miosocket) {
		socket = miosocket;
	}

	public void comunicazione(String mex) {
		mess = mex;

		try {
			outVersoServer = new DataOutputStream(socket.getOutputStream());


			outVersoServer.writeBytes(mess + '\n');
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
