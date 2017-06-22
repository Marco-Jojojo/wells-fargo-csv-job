package com.peiwc.billing.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Objects;

/**
 * Embedded id for WFMamSrcFile
 *
 */
@Embeddable
public class WFMamSrcFilePK implements Serializable {

	/**
	 * generated serial version.
	 */
	private static final long serialVersionUID = 6799455314066296091L;

	@Column(name = "CYCLE_NUMBER")
	private int cycleNumber;

	@Column(name = "SEQUENCE_NUMBER")
	private int sequenceNumber;

	/**
	 * @return the cycleNumber
	 */
	public int getCycleNumber() {
		return cycleNumber;
	}

	/**
	 * @param cycleNumber
	 *            the cycleNumber to set
	 */
	public void setCycleNumber(final int cycleNumber) {
		this.cycleNumber = cycleNumber;
	}

	/**
	 * @return the sequenceNumber
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber
	 *            the sequenceNumber to set
	 */
	public void setSequenceNumber(final int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(cycleNumber, sequenceNumber);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		boolean equals = true;
		if (!(obj instanceof WFMamSrcFilePK)) {
			equals = false;
		} else {
			final WFMamSrcFilePK other = (WFMamSrcFilePK) obj;
			equals = Objects.equal(other.getCycleNumber(), this.getCycleNumber())
					&& Objects.equal(other.getSequenceNumber(), this.getSequenceNumber());
		}
		return equals;
	}

}
