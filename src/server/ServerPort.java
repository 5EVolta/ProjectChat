package server;

public class ServerPort {

	private int portNum;
	private final int defaultPort = 4000; 
	
	public ServerPort(){
		portNum=defaultPort;
	}
	
	public int getCurrentPort(){return portNum;}
	public int getDefaultPort(){return defaultPort;}
	
	public void setCurrentPort(int portNum){this.portNum=portNum;}
	
}
