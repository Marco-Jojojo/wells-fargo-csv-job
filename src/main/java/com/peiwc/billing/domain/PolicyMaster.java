package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity for billing part 2
 *
 */
@Entity
@Table(name = "POLICY_MASTER")
public class PolicyMaster {

	@EmbeddedId
	private PolicyMasterPK id;

	@Column(name = "SUBMISSION_NUMBER")
	private int submissionNumber;

	@Column(name = "STATUS_CODE")
	private int statusCode;

	/**
	 * @return id
	 */
	public PolicyMasterPK getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(final PolicyMasterPK id) {
		this.id = id;
	}

	/**
	 * @return submissionNumber
	 */
	public int getSubmissionNumber() {
		return submissionNumber;
	}

	/**
	 * @param submissionNumber
	 */
	public void setSubmissionNumber(final int submissionNumber) {
		this.submissionNumber = submissionNumber;
	}

	/**
	 * @return statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 */
	public void setStatusCode(final int statusCode) {
		this.statusCode = statusCode;
	}

}
