package com.peiwc.billing.dao.mappers;

import com.peiwc.billing.domain.WFMamSrcFile;
import java.sql.ResultSet;
import java.sql.Date;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Sep 6, 2017
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SrcFileMapperForClearedReceivables.class)
public class SrcFileMapperForClearedReceivablesTest
{
    private ResultSet rsMock = PowerMockito.mock(ResultSet.class);
    private Date today = new Date(0);
    
    @InjectMocks
    private SrcFileMapperForClearedReceivables mapperMock;
    
    @Test
    public void testMapRow() throws Exception {
        WFMamSrcFile wfMock = PowerMockito.mock(WFMamSrcFile.class);
        whenNew(WFMamSrcFile.class).withNoArguments().thenReturn(wfMock);
        PowerMockito.when(rsMock.getInt(Matchers.anyString())).thenReturn(Integer.MIN_VALUE);
        PowerMockito.when(rsMock.getString(Matchers.anyString())).thenReturn("");
        PowerMockito.when(rsMock.getDate(Matchers.anyString())).thenReturn(today);
        
        WFMamSrcFile wfTest = mapperMock.mapRow(rsMock, 1);
        
        Assert.assertNotNull(wfTest);
        
        verifyNew(WFMamSrcFile.class).withNoArguments();
        verify(rsMock, times(2)).getInt(Matchers.anyString());
        verify(rsMock).getString(Matchers.anyString());
        verify(rsMock, times(2)).getDate(Matchers.anyString());
    }
}
