package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SPR_LOCATION")
public class SprLocation {

	@Column(name = "SUBMISSION_NUMBER")
	private String submissionNumber;

	@Column(name = "ADDR_1")
	private String addr1;

	@Column(name = "ADDR_2")
	private String addr2;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "ZIP1")
	private String zip1;

	public String getSubmission_number() {
		return submissionNumber;
	}

	public void setSubmission_number(String submission_number) {
		this.submissionNumber = submission_number;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr_2() {
		return addr2;
	}

	public void setAddr_2(String addr_2) {
		this.addr2 = addr_2;
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

	public String getZip1() {
		return zip1;
	}

	public void setZip1(String zip1) {
		this.zip1 = zip1;
	}

}
