package client;

public class SimpleProtocol {

	public static String getMittente(String mex) {

		return mex.split("@")[0];
	}

	public static String getMessaggio(String mex) {

		return mex.substring(mex.indexOf(':') + 1);
	}

}
