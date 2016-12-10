package server.model;

public class Message {

	private String recipient;
	private String sender;
	private String msg;
	private String fullString;
	
	public Message(String fullString){
		this.fullString = fullString;
		if(fullString != null){
			recipient = SimpleProtocol.getRecipient(fullString);
			sender = SimpleProtocol.getSender(fullString);
			msg = SimpleProtocol.getMess(fullString);
		}
	}
	
	public String getMsg(){return msg;}
	public String getRecipient(){return recipient;}
	public String getSender(){return sender;}
	public String getFullString(){return fullString;}
	
	public boolean isValid(){
		return SimpleProtocol.isValid(fullString);
	}
	
}