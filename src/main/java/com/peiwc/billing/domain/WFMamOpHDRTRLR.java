package com.peiwc.billing.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * this is the entity bean that contains the data related to master export of
 * csv file to database.
 */
@Entity
@Table(name = "WF_MAM_OP_HDR_TRLR")
public class WFMamOpHDRTRLR {

	@Id
	@Column(name = "CYCLE_NUMBER")
	private Integer cycleNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATION_DATE")
	private Date creationDate;

	@Column(name = "TOTAL_RECORD_COUNT")
	private int totalRecordCount;

	@Column(name = "FILENAME", length = 250)
	private String fileName;

	@Column(name = "ERROR_MSG", length = 100)
	private String statusMessage;

	@Column(name = "STATUS", length = 20)
	private String status;

	/**
	 *
	 */
	public WFMamOpHDRTRLR() {
		fileName = "";
		statusMessage = "";
		status = "";
	}

	/**
	 * @return the cycleNumber
	 */
	public Integer getCycleNumber() {
		return cycleNumber;
	}

	/**
	 * @param cycleNumber
	 *            the cycleNumber to set
	 */
	public void setCycleNumber(final Integer cycleNumber) {
		this.cycleNumber = cycleNumber;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the totalRecordCount
	 */
	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	/**
	 * @param totalRecordCount
	 *            the totalRecordCount to set
	 */
	public void setTotalRecordCount(final int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	/**
	 * @return String
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage
	 */
	public void setStatusMessage(final String statusMessage) {
		this.statusMessage = statusMessage;
	}

	/**
	 * gets the status
	 *
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * sets the status
	 *
	 * @param status
	 *            parameter
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}
}
