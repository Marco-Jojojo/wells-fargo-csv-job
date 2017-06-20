package com.peiwc.billing.process.billingP1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.CalcUnclearedBilledAmtDAO;
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

@Component("calcUnclearedBilledAmt")
public class CalcUnclearedBilledAmt {
	
	@Autowired
	private CalcUnclearedBilledAmtDAO calcUnclearedBilledAmtDAO;
	
	
	public void updWFMamSrcFileRec(int cycleNumber){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, -2);
		Date twoYearsFromToday = cal.getTime();
		SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayFormatted = sqlFormat.format(today);
		String twoYearsFromTodayFormatted = sqlFormat.format(twoYearsFromToday);
		List<WFMamSrcFile> rows = this.calcUnclearedBilledAmtDAO.findAll(todayFormatted, twoYearsFromTodayFormatted);
		for (WFMamSrcFile row : rows) {		
			WFMamSrcFile isRecordFound = this.calcUnclearedBilledAmtDAO.
					isRecordInSrcFile(row.getId().getCycleNumber(),row.getSecondaryAuth(), row.getInvoiceNumber());
			if (isRecordFound!=null) {
				isRecordFound.setAmountDue(isRecordFound.getAmountDue() + row.getAmountDue());
				this.calcUnclearedBilledAmtDAO.update(row.getId().getCycleNumber(), row.getSecondaryAuth(), row.getInvoiceNumber(), isRecordFound.getAmountDue());
			} else {
				row.setInvoiceDate(this.calcUnclearedBilledAmtDAO.getInvoiceDate(row.getInvoiceNumber()));
				this.calcUnclearedBilledAmtDAO.create(row);
			}
		}
	}
}
