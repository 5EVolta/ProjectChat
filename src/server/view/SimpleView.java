package server.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import server.model.ChatServer;
import server.model.ChatServerList;

public class SimpleView implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		
		if (e.getPropertyName().equals("chatServerList") && 
				e.getSource() instanceof ChatServerList) {
			
			ChatServer cs = null;
			if (e.getOldValue() == null && e.getNewValue() != null) {
				try {
					cs = (ChatServer) e.getNewValue();
					System.out.println(cs.getName() + " connected");
				} catch (Exception ex) {}
			}
			if (e.getNewValue() == null && e.getOldValue() != null) {
				try {
					cs = (ChatServer) e.getOldValue();
					System.out.println(cs.getName() + " disconnected");
				} catch (Exception ex) {}
			}
		}

	}

}
