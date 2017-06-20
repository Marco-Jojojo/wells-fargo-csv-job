package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POLICY_MASTER")
public class PolicyMaster {

	@Id
	@Column(name = "POLICY_NUMBER")
	private String policy_number;

	@Column(name = "SUBMISSION_NUMBER")
	private String submission_number;

	public String getPolicy_number() {
		return policy_number;
	}

	public void setPolicy_number(String value) {
		this.policy_number = value;
	}

	public String getSubmission_number() {
		return submission_number;
	}

	public void setSubmission_number(String value) {
		this.submission_number = value;
	}

}
