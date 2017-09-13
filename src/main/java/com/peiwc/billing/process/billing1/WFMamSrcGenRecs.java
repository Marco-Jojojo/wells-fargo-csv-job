package com.peiwc.billing.process.billing1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jolivarria
 *
 */
@Service("wfMamSrcGenRecs")
public class WFMamSrcGenRecs
{

    @Autowired
    private CalcUnclearedBilledAmt calcUnclearedBilledAmt;

    @Autowired
    private CalcClearedReceivableRecords calcClearedReceivableRecords;

    @Autowired
    private ProcessPoliciesZeroAndFuture processPoliciesLessOrEqual2YearsOld;

    /**
     * Starts the billing process calling subtasks. First, the Outstanding bills are going to be processed, then the Zero bill policies, after that the future policies, and finally the Cleared receivables will be processed.
     * @param cycleNumber
     */
    public void billingProcess(final int cycleNumber) {
        calcUnclearedBilledAmt.updWFMamSrcFileRec(cycleNumber); // Outstanding bills
        processPoliciesLessOrEqual2YearsOld.processPolicies(cycleNumber); // Zero bill policies
        processPoliciesLessOrEqual2YearsOld.futurePolicies(cycleNumber); // Future policies
        calcClearedReceivableRecords.processPolicies(cycleNumber); // Cleared receivables bills
    }

}
