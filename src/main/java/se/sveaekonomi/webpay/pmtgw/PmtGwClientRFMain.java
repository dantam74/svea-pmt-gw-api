package se.sveaekonomi.webpay.pmtgw;

import java.io.File;
import java.net.URL;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;


public class PmtGwClientRFMain {

	private Configurations configs = new Configurations();

	private java.util.Date fromDate = null;
	private java.util.Date untilDate = null;
	
	private String 	server;
	private String 	merchantId;
	private String	secretWord;
	
	public void loadConfig(String configfile) throws Exception {

		URL url = null;
		
		// Try absolute path first
		File cf = new File(configfile);
		if (!cf.exists()) {
			// Try read as resource
			url = ClassLoader.getSystemResource(configfile);
		} else {
			url = new URL(cf.getAbsolutePath());
		}

		if (url==null) {
			System.out.println("Can't find configfile: " + configfile);
			System.exit(-1);
		}
		
		XMLConfiguration fc = configs.xml(url);
		
		server = fc.getString("server");
		merchantId = fc.getString("merchantId");
		secretWord = fc.getString("secretWord");
		
	}	
	
	public void printHelp() {
		
		System.out.println("Usage: PmtGwClientRFMain configfile fromdate [untildate]");
		System.out.println("Date in format yyyy-MM-dd");
		
	}
	
	public StringBuffer runQuery() throws Exception {
		
		PmtGwClientRF client = new PmtGwClientRF();
		client.init(server, merchantId, secretWord);
		// StringBuffer result = new StringBuffer(client.getReconcilationReport(fromDate, untilDate));
		StringBuffer result = new StringBuffer(client.getPaymentMethods());
		return result;
	}
	
	
	public static void main(String[] args) {

		PmtGwClientRFMain main = new PmtGwClientRFMain();
		
		if (args==null || args.length==0) {
			main.printHelp();
		}
		
		try {
		
			if (args.length>0) {
				main.loadConfig(args[0]);
			}
			
			if (args.length>1) {
				main.fromDate = PmtGwClientRF.dfmt.parse(args[1]); 
			} else {
				main.printHelp();
				System.exit(0);
			}
			
			if (args.length>2) {
				main.untilDate = PmtGwClientRF.dfmt.parse(args[2]);
			}
			if (main.untilDate==null)
				main.untilDate = main.fromDate;

			StringBuffer result = main.runQuery();
			System.out.println(result.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
