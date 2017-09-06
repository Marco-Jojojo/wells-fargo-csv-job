package com.peiwc.billing.process.billing1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Sep 6, 2017
 */
@RunWith(PowerMockRunner.class)
public class WFMamSrcGenRecsTest
{
    @Mock
    private CalcUnclearedBilledAmt calcUnclearedBilledAmt;
    @Mock
    private CalcClearedReceivableRecords calcClearedReceivableRecords;
    @Mock
    private ProcessPoliciesZeroAndFuture processPoliciesLessOrEqual2YearsOld;
    
    @InjectMocks
    private WFMamSrcGenRecs recsMock;
    
    private int cycleNumberMock = 1;
    
    @Test
    public void testBillingProcess() {
        PowerMockito.doNothing().when(calcUnclearedBilledAmt).updWFMamSrcFileRec(cycleNumberMock);
        PowerMockito.doNothing().when(processPoliciesLessOrEqual2YearsOld).processPolicies(cycleNumberMock);
        PowerMockito.doNothing().when(processPoliciesLessOrEqual2YearsOld).futurePolicies(cycleNumberMock);
        PowerMockito.doNothing().when(calcClearedReceivableRecords).processPolicies(cycleNumberMock);
        
        recsMock.billingProcess(cycleNumberMock);
        
        verify(calcUnclearedBilledAmt).updWFMamSrcFileRec(cycleNumberMock);
        verify(processPoliciesLessOrEqual2YearsOld).processPolicies(cycleNumberMock);
        verify(processPoliciesLessOrEqual2YearsOld).futurePolicies(cycleNumberMock);
        verify(calcClearedReceivableRecords).processPolicies(cycleNumberMock);
    }
}
