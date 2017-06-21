package com.peiwc.billing.dao;

import java.util.Date;
import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

public interface CalcUnclearedBilledAmtDAO {

	List<WFMamSrcFile> findAll();
	
	List<WFMamSrcFile> isRecordInSrcFile(int cycleNumber, String secondaryAuth, String invoiceNumber);

	void update(int cycleNumber, String secondaryAuth, String invoiceNumber, double amountDue);
	
	void create(WFMamSrcFile wfMamSrcFile);
	
	Date getInvoiceDate(String invoiceNumber);
	
	
}