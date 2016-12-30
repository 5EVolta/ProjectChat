package server.model.loginandregister;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Hasher {
	
	public static String hash(String clearText){
		String hashedText = "";
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}  
		messageDigest.update(clearText.getBytes());
		hashedText = Hex.encodeHexString(messageDigest.digest());
		return hashedText;
	}

}
