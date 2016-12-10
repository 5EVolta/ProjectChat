package client;

import java.io.*;
import java.net.Socket;

public class AscoltoThread extends Thread {

	String mex;
	Socket miosocket;
	BufferedReader inDalServer = null;

	public AscoltoThread(Socket socket) throws IOException {
		miosocket = socket;
		inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
	}

	public void run() {

		while (true) { // Ciclo all'infinito cos� star� sempre in ascolto
			try {

				mex = inDalServer.readLine(); // Ricevo il messaggio dal server
				if (mex != null) {
					System.out.println(mex); // lo mostro a video
				}
			} catch (Exception e) {
			}
		}
	}

	public String messaggio() {

		return mex;
	}
}