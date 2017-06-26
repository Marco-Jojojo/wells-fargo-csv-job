package com.peiwc.billing.process.billing1;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peiwc.billing.configuration.ConfigurationBeanMock;
import com.peiwc.billing.dao.CalcUnclearedBilledAmtDAO;
import com.peiwc.billing.domain.WFMamSrcFile;

@ContextConfiguration(classes = { ConfigurationBeanMock.class })
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class CalcUnclearedBilledAmtTest {

	@Autowired
	private CalcUnclearedBilledAmtDAO unclearedBilledAmtDAO;

	@Test
	public void testIsRecordInSrcFile() {
		final List<WFMamSrcFile> rows = this.unclearedBilledAmtDAO.isRecordInSrcFile(2007, "1", "0");
		Assert.assertNotEquals(0, rows.size());
		final WFMamSrcFile row = rows.iterator().next();
		Assert.assertEquals(2007, row.getId().getCycleNumber());
	}
}
