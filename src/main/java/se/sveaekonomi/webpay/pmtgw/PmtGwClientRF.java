package se.sveaekonomi.webpay.pmtgw;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.slf4j.Logger;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class PmtGwClientRF {

	public static Logger clientLog = org.slf4j.LoggerFactory.getLogger("PmtGwClientRF");

	public static DateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd");
	
	private String merchantId;
	private String secretWord;
	private String serverName;
	
	
	private Configurations configs = new Configurations();
	
	private	Retrofit	retroFit = null;
	private PmtGwService service = null;

	
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
		
		serverName = fc.getString("server");
		merchantId = fc.getString("merchantId");
		secretWord = fc.getString("secretWord");
		
	}	
	
	/**
	 * Initializes client with current values. loadConfig must have been called before.
	 * 
	 */
	public void init() {
		init(serverName, merchantId, secretWord);
	}
	
	/**
	 * Initializes client
	 */
	public void init(String serverName, String merchantId, String secretWord) {

		this.serverName = serverName;
		this.merchantId = merchantId;
		this.secretWord = secretWord;
		
		// Disable SNI to prevent SSL-name problem
		System.setProperty("jsse.enableSNIExtension", "false");
		
		SimpleXmlConverterFactory converter = SimpleXmlConverterFactory.create();
		
		retroFit = new Retrofit.Builder().baseUrl(this.serverName)
				// .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(converter)
				.build();

		service = retroFit.create(PmtGwService.class);		
		
	}
	
	/**
	 * Get reconciliation report
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws IOException
	 */
	public String getPaymentMethods() throws IOException {
	
		String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" 
						+"<getpaymentmethods>\n" +
						"<merchantid>" + merchantId + "</merchantid>\n" + 
						"</getpaymentmethods>";

		String base64msg = PmtGwUtil.base64encodeMsg(message);
		
		
		String mac = PmtGwUtil.calculateMac(base64msg, secretWord);
		
		if (clientLog.isDebugEnabled()) {
			clientLog.debug("Server: " + serverName);
			clientLog.debug("Message in plain xml: \n" + message);
			clientLog.debug("MerchantID : " + merchantId);
			clientLog.debug("Message in base64: \n" + base64msg);
			clientLog.debug("SHA512 digest: " + mac);
		}
		
		Call<ResponseBody> call = service.getPaymentMethods(
				merchantId, 
				base64msg, 
				mac);
		
		Response<ResponseBody> result = call.execute();
		
		String resultMsg = null; 
		
		

		if (result.errorBody()!=null) {
			clientLog.debug(result.errorBody().string());
			resultMsg = result.errorBody().string();
		} else {
			resultMsg = result.body().string();
			clientLog.debug(result.message());
			clientLog.debug(resultMsg);
			clientLog.debug(result.raw().toString());
		}
		
		return resultMsg;
		
	}
	
	public String getReconcilationReport(Date fromDate, Date toDate) throws IOException {
		
		
		String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" 
						+"<getreconciliationreport>\n" +
						"<fromdate>" + dfmt.format(fromDate) + "</fromdate>\n" + 
						"<todate>" + dfmt.format(toDate) + "</todate>\n" + 
						"</getreconcilationreport>";

		String base64msg = PmtGwUtil.base64encodeMsg(message);
		
		String mac = PmtGwUtil.calculateMac(base64msg, secretWord); 
		
		Call<ResponseBody> call = service.getReconciliationReport(
				merchantId, 
				base64msg, 
				mac);
		
		Response<ResponseBody> result = call.execute();
		
		String resultMsg = null;
		
		clientLog.debug(result.message());
		clientLog.debug(result.raw().toString());
		
		if (result.errorBody()!=null) {
			clientLog.debug(result.errorBody().string());
			resultMsg = result.errorBody().string();
		} else {
			resultMsg = result.message();	
		}
		
		return resultMsg;
		
	}
	
	
	
}
