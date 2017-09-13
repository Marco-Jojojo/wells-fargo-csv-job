package com.peiwc.billing.dao;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Aug 30, 2017
 */
public interface UpdateHistoricalPoliciesDAO
{
    /**
     * Creates and executes a JDBC stored procedure to update all cleared receivable records from WF_MAM_SRC_FILE
     */
    void update();
}
