package com.peiwc.billing.dao;

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
import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * test for {@link WFMamSrcFileDAO}
 */
@ContextConfiguration(classes = { ConfigurationBeanMock.class })
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class WFMamSrcFileDAOTest {

	@Autowired
	private WFMamSrcFileDAO wfMamSrcFileDAO;

	/**
	 * test if records are retrieved successfully from database.
	 */
	@Test
	public void testGetMamRecordsFromCycleNumber() {
		final List<WFMamSrcFile> records = this.wfMamSrcFileDAO.getMamRecordsFromCycleNumber(2006);
		Assert.assertEquals(1, records.size());
	}

}
