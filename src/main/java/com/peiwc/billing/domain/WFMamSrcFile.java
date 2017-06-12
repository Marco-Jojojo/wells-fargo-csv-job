package com.peiwc.billing.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( schema="COMPSUPTPROD" , catalog="dbo" , name="WF_MAM_SRC_FILE" )
public class WFMamSrcFile {
	
	@Column(name="CYCLE_NUMBER")
	private int cycleNumber;
	@Column(name="SEQUENCE_NUMBER")
	private int sequenceNumber;
	@Column(name="REFERENCE_NUMBER")
	private String referenceNumber;
	@Column(name="SECONDARY_AUTH")
	private String secondaryAuth;
	@Column(name="CONSOLIDATED_NAME")
	private String consolidatedName;
	@Temporal(TemporalType.DATE)
	@Column(name="DUE_DATE")
	private Date dueDate;
	@Column(name="AMOUNT_DUE")
	private float amountDue;	
	@Column(name="INVOICE_NUMBER")
	private String invoiceNumber;
	@Temporal(TemporalType.DATE)
	@Column(name="INVOICE_DATE")
	private Date invoiceDate;
	@Column(name="EMAIL")
	private String email;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="ADDRESS_2")
	private String address2;
	@Column(name="CITY")
	private String city;
	@Column(name="STATE")
	private String state;
	@Column(name="ZIP")
	private String zip;
	@Column(name="PHONE")
	private String phone;
	
	public int getCycleNumber() {
		return cycleNumber;
	}
	public void setCycleNumber(int cycleNumber) {
		this.cycleNumber = cycleNumber;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getSecondaryAuth() {
		return secondaryAuth;
	}
	public void setSecondaryAuth(String secondaryAuth) {
		this.secondaryAuth = secondaryAuth;
	}
	public String getConsolidatedName() {
		return consolidatedName;
	}
	public void setConsolidatedName(String consolidatedName) {
		this.consolidatedName = consolidatedName;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public float getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(float amountDue) {
		this.amountDue = amountDue;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

}
