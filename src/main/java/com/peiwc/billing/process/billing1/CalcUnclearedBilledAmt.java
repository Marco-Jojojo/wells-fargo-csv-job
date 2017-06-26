package com.peiwc.billing.process.billing1;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.CalcUnclearedBilledAmtDAO;
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

@Component("calcUnclearedBilledAmt")
public class CalcUnclearedBilledAmt {

	private static final Logger LOGGER = Logger.getLogger(CalcUnclearedBilledAmt.class);

	@Autowired
	private CalcUnclearedBilledAmtDAO calcUnclearedBilledAmtDAO;

	public void updWFMamSrcFileRec(final int cycleNumber) {
		CalcUnclearedBilledAmt.LOGGER.info("PROCESS STATUS: Starting CalcUnclearedBilledAmt.updWFMamSrcFileRec");
		final List<WFMamSrcFile> rows = this.calcUnclearedBilledAmtDAO.findAll();
		CalcUnclearedBilledAmt.LOGGER.info("PROCESS STATUS: Getting all records: " + rows.size());
		int sequenceNumber = 1;
		for (final WFMamSrcFile row : rows) {
			final List<WFMamSrcFile> recordsFound = this.calcUnclearedBilledAmtDAO.isRecordInSrcFile(cycleNumber,
					row.getSecondaryAuth(), row.getInvoiceNumber());
			if (!CollectionUtils.isEmpty(recordsFound)) {
				final WFMamSrcFile record = recordsFound.iterator().next();
				record.setAmountDue(record.getAmountDue() + row.getAmountDue());
				this.calcUnclearedBilledAmtDAO.update(row.getId().getCycleNumber(), row.getSecondaryAuth(),
						row.getInvoiceNumber(), record.getAmountDue());
			} else {
				final WFMamSrcFilePK id = new WFMamSrcFilePK();
				id.setCycleNumber(cycleNumber);
				id.setSequenceNumber(sequenceNumber);
				sequenceNumber += 1;
				row.setId(id);
				this.calcUnclearedBilledAmtDAO.create(row);
			}
		}
		CalcUnclearedBilledAmt.LOGGER.info("PROCESS STATUS: Ending CalcUnclearedBilledAmt.updWFMamSrcFileRec");
	}
}
