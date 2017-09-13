package com.peiwc.billing.dao;

import com.peiwc.billing.dao.mappers.SrcFileMapperForClearedReceivables;
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Sep 6, 2017
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CalcClearedReceivableRecordsDAOImpl.class)
public class CalcClearedReceivableRecordsDAOImplTest
{
    private NamedParameterJdbcTemplate npJdbcMock = PowerMockito.mock(NamedParameterJdbcTemplate.class);
    
    @InjectMocks
    private CalcClearedReceivableRecordsDAOImpl daoImpl;
    
    private List<WFMamSrcFile> wfs = new ArrayList<WFMamSrcFile>() {
        {
            add(new WFMamSrcFile());
            add(new WFMamSrcFile());
        }
    };
    
    @Test
    public void testFindAllClearedReceivables() throws Exception {
        SrcFileMapperForClearedReceivables mapperMock = PowerMockito.mock(SrcFileMapperForClearedReceivables.class);
        whenNew(SrcFileMapperForClearedReceivables.class).withNoArguments().thenReturn(mapperMock);
//        PowerMockito.doReturn(wfs).when(npJdbcMock).query(anyString(), eq(mapperMock));
        PowerMockito.when(npJdbcMock.query(anyString(), eq(mapperMock))).thenReturn(wfs);
        
        List<WFMamSrcFile> wfsTest = daoImpl.findAllClearedReceivables();
        
        Assert.assertNotNull(wfsTest);
        Assert.assertEquals(wfs.size(), wfsTest.size());
        
        verifyNew(SrcFileMapperForClearedReceivables.class).withNoArguments();
        verify(npJdbcMock).query(anyString(), eq(mapperMock));
    }
    
    @Test
    public void testCreate() throws Exception {
        MapSqlParameterSource paramMock = PowerMockito.mock(MapSqlParameterSource.class);
        whenNew(MapSqlParameterSource.class).withNoArguments().thenReturn(paramMock);
        PowerMockito.when(paramMock.addValue(anyString(), anyObject())).thenReturn(paramMock);
        PowerMockito.when(npJdbcMock.update(anyString(), eq(paramMock))).thenReturn(1);
        
        WFMamSrcFile wfTest = new WFMamSrcFile();
        wfTest.setId(new  WFMamSrcFilePK());
        daoImpl.create(wfTest);
        
        verifyNew(MapSqlParameterSource.class).withNoArguments();
        verify(paramMock, times(7)).addValue(anyString(), anyObject()); // Seven parameters are added
        verify(npJdbcMock).update(anyString(), eq(paramMock));
    }
}
