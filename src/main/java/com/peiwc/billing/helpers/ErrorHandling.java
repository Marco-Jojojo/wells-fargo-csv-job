package com.peiwc.billing.helpers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.WFMamErrLogRepository;
import com.peiwc.billing.domain.WFMamErrLog;
import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Aug 30, 2017
 */
@Component
public class ErrorHandling {
	private static final Logger LOGGER = Logger.getLogger(ErrorHandling.class);
	/**
	 * Pending records static definition.
	 */
	public static final String PENDING_REC = "PENDING_REC";
	/**
	 * Policy Number Error Message when null record
	 */
	public static final String POLICY_NUMBER_DESCRIPTION_ERROR = "Policy number cannot be zero or null";
	/**
	 * Due Date Error message when null.
	 */
	public static final String DUE_DATE_DESCRIPTION_ERROR = "Due date cannot be null";
	@Autowired
	private WFMamErrLogRepository wfMamErrLogRepository;

	/**
	 * Stores error when record could not be saved normally.
	 *
	 * @param cycleNumber
	 *            number of run when executing batch procedure.
	 * @param sequenceNumber
	 *            sequence record that fails when executing batch procedure.
	 * @param errorMsg
	 *            reason message why could not be saved normally.
	 */
	public void sendError(final int cycleNumber, final int sequenceNumber, final String errorMsg) {
		final WFMamErrLog error = new WFMamErrLog();
		error.setCycleNumber(cycleNumber);
		error.setSequenceNumber(sequenceNumber);
		error.setDescription(errorMsg);
		error.setStatus(ErrorHandling.PENDING_REC);
		ErrorHandling.LOGGER.info("PROCESS STATUS: MSG ERROR: " + error.getDescription());
		wfMamErrLogRepository.saveAndFlush(error);
	}

	/**
	 * checks if mandatory fields are set as expected. if not an error is saved
	 * to the database.
	 * 
	 * @param rec
	 * @param cycleNumber
	 * @param seqNumber
	 */
	public void checkingMandatoryFields(final WFMamSrcFile rec, final int cycleNumber, final int seqNumber) {
		if ("0".equals(rec.getReferenceNumber())) {
			sendError(cycleNumber, seqNumber, ErrorHandling.POLICY_NUMBER_DESCRIPTION_ERROR);
		}
		if (rec.getDueDate() == null) {
			sendError(cycleNumber, seqNumber, ErrorHandling.DUE_DATE_DESCRIPTION_ERROR);
		}
	}
}
