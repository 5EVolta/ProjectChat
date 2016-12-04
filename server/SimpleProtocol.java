package server;

public class SimpleProtocol {

	public static String getSender(String fullString) {
		return fullString.split("@")[0];
	}

	public static String getRecipient(String fullString) {
		String str = fullString.split("@")[1];
		return str.split(":")[0];
	}

	public static String getMess(String fullString) {
		return fullString.substring(fullString.indexOf(':') + 1);
	}

}
