package com.peiwc.billing.dao;

import com.peiwc.billing.domain.WFMamSrcFile;

import java.util.Date;
import java.util.List;

public interface CalcUnclearedBilledAmtDAO {

    List<WFMamSrcFile> findAll();

    List<WFMamSrcFile> isRecordInSrcFile(int cycleNumber, String secondaryAuth, String invoiceNumber);

    void update(int cycleNumber, String secondaryAuth, String invoiceNumber, double amountDue);

    void create(WFMamSrcFile wfMamSrcFile);

    Date getInvoiceDate(String invoiceNumber);


}