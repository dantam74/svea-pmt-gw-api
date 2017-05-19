package se.sveaekonomi.webpay.pmtgw.test;

// import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.sveaekonomi.webpay.pmtgw.PmtGwUtil;

public class TestPmtGwUtil {

	private Configurations configs = new Configurations();	
	private String	message;
	private String	secretWord;
	private String	base64message;
	private String	baseEncodedMessage;
	
	@Before
	public void setUp() throws Exception {
		

		URL url = ClassLoader.getSystemResource("config-template.xml");
		XMLConfiguration fc = configs.xml(url);

		secretWord = fc.getString("secretWord");
		base64message = fc.getString("base64message");
		
		URL url2 = ClassLoader.getSystemResource("example-xml-message.xml");
		
		if (url2!=null) {
			// Read file
			File file = new File(url2.getFile());
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer str = new StringBuffer();
			String line;
			while ((line = reader.readLine())!=null) {
				str.append(line + "\n");
			}
			reader.close();
			message = str.toString();
		}
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBase64encodeMsg() {
		baseEncodedMessage = PmtGwUtil.base64encodeMsg(message);
		System.out.println("Base64 of examples-xml-message: ");
		System.out.println(baseEncodedMessage);
	}
	
	@Test
	public void testBase64decodeMsg() {
		String baseDecodedMessage = PmtGwUtil.base64decodeMsg(base64message);
		System.out.println("Base64decoded sample message: ");
		System.out.println(baseDecodedMessage);
	}
	
	
	@Test
	public void testCalculateMac() {
		try {
			String mac = PmtGwUtil.calculateMac(base64message, secretWord);
			System.out.println("Mac: " + mac);
		} catch (Exception e) {
			
		}
	}
		



}
