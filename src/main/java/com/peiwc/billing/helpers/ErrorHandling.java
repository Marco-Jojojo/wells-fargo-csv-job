package com.peiwc.billing.helpers;

import com.peiwc.billing.dao.WFMamErrLogRepository;
import com.peiwc.billing.domain.WFMamErrLog;
import com.peiwc.billing.domain.WFMamSrcFile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Aug 30, 2017
 */
@Component
public class ErrorHandling
{
    private static final Logger LOGGER = Logger.getLogger(ErrorHandling.class);
    public static final String PENDING_REC = "PENDING_REC";
    public static final String POLICY_NUMBER_DESCRIPTION_ERROR = "Policy number cannot be zero or null";
    public static final String DUE_DATE_DESCRIPTION_ERROR = "Due date cannot be null";
    
    @Autowired
    private WFMamErrLogRepository wfMamErrLogRepository;

    public void sendError(final int cycleNumber, final int sequenceNumber, final String errorMsg) {
        final WFMamErrLog error = new WFMamErrLog();
        error.setCycleNumber(cycleNumber);
        error.setSequenceNumber(sequenceNumber);
        error.setDescription(errorMsg);
        error.setStatus(PENDING_REC);
        LOGGER.info("PROCESS STATUS: MSG ERROR: " + error.getDescription());
        wfMamErrLogRepository.saveAndFlush(error);
    }
    
    public void checkingMandatoryFields(final WFMamSrcFile rec, final int cycleNumber, int seqNumber) {
        if ("0".equals(rec.getReferenceNumber())) {
            sendError(cycleNumber, seqNumber, ErrorHandling.POLICY_NUMBER_DESCRIPTION_ERROR);
        }
        if (rec.getDueDate() == null) {
            sendError(cycleNumber, seqNumber, ErrorHandling.DUE_DATE_DESCRIPTION_ERROR);
        }
    }
}
