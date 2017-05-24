package se.sveaekonomi.webpay.pmtgw;


public class PmtGwClientRFMain {

	private java.util.Date fromDate = null;
	private java.util.Date untilDate = null;
	private String configFile = null;
	
	
	public void printHelp() {
		
		System.out.println("Usage: PmtGwClientRFMain configfile fromdate [untildate]");
		System.out.println("Date in format yyyy-MM-dd");
		
	}
	
	public StringBuffer runQuery() throws Exception {
		
		PmtGwClientRF client = new PmtGwClientRF();

		client.loadConfig(configFile);
		client.init();
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
				main.configFile = args[0];
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
