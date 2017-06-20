package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SPR_INSURED_CONTACT_")
public class SprInsuredContact {

	@Column(name = "SUBMISSION_NUMBER")
	private String submissionNumber;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name = "PHONE_AREA_CODE")
	private String phoneAreaCode;

	@Column(name = "PHONE_PREFIX")
	private String phonePrefix;

	@Column(name = "PHONE_SUFFIX")
	private String phoneSuffix;

	public String getSubmission_number() {
		return submissionNumber;
	}

	public void setSubmission_number(String submission_number) {
		this.submissionNumber = submission_number;
	}

	public String getFirst_name() {
		return firstName;
	}

	public void setFirst_name(String first_name) {
		this.firstName = first_name;
	}

	public String getLast_name() {
		return lastName;
	}

	public void setLast_name(String last_name) {
		this.lastName = last_name;
	}

	public String getEmail_address() {
		return emailAddress;
	}

	public void setEmail_address(String email_address) {
		this.emailAddress = email_address;
	}

	public String getPhone_area_code() {
		return phoneAreaCode;
	}

	public void setPhone_area_code(String phone_area_code) {
		this.phoneAreaCode = phone_area_code;
	}

	public String getPhone_prefix() {
		return phonePrefix;
	}

	public void setPhone_prefix(String phone_prefix) {
		this.phonePrefix = phone_prefix;
	}

	public String getPhone_suffix() {
		return phoneSuffix;
	}

	public void setPhone_suffix(String phone_suffix) {
		this.phoneSuffix = phone_suffix;
	}

}
