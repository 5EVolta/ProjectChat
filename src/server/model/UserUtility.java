package server.model;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UserUtility {
	
	private LoginUtility login;
	//private RegisterUtility register;
	private static UserUtility instance = new UserUtility();
	
	public static UserUtility getInstance(){
		return instance;
	}
	
	private UserUtility(){
		login = new LoginUtility();
	}
	
	public boolean login(String userId, String password){
		/*
		try {
			return login.login(userId, password);
		} catch (NoSuchAlgorithmException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		*/
		return true;
	}

}
