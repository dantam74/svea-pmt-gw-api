package se.sveaekonomi.webpay.pmtgw;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PmtGwUtil {

	/**
	 * Calculates a MAC (Message Authentication Key) for given message and secret word.
	 * 
	 * @param message		This is a base64 encoded message.
	 * @param secretWord	This is a presumably base64 encoded secret word that's given
	 * 						to you by Svea Ekonomi.
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String calculateMac(String message, String secretWord) throws UnsupportedEncodingException {

		String combined = message + secretWord;
		String result = null;
		String salt = Integer.toHexString((int)Math.round(Math.random()*10000));
	    try {
	         MessageDigest md = MessageDigest.getInstance("SHA-512");
	         // md.update(salt.getBytes("UTF-8"));
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
	

	/**
	 * Encodes given message to Base64
	 * 
	 * @param message		An XML-message (plain text) to be encoded.
	 * @return				A base64 encoded message.
	 */
	
	public static String base64encodeMsg(String message) {
		return Base64.getEncoder().encodeToString(message.getBytes());
	}
	
	
	/**
	 * Decodes a base64 message. 
	 * 
	 * @param message
	 * @return
	 */
	public static String base64decodeMsg(String message) {
		
		StringBuffer buf = new StringBuffer();
		// Strip message from spaces, newlines etc
		char c;
		for (int i = 0; i<message.length(); i++) {
			c = message.charAt(i);
			switch(c) {
				case ' ' :
				case '\n' :
				case '\r' :
				case '\t' :
					continue;
				default:
					buf.append(c);
			}
		}
		String result = new String(Base64.getDecoder().decode(buf.toString())); 
		
		return result;
		
	}
	
	/**
	 * Creates a form request
	 */
	public static String createForm(String action, String merchantId, String message, String mac) {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("<form name=\"form\" method=\"post\" action=\"" + action + "\">\n");
		buf.append("<input type=\"hidden\" name=\"merchantid\" value=\"" + merchantId + "\"/>\n");
		buf.append("<input type=\"hidden\" name=\"message\" value=\"" + message + "\"/>\n");
		buf.append("<input type=\"hidden\" name=\"mac\" value=\"" + mac + "\"/>\n");
		
		return buf.toString();
		
	}
	
	
}
