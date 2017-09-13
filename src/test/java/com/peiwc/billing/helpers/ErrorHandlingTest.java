package com.peiwc.billing.helpers;

import com.peiwc.billing.dao.WFMamErrLogRepository;
import com.peiwc.billing.domain.WFMamErrLog;
import com.peiwc.billing.domain.WFMamSrcFile;
import java.util.Date;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
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
@PrepareForTest(ErrorHandling.class)
public class ErrorHandlingTest
{
    @Mock
    private WFMamErrLogRepository daoWFMock;
    @Mock
    private WFMamErrLog logErrorMock;
    private Date today = new Date();
    
    @InjectMocks
    private ErrorHandling errorMock;
    
    @Before
    public void init() throws Exception {
        whenNew(WFMamErrLog.class).withNoArguments().thenReturn(logErrorMock);
        suppress(method(Logger.class, "info", Object.class));
        PowerMockito.when(daoWFMock.saveAndFlush(logErrorMock)).thenReturn(logErrorMock);
    }
    
    @Test
    public void testSendError() throws Exception {
        errorMock.sendError(1, 1, "");
        
        verifySendingErrorCorrectly();
    }
    
    @Test
    public void testCheckingMandatoryFieldsWithoutFailure() throws Exception {
        WFMamSrcFile wfMock = PowerMockito.mock(WFMamSrcFile.class);
        // Valida parameters:
        PowerMockito.when(wfMock.getReferenceNumber()).thenReturn("1");
        PowerMockito.when(wfMock.getDueDate()).thenReturn(today);
        
        checkingMandatoryFieldsMethod(wfMock);
        
        verifyNew(WFMamErrLog.class, never()).withNoArguments();
        verify(daoWFMock, never()).saveAndFlush(logErrorMock);
    }
    
    @Test
    public void testCheckingMandatoryFieldsWithoutReferenceNumber() throws Exception {
        WFMamSrcFile wfMock = PowerMockito.mock(WFMamSrcFile.class);
        PowerMockito.when(wfMock.getReferenceNumber()).thenReturn("0"); // Without Reference Number
        PowerMockito.when(wfMock.getDueDate()).thenReturn(today);
        
        checkingMandatoryFieldsMethod(wfMock);
        
        verifySendingErrorCorrectly();
    }

    @Test
    public void testCheckingMandatoryFieldsWithoutDueDate() throws Exception {
        WFMamSrcFile wfMock = PowerMockito.mock(WFMamSrcFile.class);
        PowerMockito.when(wfMock.getReferenceNumber()).thenReturn("1"); // Without Due Date
        PowerMockito.when(wfMock.getDueDate()).thenReturn(null);
        
        checkingMandatoryFieldsMethod(wfMock);
        
        verifySendingErrorCorrectly();
    }
    
    private void checkingMandatoryFieldsMethod(WFMamSrcFile wfMock) {
        errorMock.checkingMandatoryFields(wfMock, 1, 1);
    }
    
    private void verifySendingErrorCorrectly() throws Exception {
        verifyNew(WFMamErrLog.class).withNoArguments();
        verify(daoWFMock).saveAndFlush(logErrorMock);
    }
}
