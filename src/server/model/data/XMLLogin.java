package server.model.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class XMLLogin extends LoginUtility {
	private XMLLoader loader;
	private Map<String, String> credentials;

	public XMLLogin() {
		credentials = Collections.synchronizedMap(new HashMap<>());
		loader = new XMLLoader();
		try {
			credentials = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean checkCredentials(String userId, String hashedPassword) {
		return credentials.containsKey(userId) && 
				credentials.get(userId).equals(hashedPassword);
	}

}
