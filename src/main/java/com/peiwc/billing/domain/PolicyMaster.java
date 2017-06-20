package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POLICY_MASTER")
public class PolicyMaster {

	
	@Column(name = "POLICY_NUMBER")
	private String policyNumber;

	@Column(name = "SUBMISSION_NUMBER")
	private String submissionNumber;

	public String getPolicy_number() {
		return policyNumber;
	}

	public void setPolicy_number(String value) {
		this.policyNumber = value;
	}

	public String getSubmission_number() {
		return submissionNumber;
	}

	public void setSubmission_number(String value) {
		this.submissionNumber = value;
	}

}
