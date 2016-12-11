package server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	
	public static String hash(String clearText) throws NoSuchAlgorithmException{
		String hashedText = "";
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");  
		messageDigest.update(clearText.getBytes());
		hashedText = new String(messageDigest.digest());
		return hashedText;
	}

}
