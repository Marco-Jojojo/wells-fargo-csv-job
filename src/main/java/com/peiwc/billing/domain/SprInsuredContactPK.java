package com.peiwc.billing.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Objects;

/**
 * Embedded if for SprInsuredContact
 *
 */
@Embeddable
public class SprInsuredContactPK implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CONTACT_CODE_ID")
	private int contectCodeId;

	@Column(name = "SUBMISSION_NUMBER")
	private int submissionNumber;

	/**
	 * @return contectCodeId
	 */
	public int getContectCodeId() {
		return contectCodeId;
	}

	/**
	 * @param contectCodeId
	 */
	public void setContectCodeId(final int contectCodeId) {
		this.contectCodeId = contectCodeId;
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

	@Override
	public int hashCode() {
		return Objects.hashCode(contectCodeId, submissionNumber);
	}

	@Override
	public boolean equals(final Object obj) {
		boolean equals = true;
		if (obj == null) {
			equals = false;
		} else {
			if (!(obj instanceof SprInsuredContactPK)) {
				equals = false;
			} else {
				final SprInsuredContactPK other = (SprInsuredContactPK) obj;
				equals = Objects.equal(other.contectCodeId, this.contectCodeId)
						&& Objects.equal(this.submissionNumber, other.submissionNumber);
			}
		}
		return equals;
	}

}
