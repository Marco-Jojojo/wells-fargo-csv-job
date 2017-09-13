package com.peiwc.billing.process;

import com.peiwc.billing.dao.UpdateHistoricalPoliciesDAO;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.verify;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Sep 6, 2017
 */
@RunWith(PowerMockRunner.class)
public class UpdateHistoricalPoliciesImplTest
{
    private UpdateHistoricalPoliciesDAO daoMock = PowerMockito.mock(UpdateHistoricalPoliciesDAO.class);
    
    @InjectMocks
    private UpdateHistoricalPoliciesImpl daoImpl;
    
    @Test
    public void testUpdateHistoricalBills() {
        suppress(method(Logger.class, "info", Object.class));
        PowerMockito.doNothing().when(daoMock).update();
        
        daoImpl.updateHistoricalBills();
        
        verify(daoMock).update();
    }
}
