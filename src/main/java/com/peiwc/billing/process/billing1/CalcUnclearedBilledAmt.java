package com.peiwc.billing.process.billing1;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.CalcUnclearedBilledAmtDAO;
import com.peiwc.billing.dao.WFMamErrLogRepository;
import com.peiwc.billing.domain.WFMamErrLog;
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

/**
 * @author jolivarria
 *
 */
@Component("calcUnclearedBilledAmt")
public class CalcUnclearedBilledAmt {

	private static final Logger LOGGER = Logger.getLogger(CalcUnclearedBilledAmt.class);

	@Autowired
	private CalcUnclearedBilledAmtDAO calcUnclearedBilledAmtDAO;

	@Autowired
	private WFMamErrLogRepository wfMamErrLogRepository;

	private static final String PENDING_REC = "PENDING_REC";

	private static final String POLICY_NUMBER_DESCRIPTION_ERROR = "Policy number cannot be zero or null";

	private static final String DUE_DATE_DESCRIPTION_ERROR = "Due date cannot bu null";

	/**
	 * Take records from COLLECTION MASTER and insert them into WF_MAM_SRC_FILE
	 *
	 * @param cycleNumber
	 */
	public void updWFMamSrcFileRec(final int cycleNumber) {
		CalcUnclearedBilledAmt.LOGGER.info("PROCESS STATUS: Starting CalcUnclearedBilledAmt.updWFMamSrcFileRec");
		final List<WFMamSrcFile> rows = this.calcUnclearedBilledAmtDAO.findAll();
		CalcUnclearedBilledAmt.LOGGER.info("PROCESS STATUS: Getting all records: " + rows.size());
		int sequenceNumber = 1;
		int createCounter = 0;
		for (final WFMamSrcFile row : rows) {
			final WFMamSrcFilePK id = new WFMamSrcFilePK();
			id.setCycleNumber(cycleNumber);
			id.setSequenceNumber(sequenceNumber);
			if ("0".equals(row.getReferenceNumber())) {
				this.sendError(cycleNumber, sequenceNumber, CalcUnclearedBilledAmt.POLICY_NUMBER_DESCRIPTION_ERROR);
			} else if (row.getDueDate() == null) {
				this.sendError(cycleNumber, sequenceNumber, CalcUnclearedBilledAmt.DUE_DATE_DESCRIPTION_ERROR);
			}
			sequenceNumber += 1;
			row.setId(id);
			this.calcUnclearedBilledAmtDAO.create(row);
			createCounter += 1;
		}
		CalcUnclearedBilledAmt.LOGGER.info("PROCESS STATUS: created: " + createCounter);
		CalcUnclearedBilledAmt.LOGGER.info("PROCESS STATUS: Ending CalcUnclearedBilledAmt.updWFMamSrcFileRec");
	}

	private void sendError(final int cycleNumber, final int sequenceNumber, final String errorMsg) {
		final WFMamErrLog error = new WFMamErrLog();
		error.setCycleNumber(cycleNumber);
		error.setSequenceNumber(sequenceNumber);
		error.setDescription(errorMsg);
		error.setStatus(CalcUnclearedBilledAmt.PENDING_REC);
		CalcUnclearedBilledAmt.LOGGER.info("PROCESS STATUS: MSG ERROR: " + error.getDescription());
		wfMamErrLogRepository.saveAndFlush(error);

	}
}
