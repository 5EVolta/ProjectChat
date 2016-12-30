package server.model.loginandregister;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class XMLLogin implements LoginUtility {
	private CredentialsLoader loader;
	private Map<String, String> credentials;

	public XMLLogin() {
		credentials = Collections.synchronizedMap(new HashMap<>());
		loader = new CredentialsLoader();
		try {
			credentials = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized boolean login(String userId, String password) {
		password = Hasher.hash(password);
		return credentials.containsKey(userId) && 
				credentials.get(userId).equals(password);
	}

}
