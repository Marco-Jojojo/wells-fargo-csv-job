package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * error log class, this is used to add detail of records that could not be
 * processed.
 */
@Entity
@Table(name = "WF_MAM_ERR_LOG")
public class WFMamErrLog {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "ID_ERR_LOG")
	private int id;

	@Column(name = "CYCLE_NUMBER")
	private int cycleNumber;

	@Column(name = "SEQUENCE_NUMBER")
	private int sequenceNumber;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STATUS")
	private String status;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

}