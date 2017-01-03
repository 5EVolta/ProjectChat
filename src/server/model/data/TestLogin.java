package server.model.data;

public class TestLogin extends LoginUtility {

	@Override
	protected boolean checkCredentials(String userId, String hashedPassword) {
		return true;
	}
	
	

}
