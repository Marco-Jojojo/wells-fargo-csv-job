package com.peiwc.billing.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Objects;

/**
 * Embedded id for SprLocation
 *
 */
@Embeddable
public class SprLocationPK implements Serializable {

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
	 * @return entityNumber
	 */
	public int getEntityNumber() {
		return entityNumber;
	}

	/**
	 * @param entityNumber
	 */
	public void setEntityNumber(final int entityNumber) {
		this.entityNumber = entityNumber;
	}

	/**
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(submissionNumber, entityNumber, location);
	}

	@Override
	public boolean equals(final Object obj) {
		boolean equals = true;
		if (obj == null) {
			equals = false;
		} else {
			if (!(obj instanceof SprLocationPK)) {
				equals = false;
			} else {
				final SprLocationPK other = (SprLocationPK) obj;
				equals = Objects.equal(other.submissionNumber, this.submissionNumber)
						&& Objects.equal(other.entityNumber, this.entityNumber)
						&& Objects.equal(other.location, this.location);
			}
		}
		return equals;
	}

}
