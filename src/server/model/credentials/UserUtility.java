package server.model.credentials;

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
		login = LoginUtility.getLoginUtility(LoginUtilityType.XML);
	}
	
	public synchronized boolean login(String userId, String password){
		try {
			return login.login(userId, password);
		} catch (Exception e) {
			System.out.println("Login exception in db connection :)");
			e.printStackTrace();
		}
		return false;
	}

}
