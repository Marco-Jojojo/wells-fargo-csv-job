package com.peiwc.billing.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SprLocationPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "SUBMISSION_NUMBER")
	private int submissionNumber;
	
	@Column(name = "ENTITY_NUMBER")
	private int entityNumber;
	
	@Column(name = "LOCATION")
	private String location;

	public int getSubmissionNumber() {
		return submissionNumber;
	}

	public void setSubmissionNumber(int submissionNumber) {
		this.submissionNumber = submissionNumber;
	}

	public int getEntityNumber() {
		return entityNumber;
	}

	public void setEntityNumber(int entityNumber) {
		this.entityNumber = entityNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
