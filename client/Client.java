package client;

import java.io.*;
import java.net.*;

public class Client {

	String nomeServer = "localhost";
	int portaServer = 4000;
	Socket miosocket;
	InetAddress addr; //Con Server Su un'altra macchina
	DataOutputStream outVersoServer;
	InputStreamReader input = new InputStreamReader(System.in);
	BufferedReader inDalServer;
	BufferedReader tastiera = new BufferedReader(input);

	public void comunica() {
		try {

			//outVersoServer.writeBytes("gabriele@gabriele:");

			for (;;) {

				outVersoServer.writeBytes(tastiera.readLine() + '\n');

			}
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		Client c = new Client(); // crea un oggetto di tipo 'Client' ed esegue
									// sia la connessione che fa partire il
									// thread
		c.connetti(); // si connette al server

		c.faiPartireThread();
		c.comunica();

	}

	public void faiPartireThread() {
		Thread t1 = null;

		try {
			t1 = new Thread(new AscoltoThread(miosocket));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		t1.start();

	}

	public Client() {

	}

	public void connetti() { // METODO PER CONNETTERSI AL SERVER. STANDARD:
								// IMPOSTATO SU LOCAL HOST
		try {
			addr = InetAddress.getByName("localhost");
			miosocket = new Socket(addr, portaServer); // Sostituire
																// 'nomeServer'con
																// 'addr'per
																// connettersi
																// ad un'altra
																// macchina
			outVersoServer = new DataOutputStream(miosocket.getOutputStream());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}