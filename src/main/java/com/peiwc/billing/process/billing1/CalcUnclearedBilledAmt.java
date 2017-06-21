package com.peiwc.billing.process.billingP1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.CalcUnclearedBilledAmtDAO;
import com.peiwc.billing.domain.WFMamSrcFile;

@Component("calcUnclearedBilledAmt")
public class CalcUnclearedBilledAmt {
	
	@Autowired
	private CalcUnclearedBilledAmtDAO calcUnclearedBilledAmtDAO;
	
	
	public void updWFMamSrcFileRec(int cycleNumber){
		List<WFMamSrcFile> rows = this.calcUnclearedBilledAmtDAO.findAll();
		for (WFMamSrcFile row : rows) {
			WFMamSrcFile isRecordFound = this.calcUnclearedBilledAmtDAO.isRecordInSrcFile(
					row.getId().getCycleNumber(),
					row.getSecondaryAuth(),
					row.getInvoiceNumber());
			if (isRecordFound!=null) {
				isRecordFound.setAmountDue(isRecordFound.getAmountDue() + row.getAmountDue());
				this.calcUnclearedBilledAmtDAO.update(
						row.getId().getCycleNumber(),
						row.getSecondaryAuth(),
						row.getInvoiceNumber(),
						isRecordFound.getAmountDue());
			} else {
				row.setInvoiceDate(this.calcUnclearedBilledAmtDAO.getInvoiceDate(row.getInvoiceNumber()));
				this.calcUnclearedBilledAmtDAO.create(row);
			}
		}
	}
}
