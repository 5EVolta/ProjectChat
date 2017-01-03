package server.model.msg;

public class SimpleProtocol {

	public static String getSender(String fullString) {
		return fullString.split("@")[0];
	}

	public static String getRecipient(String fullString) {
		String str = fullString.split("@")[1];
		return str.split(":")[0];
	}

	public static String getMessage(String fullString) {
		return fullString.substring(fullString.indexOf(':') + 1);
	}
	
	public static boolean isValid(String fullString){
		if(fullString.contains(":") && fullString.contains("@")){
			if(!(SimpleProtocol.getSender(fullString).equals("") && 
					SimpleProtocol.getRecipient(fullString).equals(""))){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValidLogin(String fullString){
		return isValid(fullString) && getRecipient(fullString).equals("login");
	}

}
