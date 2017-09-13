package com.peiwc.billing.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Sep 6, 2017
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UpdateHistoricalPoliciesDAOImpl.class)
public class UpdateHistoricalPoliciesDAOImplTest
{
    private NamedParameterJdbcTemplate npJdbcMock = PowerMockito.mock(NamedParameterJdbcTemplate.class);
    
    @InjectMocks
    private UpdateHistoricalPoliciesDAOImpl daoImpl;
    
    @Test
    public void testUpdate() throws Exception {
        MapSqlParameterSource paramMock = PowerMockito.mock(MapSqlParameterSource.class);
        whenNew(MapSqlParameterSource.class).withNoArguments().thenReturn(paramMock);
        PowerMockito.when(npJdbcMock.execute(anyString(), eq(paramMock), any(PreparedStatementCallback.class))).thenReturn(true);
        
        daoImpl.update();
        
        verifyNew(MapSqlParameterSource.class).withNoArguments();
        verify(npJdbcMock).execute(anyString(), eq(paramMock), any(PreparedStatementCallback.class));
    }
}
