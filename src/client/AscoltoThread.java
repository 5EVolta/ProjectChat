package client;

import java.io.*;
import java.net.Socket;

public class AscoltoThread extends Thread {

	String mex;
	Socket miosocket;
	BufferedReader inDalServer = null;
	Finestra f1;

	public AscoltoThread(Finestra f, Socket socket) throws IOException {
		f1 = f;
		miosocket = socket;
		inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
	}

	public void run() {

		while (true) { // Ciclo all'infinito cos� star� sempre in ascolto
			try {

				mex = inDalServer.readLine(); // Ricevo il messaggio dal server

				if (mex != null)
					f1.mostraMex(mex); // lo invio al main

			} catch (Exception e) {
			}
		}
	}

	public String messaggio() {

		return mex;
	}
}
