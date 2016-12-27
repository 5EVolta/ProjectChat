package server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import server.model.ServerConnection;
import server.view.View;

public class Controller implements ActionListener, KeyListener {

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

	private void startServer() {
		try {
			servConn.start();
			view.setEnabledButStop(true);
			view.setEnabledButStart(false);
			view.setEnabledPortValue(false);
		} catch (Exception e) {
			view.displayError(e.getMessage());
		}
	}

	private void stopServer() {
		servConn.stop();
		view.setEnabledButStart(true);
		view.setEnabledPortValue(true);
		view.setEnabledButStop(false);
	}

	private void changePort() {
		String newPort = view.getPortText();
		try {
			Integer port = Integer.parseInt(newPort);
			servConn.setPort(port);
			view.setEnabledButStart(true);
		} catch (Exception e) {
			e.printStackTrace();
			view.displayError(e.getMessage());
		}
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
				this.changePort();
				break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.changePort();
			this.startServer();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
