package com.peiwc.billing.process.billing1;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.CalcUnclearedBilledAmtDAO;
import com.peiwc.billing.domain.WFMamSrcFile;

@Component("calcUnclearedBilledAmt")
public class CalcUnclearedBilledAmt {

	@Autowired
	private CalcUnclearedBilledAmtDAO calcUnclearedBilledAmtDAO;

	public void updWFMamSrcFileRec(final int cycleNumber) {
		final List<WFMamSrcFile> rows = this.calcUnclearedBilledAmtDAO.findAll();
		for (final WFMamSrcFile row : rows) {
			final List<WFMamSrcFile> recordsFound = this.calcUnclearedBilledAmtDAO.isRecordInSrcFile(cycleNumber,
					row.getSecondaryAuth(), row.getInvoiceNumber());
			if (!CollectionUtils.isEmpty(recordsFound)) {
				final WFMamSrcFile record = recordsFound.iterator().next();
				record.setAmountDue(record.getAmountDue() + row.getAmountDue());
				this.calcUnclearedBilledAmtDAO.update(cycleNumber, row.getSecondaryAuth(), row.getInvoiceNumber(),
						record.getAmountDue());
			} else {
				row.getId().setCycleNumber(cycleNumber);
				row.setInvoiceDate(this.calcUnclearedBilledAmtDAO.getInvoiceDate(row.getInvoiceNumber()));
				this.calcUnclearedBilledAmtDAO.create(row);
			}
		}
	}
}
