package server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import server.model.ServerConnection;
import server.view.View;

public class Controller implements ActionListener {

	private ServerConnection servConn;
	private View view;

	public Controller(ServerConnection serverConnection) {
		if (serverConnection == null) {
			throw new NullPointerException("serverConnection can't be null");
		}
		this.servConn = serverConnection;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	private void startServer(){
		servConn.start();
		view.setEnabledButStop(true);
		view.setEnabledButStart(false);
		view.setEnabledPortValue(false);
	}
	
	private void stopServer(){
		servConn.stop();
		view.setEnabledButStart(true);
		view.setEnabledPortValue(true);
		view.setEnabledButStop(false);
	}
	
	private void changePort(String newPort){
		try{
			Integer port = Integer.parseInt(newPort);
			servConn.changePort(port);
		}catch(Exception e){}
		view.setEnabledButStart(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JButton) {
			JButton btn = (JButton) obj;
			switch (btn.getText()) {
			case "Start Server":
				this.startServer();
				break;
			case "Stop Server":
				this.stopServer();
				break;
			case "OK":
				this.changePort(view.getPortText());
				break;
			}
		}

	}

}
