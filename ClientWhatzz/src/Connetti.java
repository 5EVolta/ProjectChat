import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connetti {

	InetAddress ip;
	int pS;
	Socket miosocket;

	public Connetti(InetAddress addr, int portaServer) {
		ip = addr;
		pS = portaServer;
	}

	public Socket connessione() {

		try {
			miosocket = new Socket(ip, pS);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
		return miosocket;
	}

}