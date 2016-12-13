package client;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.*;

public class Finestra extends JFrame {

	SimpleProtocol sp = new SimpleProtocol();
	static String nomeUtente;
	static String password;

	JLabel erroreLog = new JLabel("Nome utente o password errati, riprovare.");
	boolean visi = false;
	JLabel userLabel = new JLabel("Nome utente (cognome.nome): ");
	TextField userText = new TextField("");
	JLabel pwdLabel = new JLabel("Password: ");
	JPasswordField pwdText = new JPasswordField("");
	JButton pwdButton = new JButton("Accedi");

	JLabel l1 = new JLabel("Indirizzo IP Server: ");
	TextField t1 = new TextField("192.168.");
	JLabel l11 = new JLabel("Porta Server: ");
	TextField t11 = new TextField("");

	JButton b1 = new JButton("Connetti");

	JLabel utenteReg;
	JLabel destinatario = new JLabel("Destinatario: ");
	TextField destText = new TextField("");
	JLabel ricevere = new JLabel("Ricevuto: ");
	TextArea ricevText = new TextArea("");
	JLabel inviare = new JLabel("Scrivi: ");
	TextField inviaText = new TextField("");

	JButton b2 = new JButton("Invia");

	JPanel panel1 = new JPanel(), nompwd = new JPanel(), errore = new JPanel(), panel2 = new JPanel(),
			panel3 = new JPanel(), contenitore = new JPanel();

	CardLayout cL = new CardLayout();
	BorderLayout bL = new BorderLayout();
	GridBagConstraints c = new GridBagConstraints();
	actionListener al = new actionListener();

	Socket miosocket;
	String mex;
	int portaServer;
	InetAddress addr;

	public void finestraCont() {
		contenitore.setLayout(cL);

		contenitore.add(panel1, "uno");
		contenitore.add(panel2, "due");
		contenitore.add(panel3, "tre");
		this.setContentPane(contenitore);
		cL.show(contenitore, "primo");

	}

	public void fin1(boolean visi) {
		panel1.setLayout(bL);

		nompwd.setLayout(new GridBagLayout());
		nompwd.setBackground(new Color(49,126,184));

		c.gridx = 0;
		c.gridy = 0;
		userLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		nompwd.add(userLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		userText.setPreferredSize(new Dimension(100, 20));
		nompwd.add(userText, c);

		c.insets = new Insets(20, 0, 0, 0); // top padding
		c.gridx = 0;
		c.gridy = 1;
		pwdLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		nompwd.add(pwdLabel, c);

		c.insets = new Insets(20, 0, 0, 0); // top padding
		c.gridx = 1;
		c.gridy = 1;
		pwdText.setPreferredSize(new Dimension(100, 20));
		nompwd.add(pwdText, c);

		c.gridx = 3;
		c.gridy = 2;
		nompwd.add(pwdButton, c);
		pwdButton.addActionListener(al);
		panel1.add(nompwd, BorderLayout.CENTER);

		errore.setBackground(new Color(255, 000, 000));
		errore.setVisible(visi);
		erroreLog.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		errore.add(erroreLog);
		panel1.add(errore, BorderLayout.PAGE_END);
	}

	public void fin2() {

		panel2.setLayout(new GridBagLayout());
		panel2.setBackground(new Color(49,126,184));

		c.gridx = 0;
		c.gridy = 0;
		l1.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel2.add(l1, c);

		c.gridx = 1;
		c.gridy = 0;
		t1.setPreferredSize(new Dimension(100, 20));
		panel2.add(t1, c);

		c.insets = new Insets(20, 0, 0, 0); // top padding
		c.gridx = 0;
		c.gridy = 1;
		l11.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel2.add(l11, c);

		c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 1;
		c.gridy = 1;
		t11.setPreferredSize(new Dimension(100, 20));
		panel2.add(t11, c);

		c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 2;
		c.gridy = 2;
		panel2.add(b1, c);

		b1.addActionListener(al);

	}

	public void fin3() {
		panel3.setBackground(new Color(49,126,184));

		c.gridx = 0;
		c.gridy = 1;
		ricevere.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel3.add(ricevere, c);

		c.gridx = 0;
		c.gridy = 2;
		ricevText.setPreferredSize(new Dimension(450, 145));
		ricevText.setEditable(false);
		ricevText.setFont(new Font("Arial", Font.PLAIN, 16));
		panel3.add(ricevText, c);

		c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 0;
		c.gridy = 3;
		destinatario.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel3.add(destinatario, c);

		c.gridx = 1;
		c.gridy = 3;
		destText.setPreferredSize(new Dimension(100, 20));
		panel3.add(destText, c);

		c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 0;
		c.gridy = 4;
		inviare.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel3.add(inviare, c);

		c.gridx = 0;
		c.gridy = 5;
		inviaText.setPreferredSize(new Dimension(450, 145));
		panel3.add(inviaText, c);

		c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 5;
		c.gridy = 6;
		panel3.add(b2, c);
		b2.addActionListener(al);

	}

	public Finestra() {
		super("Chat 5E");
		finestraCont();
		fin1(visi);
		fin2();
		fin3();

	}

	public class actionListener implements ActionListener {

		public void actionPerformed(ActionEvent evt) {

			JButton src = (JButton) evt.getSource();

			if (src.equals(pwdButton)) {

				nomeUtente = userText.getText().trim();
				password = pwdText.getText().trim();

				Login log = new Login(nomeUtente, password);

				if (log.accesso() == 1) {
					cL.show(contenitore, "due");
				}

				else {
					visi = true;
					fin1(visi);
				}

			}

			if (src.equals(b1)) {

				cL.show(contenitore, "tre");

				try {
					addr = InetAddress.getByName(t1.getText());
				} catch (Exception e1) {
				}

				portaServer = Integer.parseInt(t11.getText());

				Connetti conn = new Connetti(nomeUtente,password, addr, portaServer);
				miosocket = conn.connessione();

				faiPartireThread(miosocket);

			}

			if (evt.getActionCommand() == "Invia") {

				mex = nomeUtente + "@" + destText.getText().trim() + ":" + inviaText.getText().trim();
				inviaText.setText("");
				
				Comunica com;
				com = new Comunica(miosocket);

				com.comunicazione(mex);

			}

		}

	}

	public void mostraMex(String mex) {

		ricevText.append(sp.getMittente(mex).trim() + ": \t" + sp.getMessaggio(mex) + '\n');

	}

	public void faiPartireThread(Socket miosocket) {

		Socket socket = miosocket;

		Thread t1 = null;

		try {
			t1 = new Thread(new AscoltoThread(this, socket));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		t1.start();

	}

	public static void main(String[] args) {
		Finestra card = new Finestra();

		card.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		card.setSize(500, 450);
		card.setVisible(true);

	}

}