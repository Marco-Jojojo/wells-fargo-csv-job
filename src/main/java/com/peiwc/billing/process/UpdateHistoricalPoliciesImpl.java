package com.peiwc.billing.process;

import com.peiwc.billing.dao.UpdateHistoricalPoliciesDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Aug 30, 2017
 */
@Service
public class UpdateHistoricalPoliciesImpl implements UpdateHistoricalPolicies
{
    private static final Logger LOGGER = Logger.getLogger(UpdateHistoricalPoliciesImpl.class);
    
    @Autowired
    private UpdateHistoricalPoliciesDAO updateHistoricalPoliciesDAO;
    
    @Override
    public void updateHistoricalBills() {
        LOGGER.info("PROCESS STATUS: Starting UpdateHistoricalPolicies");
        updateHistoricalPoliciesDAO.update();
        LOGGER.info("PROCESS STATUS: Ending UpdateHistoricalPolicies");
    }

}
