package server.model.loginandregister;

public class UserUtility {
	
	private LoginUtility login;
	//private RegisterUtility register;
	private static UserUtility instance = new UserUtility();
	
	public static UserUtility getInstance(){
		return instance;
	}
	
	private UserUtility(){
		login = new XMLLogin();
	}
	
	public synchronized boolean login(String userId, String password){
		try {
			return login.login(userId, password);
		} catch (Exception e) {
			System.out.println("Login exception");
			e.printStackTrace();
		}
		return false;
	}

}
