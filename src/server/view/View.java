package server.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

public class View implements PropertyChangeListener {

	private JFrame f;
	private JPanel pan;
	private JLabel labConn, labMess;
	private JButton butStart, butPort;
	private JTextArea textConn, textMess;
	private JTextField textPort;
	private GridBagConstraints gbc;

	public View(Controller controller) {
		this.f = new JFrame("Server");

		pan = new JPanel();

		labConn = new JLabel("Active Connections");
		textConn = new JTextArea(20, 30);
		textConn.setWrapStyleWord(true);
		textConn.setEditable(false);
		JScrollPane textConnScroll = new JScrollPane(textMess, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		labMess = new JLabel("LOG");
		textMess = new JTextArea(20, 80);
		textMess.setWrapStyleWord(true);
		textMess.setEditable(false);
		JScrollPane textMessScroll = new JScrollPane(textMess, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		butStart = new JButton("Start Server");
		butStart.addActionListener(controller);

		butPort = new JButton("OK");
		textPort = new JTextField("Porta");
		//textPort.addKeyListener(controller); on day...

		pan.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();

		gbc.insets = new Insets(20, 10, 20, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		pan.add(butStart, gbc);

		gbc.gridx = 1;
		gbc.ipadx = 80;
		gbc.anchor = GridBagConstraints.LINE_END;
		pan.add(textPort, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		pan.add(butPort, gbc);

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		pan.add(labConn, gbc);

		gbc.gridx = 1;
		pan.add(labMess, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 2;
		pan.add(textConnScroll, gbc);

		gbc.gridx = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		pan.add(textMessScroll, gbc);

		f.add(pan);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);

		f.pack();
		f.setVisible(true);
	}

	public JButton getButStart() {
		return butStart;
	}

	public JButton getButPort() {
		return butPort;
	}

	public JTextArea getTextConn() {
		return textConn;
	}

	public JTextArea getTextMess() {
		return textMess;
	}

	public JTextField getTextPort() {
		return textPort;
	}

	public void propertyChange(PropertyChangeEvent e) {

		if (e.getPropertyName().equals("chatServerList") && e.getSource() instanceof ChatServerList) {

			ChatServer cs = null;
			if (e.getOldValue() == null && e.getNewValue() != null) {
				try {
					cs = (ChatServer) e.getNewValue();
					System.out.println(cs.getName() + " connected");
				} catch (Exception ex) {
				}
			}
			if (e.getNewValue() == null && e.getOldValue() != null) {
				try {
					cs = (ChatServer) e.getOldValue();
					System.out.println(cs.getName() + " disconnected");
				} catch (Exception ex) {
				}
			}
		}

	}

}
