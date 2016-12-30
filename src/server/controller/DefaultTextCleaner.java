package server.controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class DefaultTextCleaner implements FocusListener, MouseListener {
	private String defaultText;
	private boolean isFocused;

	public DefaultTextCleaner(String defaultText) {
		this.defaultText = defaultText;
		this.isFocused = false;
	}

	@Override
	public void focusGained(FocusEvent e) {
		JTextField tField = this.tryToCastIntoJTextField(e.getSource());
		if (tField != null && tField.getText().equals(defaultText)) {
			isFocused = true;
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField tField = this.tryToCastIntoJTextField(e.getSource());
		if (tField != null) {
			isFocused = false;
			if(tField.getText().equals("")){
				tField.setText(defaultText);
			}else{
				tField.removeFocusListener(this);
				tField.removeMouseListener(this);
			}
		}
	}

	private JTextField tryToCastIntoJTextField(Object obj) {
		if (obj instanceof JTextField) {
			return (JTextField) obj;
		}
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTextField tField = this.tryToCastIntoJTextField(e.getSource());
		if (tField != null && isFocused) {
			tField.setText("");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
