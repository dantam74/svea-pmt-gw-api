package se.sveaekonomi.webpay.pmtgw.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "reconciliationtransaction")
public class ReconciliationTransaction {

	private String transactionId;
	private String customerRefNo;
	private String paymentMethod;
	private String amountStr;
	private String currency;
	private String timeStr;
	
	@XmlElement(name="transactionid")
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	@XmlElement(name="customerrefno")
	public String getCustomerRefNo() {
		return customerRefNo;
	}
	public void setCustomerRefNo(String customerRefNo) {
		this.customerRefNo = customerRefNo;
	}
	@XmlElement(name="paymentmethod")
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	@XmlElement(name="amount")
	public String getAmountStr() {
		return amountStr;
	}
	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}
	
	@XmlElement(name="currency")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@XmlElement(name="time")
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	
	
	
	
}
