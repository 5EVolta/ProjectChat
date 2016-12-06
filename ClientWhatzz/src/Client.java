
import java.io.*;
import java.net.*;

public class Client {

	public Client() {

	}

	public static void main(String[] args) {

		int portaServer = 6789;
		InetAddress addr = null;
		try {
			addr = InetAddress.getByName("localhost");
		} catch (Exception e1) {
		}

		Client c = new Client(); // crea un oggetto di tipo 'Client' 
		Connetti conn = new Connetti(addr, portaServer);
		Socket miosocket = conn.connessione(); // si connette al server e
												// restituisce il socket

		c.faiPartireThread(miosocket);

		Comunica com = new Comunica(miosocket);
		com.comunicazione();

	}

	public void faiPartireThread(Socket miosocket) {

		Socket socket = miosocket;

		Thread t1 = null;

		try {
			t1 = new Thread(new AscoltoThread(socket));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		t1.start();

	}

}
