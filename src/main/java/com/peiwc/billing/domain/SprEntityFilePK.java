package com.peiwc.billing.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Objects;

/**
 * Embedded id for SprEntityFile
 *
 */
@Embeddable
public class SprEntityFilePK implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "SUBMISSION_NUMBER")
	private int submissionNumber;

	@Column(name = "NEXT_DBA_NUMBER")
	private int nextDbaNumber;

	@Column(name = "ENTITY_NUMBER")
	private int entityNumber;

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
	 * @return nextDbaNumber
	 */
	public int getNextDbaNumber() {
		return nextDbaNumber;
	}

	/**
	 * @param nextDbaNumber
	 */
	public void setNextDbaNumber(final int nextDbaNumber) {
		this.nextDbaNumber = nextDbaNumber;
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

	@Override
	public int hashCode() {
		return Objects.hashCode(submissionNumber, nextDbaNumber, entityNumber);
	}

	@Override
	public boolean equals(final Object obj) {
		boolean equals = true;
		if (obj == null) {
			equals = false;
		} else {
			if (obj instanceof SprEntityFilePK) {
				equals = false;
			} else {
				final SprEntityFilePK other = (SprEntityFilePK) obj;
				equals = Objects.equal(other.submissionNumber, this.submissionNumber)
						&& Objects.equal(other.nextDbaNumber, this.nextDbaNumber)
						&& Objects.equal(other.entityNumber, this.entityNumber);

			}
		}
		return equals;
	}
}
