package client;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connetti {

	InetAddress ip;
	int pS;
	String nU,pwd;
	Socket miosocket;
	DataOutputStream outVersoServer;

	public Connetti(String nomeUtente,String password, InetAddress addr, int portaServer) {
		ip = addr;
		pS = portaServer;
		nU=nomeUtente;
		pwd=password;
	}

	public Socket connessione() {

		try {
			miosocket = new Socket(ip, pS);
			
		    outVersoServer = new DataOutputStream(miosocket.getOutputStream());
			outVersoServer.writeBytes(nU+"@login:"+pwd+ '\n'); //effettuo il login

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return miosocket;
	}

}