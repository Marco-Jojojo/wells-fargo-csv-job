package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SP_BILL_STMT_CTRL")
public class SpBillStmtCtrl {

	@Id
	@Column(name = "POLICY_NUMBER")
	private String policy_number;

	@Column(name = "AMOUNT_DUE")
	private String amount_due;

	@Column(name = "DUE_DATE")
	private String due_date;

	@Column(name = "INVOICE_NUMBER")
	private String invoice_number;

	@Column(name = "ENTRY_DATE")
	private String entry_date;

	public String getPolicy_number() {
		return policy_number;
	}

	public void setPolicy_number(String policy_number) {
		this.policy_number = policy_number;
	}

	public String getAmount_due() {
		return amount_due;
	}

	public void setAmount_due(String amount_due) {
		this.amount_due = amount_due;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	public String getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}

}
