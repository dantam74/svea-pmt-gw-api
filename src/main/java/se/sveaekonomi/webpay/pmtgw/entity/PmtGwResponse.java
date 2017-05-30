package se.sveaekonomi.webpay.pmtgw.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This is the actual response from the webservice. This response is embedded in the
 * Base64EncodedResponse in the message attribute.
 * 
 * @see se.sveaekonomi.webpay.pmtgw.ws.Base64EncodedResponse
 *  
 * @author Daniel Tamm
 *
 */

@XmlRootElement(name = "response")
public class PmtGwResponse {

	private List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
	private int	statusCode;
	private String rawXml;

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	@XmlElement(name = "paymentmethods")
	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	
	@XmlElement(name = "statuscode")
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@XmlTransient
	public String getRawXml() {
		return rawXml;
	}

	public void setRawXml(String rawXml) {
		this.rawXml = rawXml;
	}
	
	public String toString() {
		if (rawXml!=null)
			return rawXml;
		return super.toString();
	}
	
	
}
