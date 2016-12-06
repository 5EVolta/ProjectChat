import java.io.*;
import java.net.*;

public class Server {

	ServerSocket server = null;
	Socket client = null;
	String stringaRicevuta, stringaModificata = null;
	BufferedReader inserisci;
	DataOutputStream invia;
	String mex;
	InputStreamReader input = new InputStreamReader(System.in);			
	BufferedReader scrivi = new BufferedReader(input);

	public void connessione() {
		try {
			server = new ServerSocket(6789);
			client = server.accept();
			server.close();
			
			
			inserisci = new BufferedReader(new InputStreamReader(client.getInputStream()));
			invia = new DataOutputStream(client.getOutputStream());
			

		} catch (Exception e) {
		}

		// return client;
	}

	public void comunica() {
		try {

			invia.writeBytes("inizia chat: " + '\n'); // invia i dati al client e vado avanti

			for (;;) {
				
				stringaRicevuta = inserisci.readLine(); // mi metto in attesa di ricevere qualcosa dal client invia quello che ricevo in input da tastiera
				System.out.println(stringaRicevuta);	
				mex=scrivi.readLine();								
				invia.writeBytes(mex + "\n");  
			}
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {

		Server s = new Server();
		s.connessione();
		s.comunica();
	}

}
