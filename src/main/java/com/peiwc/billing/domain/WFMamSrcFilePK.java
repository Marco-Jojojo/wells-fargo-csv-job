package com.peiwc.billing.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Embedded id for WFMamSrcFile
 *
 */
@Embeddable
public class WFMamSrcFilePK implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6799455314066296091L;
	@Column(name = "CYCLE_NUMBER")
	private int cycleNumber;
	@Column(name = "SEQUENCE_NUMBER")
	private int sequenceNumber;

	public int getCycleNumber() {
		return cycleNumber;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setCycleNumber(final int cycleNumber) {
		this.cycleNumber = cycleNumber;
	}

	public void setSequenceNumber(final int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

}
