package se.sveaekonomi.webpay.pmtgw.test;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.net.URL;

import javax.xml.bind.JAXB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.sveaekonomi.webpay.pmtgw.PmtGwUtil;
import se.sveaekonomi.webpay.pmtgw.entity.PaymentMethod;
import se.sveaekonomi.webpay.pmtgw.entity.PmtGwResponse;
import se.sveaekonomi.webpay.pmtgw.ws.Base64EncodedResponse;

public class TestXMLResponse {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testXMLResponse() throws Exception {

		String message = null;
		
		URL url2 = ClassLoader.getSystemResource("sample-xml-response.xml");
		
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
		} else {
			fail("Can't find file sample-xml-response.xml in classpath");
			return;
		}
		
		Base64EncodedResponse response = JAXB.unmarshal(new StringReader(message), Base64EncodedResponse.class);

		String sveaResponse = null;
		
		if (response.getMessage()!=null && response.getMessage().trim().length()>0) {
			sveaResponse = PmtGwUtil.base64decodeMsg(response.getMessage());
			System.out.println(sveaResponse);
			
			// Try to parse response
			PmtGwResponse r = JAXB.unmarshal(new StringReader(sveaResponse), PmtGwResponse.class);
			if (r!=null) {
				System.out.println("Statuscode : " + r.getStatusCode());
				if (r.getPaymentMethods().size()>0) {
					for (PaymentMethod p : r.getPaymentMethods()) {
						System.out.println(p.getPaymentMethod());
					}
				}
			}
			
		} else {
			fail("Failed to parse xml.");
		}
		
	}

}
