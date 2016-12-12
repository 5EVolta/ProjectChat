package server.model;

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
		return login.login(userId, password);
	}

}
