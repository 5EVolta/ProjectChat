package server.model;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

public class Hasher {
	
	public static String hash(String clearText) throws NoSuchAlgorithmException{
		String hashedText = "";
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");  
		messageDigest.update(clearText.getBytes());
		hashedText = Hex.encodeHexString(messageDigest.digest());
		return hashedText;
	}

}