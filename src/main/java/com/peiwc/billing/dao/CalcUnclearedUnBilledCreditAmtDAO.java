package com.peiwc.billing.dao;

import java.util.Date;
import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * @author jolivarria
 *
 */
public interface CalcUnclearedUnBilledCreditAmtDAO {

	/**
	 * @return List<WFMamSrcFile>
	 */
	List<WFMamSrcFile> findAll();

	/**
	 * @param cycleNumber
	 * @param submissionNumber
	 * @return List<WFMamSrcFile>
	 */
	List<WFMamSrcFile> findOneByDBI1(int cycleNumber, int submissionNumber);

	/**
	 * @param cycleNumber
	 * @param submissionNumber
	 * @param invoiceNumber
	 * @return List<WFMamSrcFile>
	 */
	List<WFMamSrcFile> findOneByDBI0(int cycleNumber, int submissionNumber, String invoiceNumber);

	/**
	 * @param cycleNumber
	 * @param submissionNumber
	 * @param amtDue
	 */
	void updateDBI1(int cycleNumber, int submissionNumber, float amtDue);

	/**
	 * @param cycleNumber
	 * @param submissionNumber
	 * @param invoiceNumber
	 * @param amtDue
	 */
	void updateDBI0(int cycleNumber, int submissionNumber, String invoiceNumber, float amtDue);

	/**
	 * @param wfMamSrcFile
	 */
	void create(WFMamSrcFile wfMamSrcFile);

	/**
	 * @param invoiceNumber
	 * @return Date
	 */
	Date getInvoiceDate(String invoiceNumber);

	/**
	 * @param cycleNumber
	 * @return int
	 */
	int getMaxSequenceNumber(int cycleNumber);

}
