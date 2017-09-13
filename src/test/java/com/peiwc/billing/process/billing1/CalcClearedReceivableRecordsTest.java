package com.peiwc.billing.process.billing1;

import com.peiwc.billing.dao.CalcClearedReceivableRecordsDAO;
import com.peiwc.billing.dao.ProcessDAO;
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;
import com.peiwc.billing.helpers.ErrorHandling;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Sep 6, 2017
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CalcClearedReceivableRecords.class)
public class CalcClearedReceivableRecordsTest
{
    @Mock
    private ErrorHandling errorHandling;
    @Mock
    private ProcessDAO processDAO;
    @Mock
    private CalcClearedReceivableRecordsDAO calcClearedReceivableRecordsDAO;
    private int cycleNumberMock = 1;
    
    @InjectMocks
    private CalcClearedReceivableRecords receivablesMock;
    
    private WFMamSrcFilePK idMock;
    
    private List<WFMamSrcFile> wfs = new ArrayList<WFMamSrcFile>() {
        {
            add(new WFMamSrcFile());
            add(new WFMamSrcFile());
        }
    };
    
    @Before
    public void init() {
        idMock = PowerMockito.mock(WFMamSrcFilePK.class);
    }
    
    @Test
    public void testProcessPolicies() throws Exception {
        suppress(method(Logger.class, "info", Object.class));
        PowerMockito.when(calcClearedReceivableRecordsDAO.findAllClearedReceivables()).thenReturn(wfs);
        PowerMockito.when(processDAO.getMaxSequenceNumber(cycleNumberMock)).thenReturn(1);
        whenNew(WFMamSrcFilePK.class).withNoArguments().thenReturn(idMock).thenReturn(idMock);
        PowerMockito.doNothing().when(errorHandling).checkingMandatoryFields(any(WFMamSrcFile.class), eq(cycleNumberMock), anyInt());
        PowerMockito.doNothing().when(calcClearedReceivableRecordsDAO).create(any(WFMamSrcFile.class));
        
        receivablesMock.processPolicies(cycleNumberMock);
        
        verify(calcClearedReceivableRecordsDAO).findAllClearedReceivables();
        verify(processDAO).getMaxSequenceNumber(cycleNumberMock);
        verifyNew(WFMamSrcFilePK.class, times(2)).withNoArguments();
        verify(errorHandling, times(2)).checkingMandatoryFields(any(WFMamSrcFile.class), eq(cycleNumberMock), anyInt());
        verify(calcClearedReceivableRecordsDAO, times(2)).create(any(WFMamSrcFile.class));
    }
    
    @Test
    public void testProcessZeroPolicies() throws Exception {
        suppress(method(Logger.class, "info", Object.class));
        PowerMockito.when(calcClearedReceivableRecordsDAO.findAllClearedReceivables()).thenReturn(new ArrayList<WFMamSrcFile>());
        PowerMockito.when(processDAO.getMaxSequenceNumber(cycleNumberMock)).thenReturn(1);
        whenNew(WFMamSrcFilePK.class).withNoArguments().thenReturn(idMock).thenReturn(idMock);
        
        receivablesMock.processPolicies(cycleNumberMock);
        
        verify(calcClearedReceivableRecordsDAO).findAllClearedReceivables();
        verify(processDAO).getMaxSequenceNumber(cycleNumberMock);
        verifyNew(WFMamSrcFilePK.class, never()).withNoArguments();
        verify(errorHandling, never()).checkingMandatoryFields(any(WFMamSrcFile.class), eq(cycleNumberMock), anyInt());
        verify(calcClearedReceivableRecordsDAO, never()).create(any(WFMamSrcFile.class));
    }
}
