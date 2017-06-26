package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "POLICY_MASTER")
public class PolicyMaster {

	@EmbeddedId
	private PolicyMasterPK id;

	@Column(name = "SUBMISSION_NUMBER")
	private int submissionNumber;

	@Column(name = "STATUS_CODE")
	private int statusCode;

	public PolicyMasterPK getId() {
		return id;
	}

	public void setId(final PolicyMasterPK id) {
		this.id = id;
	}

	public int getSubmissionNumber() {
		return submissionNumber;
	}

	public void setSubmissionNumber(final int submissionNumber) {
		this.submissionNumber = submissionNumber;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(final int statusCode) {
		this.statusCode = statusCode;
	}

}
