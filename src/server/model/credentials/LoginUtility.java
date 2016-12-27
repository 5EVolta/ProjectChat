package server.model.credentials;

public abstract class LoginUtility {

	public abstract boolean login(String userId, String password) throws Exception;

	public static LoginUtility getLoginUtility(LoginUtilityType type) {
		switch (type) {
		case DATABASE:
			return new DataBaseLogin();
		case XML:
			return new XmlLogin();
		case TEST:
			return new TestLogin();
		}
		throw new IllegalArgumentException("Invalid LoginUtilityType");
	}

}
