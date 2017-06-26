package com.peiwc.billing.process.billing1;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.CalcUnclearedUnBilledCreditAmtDAO;
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

@Component("calcUnclearedUnBilledCreditAmt")
public class CalcUnclearedUnBilledCreditAmt {

	private static final Logger LOGGER = Logger.getLogger(CalcUnclearedUnBilledCreditAmt.class);

	@Autowired
	private CalcUnclearedUnBilledCreditAmtDAO unBilledCreditAmtDAO;

	public void wfMamSrcFileUpdRecDBI1(final int cycleNumber) {

		CalcUnclearedUnBilledCreditAmt.LOGGER.info("PROCESS STATUS: Starting CalcUnclearedUnBilledCreditAmt");

		final List<WFMamSrcFile> recordsFromCM = this.unBilledCreditAmtDAO.findAll();
		CalcUnclearedUnBilledCreditAmt.LOGGER.info("PROCESS STATUS: Getting records from CM: " + recordsFromCM.size());
		int sequenceNumber = this.unBilledCreditAmtDAO.getMaxSequenceNumber(cycleNumber) + 1;
		for (final WFMamSrcFile recordFromCM : recordsFromCM) {

			final List<WFMamSrcFile> recordsFromSrcFileDBI1 = this.unBilledCreditAmtDAO.findOneByDBI1(cycleNumber,
					recordFromCM.getSecondaryAuth());

			if (!CollectionUtils.isEmpty(recordsFromSrcFileDBI1)) {

				final float amtDueWFSrcFile = recordsFromSrcFileDBI1.iterator().next().getAmountDue();
				float amtApplied;
				float amt_due;

				if (amtDueWFSrcFile <= Math.abs(recordFromCM.getAmountDue())) {
					amtApplied = amtDueWFSrcFile * -1;
					amt_due = amtDueWFSrcFile + amtApplied;
					this.unBilledCreditAmtDAO.updateDBI1(cycleNumber,
							recordsFromSrcFileDBI1.iterator().next().getSecondaryAuth(), amt_due);
				} else {
					amtApplied = recordFromCM.getAmountDue();
					amt_due = amtDueWFSrcFile + amtApplied;
					this.unBilledCreditAmtDAO.updateDBI1(cycleNumber,
							recordsFromSrcFileDBI1.iterator().next().getSecondaryAuth(), amt_due);
				}
			} else {

				final List<WFMamSrcFile> recordsFromSrcFileDBI0 = this.unBilledCreditAmtDAO.findOneByDBI0(cycleNumber,
						recordFromCM.getSecondaryAuth(), recordFromCM.getInvoiceNumber());

				if (!CollectionUtils.isEmpty(recordsFromSrcFileDBI0)) {

					final float amtDueWFSrcFile = recordsFromSrcFileDBI0.iterator().next().getAmountDue();
					final float amt_due = amtDueWFSrcFile + recordFromCM.getAmountDue();

					this.unBilledCreditAmtDAO.updateDBI0(cycleNumber, recordFromCM.getSecondaryAuth(),
							recordFromCM.getInvoiceNumber(), amt_due);
				} else {
					try {
						final Date date = this.unBilledCreditAmtDAO.getInvoiceDate(recordFromCM.getInvoiceNumber());
						recordFromCM.setInvoiceDate(date);
					} catch (final EmptyResultDataAccessException e) {
						final Date date = null;
						recordFromCM.setInvoiceDate(date);
					}
					//final Date date = this.unBilledCreditAmtDAO.getInvoiceDate(recordFromCM.getInvoiceNumber());
					final WFMamSrcFilePK id = new WFMamSrcFilePK();
					id.setCycleNumber(cycleNumber);
					id.setSequenceNumber(sequenceNumber);
					sequenceNumber += 1;
					recordFromCM.setId(id);
					this.unBilledCreditAmtDAO.create(recordFromCM);
				}
			}
		}
		CalcUnclearedUnBilledCreditAmt.LOGGER
				.info("PROCESS STATUS: Ending CalcUnclearedUnBilledCreditAmtDAO.wfMamSrcFileUpdRecDBI1");
	}

}
