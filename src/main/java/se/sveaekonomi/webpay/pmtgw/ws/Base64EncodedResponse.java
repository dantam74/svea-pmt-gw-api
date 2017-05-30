package se.sveaekonomi.webpay.pmtgw.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class Base64EncodedResponse {

	private String message;
	private String merchantid;
	private String mac;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
}
