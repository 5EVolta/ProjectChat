package gui;


import java.awt.*;
import java.awt.event.*;
import server.*;

public class Interface implements ActionListener{

	private Frame f;
	private Panel pan;
	private Label labConn,labMess;
	private Button butStart,butPort;
	private TextArea textConn,textMess;
	private TextField textPort;
	private GridBagConstraints gbc;
	
	public Interface(){
		
		this.f = new Frame("Server");
		
		pan = new Panel();
		
		labConn = new Label("Connessioni Attive");
	    textConn = new TextArea(20,30);
	    textConn.setEditable(false);
	    labMess = new Label("LOG");
	    textMess = new TextArea(20,80);
	    textMess.setEditable(false);
	    
	    butStart = new Button("Start Server");  
	    butStart.addActionListener(this);
	    
	    butPort = new Button("OK");
	    textPort = new TextField("Porta");
	    
	    pan.setLayout(new GridBagLayout());
	    gbc = new GridBagConstraints();
	    
	    gbc.insets = new Insets(20,10,20,10);
	    gbc.gridx=0;
	    gbc.gridy=0;
	    pan.add(butStart,gbc);
	    
	    gbc.gridx=1;
	    gbc.ipadx=80;
	    gbc.anchor=GridBagConstraints.LINE_END;
	    pan.add(textPort,gbc);
	    
	    gbc.gridx=2;
	    gbc.gridy=0;
	    gbc.anchor=GridBagConstraints.LINE_START;
	    pan.add(butPort,gbc);
	    
	    gbc.insets = new Insets(5,5,5,5);
	    gbc.gridx=0;
	    gbc.gridy=1;
	    pan.add(labConn, gbc);
	    
	    gbc.gridx=1;
	    pan.add(labMess, gbc);
	    
	    gbc.anchor=GridBagConstraints.CENTER;
	    gbc.gridx=0;
	    gbc.gridy=2;
	    pan.add(textConn, gbc);
	    
	    gbc.gridx=1;
	    gbc.gridwidth=GridBagConstraints.REMAINDER;
	    pan.add(textMess, gbc);
	    
	    f.add(pan);
	    
	    f.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        } );
	    
		f.pack();
	    f.setVisible(true);
	}

	public Button getButStart(){return butStart;}
	public Button getButPort(){return butPort;}
	public TextArea getTextConn(){return textConn;}
	public TextArea getTextMess(){return textMess;}
	public TextField getTextPort(){return textPort;}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
