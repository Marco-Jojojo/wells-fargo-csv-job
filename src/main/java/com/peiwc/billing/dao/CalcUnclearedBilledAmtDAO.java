package com.peiwc.billing.dao;

import java.util.Date;
import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

public interface CalcUnclearedBilledAmtDAO {

	WFMamSrcFile isRecordInSrcFile(int cycleNumber, String secondaryAuth, String invoiceNumber);

	List<WFMamSrcFile> findAll(String todayFormatted, String twoYearsFromTodayFormatted);

	void update(int cycleNumber, String secondaryAuth, String invoiceNumber, double amountDue);
	
	Date getInvoiceDate(String invoiceNumber);
	
	void create(WFMamSrcFile obj);
	
	
}