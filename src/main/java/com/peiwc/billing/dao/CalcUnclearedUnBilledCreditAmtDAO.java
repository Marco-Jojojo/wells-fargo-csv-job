package com.peiwc.billing.dao;

import java.util.Date;
import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

public interface CalcUnclearedUnBilledCreditAmtDAO {

	List<WFMamSrcFile> findAll();

	List<WFMamSrcFile> findOneByDBI1(int cycleNumber, int submissionNumber);

	List<WFMamSrcFile> findOneByDBI0(int cycleNumber, int submissionNumber, String invoiceNumber);

	void updateDBI1(int cycleNumber, int submissionNumber, float amtDue);

	void updateDBI0(int cycleNumber, int submissionNumber, String invoiceNumber, float amtDue);

	void create(WFMamSrcFile wfMamSrcFile);

	Date getInvoiceDate(String invoiceNumber);

	int getMaxSequenceNumber(int cycleNumber);

}
