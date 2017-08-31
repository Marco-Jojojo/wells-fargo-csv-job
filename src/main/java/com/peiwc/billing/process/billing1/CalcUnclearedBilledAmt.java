package com.peiwc.billing.process.billing1;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peiwc.billing.dao.CalcUnclearedBilledAmtDAO;
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;
import com.peiwc.billing.helpers.ErrorHandling;

/**
 * @author jolivarria
 *
 */
@Service("calcUnclearedBilledAmt")
public class CalcUnclearedBilledAmt {

	private static final Logger LOGGER = Logger.getLogger(CalcUnclearedBilledAmt.class);

	@Autowired
	private CalcUnclearedBilledAmtDAO calcUnclearedBilledAmtDAO;
        
                  @Autowired
                  private ErrorHandling errorHandling;

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
			errorHandling.checkingMandatoryFields(row, cycleNumber, sequenceNumber);
			sequenceNumber += 1;
			row.setId(id);
			this.calcUnclearedBilledAmtDAO.create(row);
			createCounter += 1;
		}
		CalcUnclearedBilledAmt.LOGGER.info("PROCESS STATUS: created: " + createCounter);
		CalcUnclearedBilledAmt.LOGGER.info("PROCESS STATUS: Ending CalcUnclearedBilledAmt.updWFMamSrcFileRec");
	}

}
