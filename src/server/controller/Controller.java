package server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import server.model.ServerConnection;
import server.view.View;

public class Controller implements ActionListener {

	private ServerConnection servConn;
	private View view;
	
	public Controller(ServerConnection serverConnection){
		if(serverConnection == null){
			throw new NullPointerException("serverConnection can't be null");
		}
		this.servConn = serverConnection;
	}
	
	public void setView(View view){
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton){
			JButton btn = (JButton)obj;
			if(btn.getText().equals("Start Server")){
				servConn.start();
			}
			if(btn.getText().equals("OK")){
				try{
					String txt = view.getTextPort().getText();
					servConn.changePort(Integer.parseInt(txt));
				}catch(NumberFormatException | IllegalStateException nfe){}
			}
		}
		
	}

}
