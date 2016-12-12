package server.model;

public class UserUtility {
	
	private LoginUtility login;
	//private RegisterUtility register;
	private static UserUtility instance;
	
	public static UserUtility getInstance(){
		if(instance == null){
			synchronized(instance){
				if(instance == null){
					instance = new UserUtility();
				}
			}
		}
		return instance;
	}
	
	private UserUtility(){
	}
	
	public boolean login(String userId, String password){
		return login.login(userId, password);
	}

}
