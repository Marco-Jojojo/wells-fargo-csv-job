package com.peiwc.billing.process.billing1;

import com.peiwc.billing.dao.CalcClearedReceivableRecordsDAO;
import com.peiwc.billing.dao.ProcessDAO;
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;
import com.peiwc.billing.helpers.ErrorHandling;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Aug 30, 2017
 */
@Service
public class CalcClearedReceivableRecords
{
    private static final Logger LOGGER = Logger.getLogger(CalcClearedReceivableRecords.class);

    @Autowired
    private ErrorHandling errorHandling;
    @Autowired
    private ProcessDAO processDAO;

    @Autowired
    private CalcClearedReceivableRecordsDAO calcClearedReceivableRecordsDAO;

    /**
     * Service to process the Cleared receivable bills
     * @param cycleNumber 
     */
    public void processPolicies(final int cycleNumber) {
        LOGGER.info("PROCESS STATUS: Starting CalcClearedReceivableRecords.processPolicies");
        List<WFMamSrcFile> records = calcClearedReceivableRecordsDAO.findAllClearedReceivables();
        LOGGER.info("PROCESS STATUS: Getting records: " + records.size());
        int seqNumber = processDAO.getMaxSequenceNumber(cycleNumber) + 1;
        int createCounter = 0;
        for (final WFMamSrcFile rec : records) {
            final WFMamSrcFilePK id = new WFMamSrcFilePK();
            id.setCycleNumber(cycleNumber);
            id.setSequenceNumber(seqNumber);
            errorHandling.checkingMandatoryFields(rec, cycleNumber, seqNumber);
            seqNumber++;
            rec.setId(id);
            calcClearedReceivableRecordsDAO.create(rec);
            createCounter++;
        }
        LOGGER.info("PROCESS STATUS: created: " + createCounter);
        LOGGER.info("PROCESS STATUS: Ending CalcClearedReceivableRecords.processPolicies");
    }

}
