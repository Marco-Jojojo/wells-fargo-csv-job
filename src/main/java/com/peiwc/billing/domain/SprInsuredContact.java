package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SPR_INSURED_CONTACT_")
public class SprInsuredContact {

	@Id
	@Column(name = "SUBMISSION_NUMBER")
	private String submission_number;

	@Column(name = "FIRST_NAME")
	private String first_name;

	@Column(name = "LAST_NAME")
	private String last_name;

	@Column(name = "EMAIL_ADDRESS")
	private String email_address;

	@Column(name = "PHONE_AREA_CODE")
	private String phone_area_code;

	@Column(name = "PHONE_PREFIX")
	private String phone_prefix;

	@Column(name = "PHONE_SUFFIX")
	private String phone_suffix;

	public String getSubmission_number() {
		return submission_number;
	}

	public void setSubmission_number(String submission_number) {
		this.submission_number = submission_number;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getPhone_area_code() {
		return phone_area_code;
	}

	public void setPhone_area_code(String phone_area_code) {
		this.phone_area_code = phone_area_code;
	}

	public String getPhone_prefix() {
		return phone_prefix;
	}

	public void setPhone_prefix(String phone_prefix) {
		this.phone_prefix = phone_prefix;
	}

	public String getPhone_suffix() {
		return phone_suffix;
	}

	public void setPhone_suffix(String phone_suffix) {
		this.phone_suffix = phone_suffix;
	}

}
