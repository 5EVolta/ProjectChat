package server.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import server.controller.Controller;
import server.model.ChatServer;
import server.model.ChatServerList;
import server.model.Message;
import server.model.MessageSender;

public class View implements PropertyChangeListener {

	private JFrame f;
	private JPanel pan;
	private JLabel labConn, labLog, labMess;
	private JButton butStart, butStop, butPort;
	private JTextArea textConn, textLog, textMess;
	private JTextField textPort;
	private GridBagConstraints gbc;

	public View(Controller controller) {
		this.f = new JFrame("Server");

		pan = new JPanel();

		labConn = new JLabel("Active Connections");
		textConn = new JTextArea(34, 20);
		textConn.setWrapStyleWord(true);
		textConn.setEditable(false);
		JScrollPane textConnScroll = new JScrollPane(textConn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		labLog = new JLabel("LOG");
		textLog = new JTextArea(15, 80);
		textLog.setWrapStyleWord(true);
		textLog.setEditable(false);
		JScrollPane textLogScroll = new JScrollPane(textLog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		labMess = new JLabel("Messagges");
		textMess = new JTextArea(15, 80);
		textMess.setWrapStyleWord(true);
		textMess.setEditable(false);
		JScrollPane textMessScroll = new JScrollPane(textMess, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		butStart = new JButton("Start Server");
		// butStart.setBackground(Color.WHITE);
		butStart.setForeground(Color.GREEN);
		butStart.addActionListener(controller);
		butStart.setEnabled(false);

		butStop = new JButton("Stop Server");
		// butStop.setBackground(Color.WHITE);
		butStop.setForeground(Color.RED);
		butStop.addActionListener(controller);
		butStop.setEnabled(false);

		butPort = new JButton("OK");
		butPort.addActionListener(controller);
		textPort = new JTextField("Porta");
		// textPort.addKeyListener(controller); one day...

		pan.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();

		gbc.insets = new Insets(20, 10, 20, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		pan.add(butStart, gbc);

		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		gbc.gridy = 0;
		pan.add(butStop, gbc);

		gbc.insets = new Insets(20, 250, 20, 10);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 100;
		gbc.ipady = 10;
		gbc.anchor = GridBagConstraints.LINE_END;
		pan.add(textPort, gbc);

		gbc.insets = new Insets(20, 10, 20, 10);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.ipadx = 100;
		gbc.ipady = 5;
		gbc.anchor = GridBagConstraints.LINE_START;
		pan.add(butPort, gbc);

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		pan.add(labConn, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		pan.add(labLog, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		pan.add(textConnScroll, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		pan.add(textLogScroll, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		pan.add(labMess, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		pan.add(textMessScroll, gbc);

		f.add(pan);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocation(new Point(200, 150));

		f.pack();
		f.setVisible(true);
	}

	public void setEnabledButStart(boolean state) {
		butStart.setEnabled(state);
	}

	public void setEnabledButStop(boolean state) {
		butStop.setEnabled(state);
	}

	public void setEnabledPortValue(boolean state) {
		textPort.setEnabled(state);
		butPort.setEnabled(state);
	}

	public String getPortText() {
		return textPort.getText();
	}

	public void propertyChange(PropertyChangeEvent e) {

		if (e.getPropertyName().equals("chatServerList") && e.getSource() instanceof ChatServerList) {

			ChatServer cs = null;
			if (e.getOldValue() == null && e.getNewValue() != null) {
				try {
					cs = (ChatServer) e.getNewValue();
					textLog.append(cs.getUserId() + " connected\n");
				} catch (Exception ex) {
				}
			}
			if (e.getNewValue() == null && e.getOldValue() != null) {
				try {
					cs = (ChatServer) e.getOldValue();
					textLog.append(cs.getUserId() + " disconnected\n");
				} catch (Exception ex) {
				}
			}
		} else if (e.getPropertyName().equals("ChatServer") && e.getSource() instanceof ChatServer) {
			if (e.getOldValue() == null && e.getNewValue() instanceof ChatServer) {
				textLog.append("new ChatServer running\n");
			}
		} else if (e.getOldValue() == null && e.getNewValue() instanceof Message) {
			Message msg = (Message) e.getNewValue();
			if(e.getSource() instanceof ChatServer && e.getPropertyName().equals("receivedMessage")){
				textMess.append("Received: " + msg.getFullString() + "\n");
			}else if(e.getSource() instanceof MessageSender && e.getPropertyName().equals("sentMessage")){
				textMess.append("Sent: " + msg.getFullString() + "\n");
			}
		}

	}

}
