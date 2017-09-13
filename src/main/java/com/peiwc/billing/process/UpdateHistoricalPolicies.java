package com.peiwc.billing.process;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Aug 30, 2017
 * Updates all records in the WF_MAM_SRC_FILE table for cleared receivable bills.
 */
public interface UpdateHistoricalPolicies
{
    /**
     * Updates all records in the WF_MAM_SRC_FILE table for cleared receivable bills.
     */
    void updateHistoricalBills();
}
