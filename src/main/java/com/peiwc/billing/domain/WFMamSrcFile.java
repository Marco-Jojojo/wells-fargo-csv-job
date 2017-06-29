package com.peiwc.billing.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Wfmamsrcfile jpa bean for containing values to read from source table
 * WF_MAM_SRC_FILE, these results are going to be stored in a csv file.
 *
 */
@Entity
@Table(name = "WF_MAM_SRC_FILE")
public class WFMamSrcFile {

	@EmbeddedId
	private WFMamSrcFilePK id;
	@Column(name = "REFERENCE_NUMBER")
	private String referenceNumber;
	@Column(name = "SECONDARY_AUTH")
	private String secondaryAuth;
	@Column(name = "CONSOLIDATED_NAME")
	private String consolidatedName;
	@Temporal(TemporalType.DATE)
	@Column(name = "DUE_DATE")
	private Date dueDate;
	@Column(name = "AMOUNT_DUE")
	private float amountDue;
	@Column(name = "INVOICE_NUMBER")
	private String invoiceNumber;
	@Temporal(TemporalType.DATE)
	@Column(name = "INVOICE_DATE")
	private Date invoiceDate;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "ADDRESS_2")
	private String address2;
	@Column(name = "CITY")
	private String city;
	@Column(name = "STATE")
	private String state;
	@Column(name = "ZIP")
	private String zip;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "STATUS_INVOICE")
	private String statusInvoice;
	@Column(name = "SUBMISSION_NUMBER")
	private int submissionNumber;

	/**
	 * @return the id
	 */
	public WFMamSrcFilePK getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final WFMamSrcFilePK id) {
		this.id = id;
	}

	/**
	 * @return the referenceNumber
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}

	/**
	 * @param referenceNumber
	 *            the referenceNumber to set
	 */
	public void setReferenceNumber(final String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	/**
	 * @return the secondaryAuth
	 */
	public String getSecondaryAuth() {
		return secondaryAuth;
	}

	/**
	 * @param secondaryAuth
	 *            the secondaryAuth to set
	 */
	public void setSecondaryAuth(final String secondaryAuth) {
		this.secondaryAuth = secondaryAuth;
	}

	/**
	 * @return the consolidatedName
	 */
	public String getConsolidatedName() {
		return consolidatedName;
	}

	/**
	 * @param consolidatedName
	 *            the consolidatedName to set
	 */
	public void setConsolidatedName(final String consolidatedName) {
		this.consolidatedName = consolidatedName;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(final Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the amountDue
	 */
	public float getAmountDue() {
		return amountDue;
	}

	/**
	 * @param amountDue
	 *            the amountDue to set
	 */
	public void setAmountDue(final float amountDue) {
		this.amountDue = amountDue;
	}

	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber
	 *            the invoiceNumber to set
	 */
	public void setInvoiceNumber(final String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate
	 *            the invoiceDate to set
	 */
	public void setInvoiceDate(final Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(final String zip) {
		this.zip = zip;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(final String phone) {
		this.phone = phone;
	}

	/**
	 * @return the statusInvoice
	 */
	public String getStatusInvoice() {
		return statusInvoice;
	}

	/**
	 * @param statusInvoice
	 *            the statusInvoice to set
	 */
	public void setStatusInvoice(final String statusInvoice) {
		this.statusInvoice = statusInvoice;
	}

	/**
	 * @return the submissionNumber
	 */
	public int getSubmissionNumber() {
		return submissionNumber;
	}

	/**
	 * @param submissionNumber
	 *            the submissionNumber to set
	 */
	public void setSubmissionNumber(final int submissionNumber) {
		this.submissionNumber = submissionNumber;
	}

}
