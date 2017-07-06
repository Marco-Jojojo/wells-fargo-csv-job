package com.peiwc.billing.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Objects;

/**
 * Embedded id for SprDba
 *
 */
@Embeddable
public class SprDbaPK implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "SUBMISSION_NUMBER")
	private int submissionNumber;

	@Column(name = "ENTITY_NUMBER")
	private int entityNumber;

	@Column(name = "DBA_NUMBER")
	private int dbaNumber;

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
	 * @return dbaNumber
	 */
	public int getDbaNumber() {
		return dbaNumber;
	}

	/**
	 * @param dbaNumber
	 */
	public void setDbaNumber(final int dbaNumber) {
		this.dbaNumber = dbaNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(submissionNumber, entityNumber, dbaNumber);
	}

	@Override
	public boolean equals(final Object obj) {
		boolean equals = true;
		if (obj == null) {
			equals = false;
		} else {
			if (obj instanceof PolicyMasterPK) {
				equals = false;
			} else {
				final SprDbaPK other = (SprDbaPK) obj;
				equals = Objects.equal(other.submissionNumber, this.submissionNumber)
						&& Objects.equal(other.entityNumber, this.entityNumber)
						&& Objects.equal(other.dbaNumber, this.dbaNumber);

			}
		}
		return equals;
	}

}
