package se.sveaekonomi.webpay.pmtgw.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Container class for keeping payment methods.
 * 
 * @author Daniel Tamm
 *
 */

@XmlType(name = "paymentmethods")
public class PaymentMethod {

	public static final String SVEACARDPAY_PF = "SVEACARDPAY_PF";
	public static final String SVEAINVOICEEU_SE = "SVEAINVOICEEU_SE";
	public static final String SVEAINVOICEEU_NO = "SVEAINVOICEEU_NO";
	public static final String SVEAINVOICEEU_FI = "SVEAINVOICEEU_FI";
	public static final String SVEAINVOICEEU_DE = "SVEAINVOICEEU_DE";
	public static final String SVEAINVOICEEU_NL = "SVEAINVOICEEU_NL";
	
	private String paymentMethod;

	@XmlElement(name = "paymentmethod")
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	
}
