package se.sveaekonomi.webpay.pmtgw;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PmtGwUtil {

	public static String calculateMac(String message, String secretWord) throws UnsupportedEncodingException {

		String combined = message + secretWord;
		String result = null;
		String salt = Integer.toHexString((int)Math.round(Math.random()*10000));
	    try {
	         MessageDigest md = MessageDigest.getInstance("SHA-512");
	         md.update(salt.getBytes("UTF-8"));
	         byte[] bytes = md.digest(combined.getBytes("UTF-8"));
	         StringBuilder sb = new StringBuilder();
	         for(int i=0; i< bytes.length ;i++){
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	         }
	         result = sb.toString();
	        } 
	       catch (NoSuchAlgorithmException e){
	        e.printStackTrace();
	       }
	    return result;
		
	}
	
	public static String base64encodeMsg(String message) {
		
		return Base64.getEncoder().encodeToString(message.getBytes());
		
	}
	
	
}
