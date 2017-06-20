package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SP_BILL_STMT_CTRL")
public class SpBillStmtCtrl {

	
	@Column(name = "POLICY_NUMBER")
	private String policyNumber;

	@Column(name = "AMOUNT_DUE")
	private String amountDue;

	@Column(name = "DUE_DATE")
	private String dueDate;

	@Column(name = "INVOICE_NUMBER")
	private String invoiceNumber;

	@Column(name = "ENTRY_DATE")
	private String entryDate;

	public String getPolicy_number() {
		return policyNumber;
	}

	public void setPolicy_number(String policy_number) {
		this.policyNumber = policy_number;
	}

	public String getAmount_due() {
		return amountDue;
	}

	public void setAmount_due(String amount_due) {
		this.amountDue = amount_due;
	}

	public String getDue_date() {
		return dueDate;
	}

	public void setDue_date(String due_date) {
		this.dueDate = due_date;
	}

	public String getInvoice_number() {
		return invoiceNumber;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoiceNumber = invoice_number;
	}

	public String getEntry_date() {
		return entryDate;
	}

	public void setEntry_date(String entry_date) {
		this.entryDate = entry_date;
	}

}
