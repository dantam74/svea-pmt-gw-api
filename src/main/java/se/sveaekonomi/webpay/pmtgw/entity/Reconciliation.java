package se.sveaekonomi.webpay.pmtgw.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "reconciliation")
public class Reconciliation {

	private List<ReconciliationTransaction> reconciliationTransaction = new ArrayList<ReconciliationTransaction>();

	@XmlElement(name = "reconciliationtransaction")
	public List<ReconciliationTransaction> getReconciliationTransaction() {
		return reconciliationTransaction;
	}

	public void setReconciliationTransaction(
			List<ReconciliationTransaction> reconciliationTransaction) {
		this.reconciliationTransaction = reconciliationTransaction;
	}

	
}
