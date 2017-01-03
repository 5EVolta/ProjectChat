package server.model.data;

public abstract class LoginUtility {

	public boolean login(String userId, String password) {
		String hashPass = Hasher.hash(password);
		return checkCredentials(userId, hashPass);
	}
	
	protected abstract boolean checkCredentials(String userId, String hashedPassword);

}
