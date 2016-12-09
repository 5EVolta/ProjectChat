package server.gui;

public class ControllerInterface {

	private View gui;
	
	public ControllerInterface(View gui){
		this.gui = gui;
	}
	
	public void addMessageToTextArea(String msg){
		gui.getTextMess().append(msg+'\n');
	}
	
	public void addConnectionToList(String ID){
		gui.getTextConn().setForeground(UserFontColorGenerator.getNewFontColor());
		gui.getTextConn().append(ID+'\n');
	}
	
	public void removeConnectionFromList(String ID){
		gui.getTextConn().getText().replaceFirst(ID,"");
	}
	
	public void changeServerState(){
		
	}
	
	public void changeServerSocketPort(){
		
	}
}
