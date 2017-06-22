package com.peiwc.billing.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SprInsuredContactPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CONTACT_CODE_ID")
	private int contectCodeId;
	
	@Column(name = "SUBMISSION_NUMBER")
	private int submissionNumber;

	public int getContectCodeId() {
		return contectCodeId;
	}

	public void setContectCodeId(int contectCodeId) {
		this.contectCodeId = contectCodeId;
	}

	public int getSubmissionNumber() {
		return submissionNumber;
	}

	public void setSubmissionNumber(int submissionNumber) {
		this.submissionNumber = submissionNumber;
	}

}
