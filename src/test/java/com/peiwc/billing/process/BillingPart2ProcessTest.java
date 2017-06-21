package com.peiwc.billing.process;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peiwc.billing.configuration.ConfigurationBeanMock;
import com.peiwc.billing.dao.BillingProcessDAOImpl;
import com.peiwc.billing.dao.WFUserInfoMapper;
import com.peiwc.billing.domain.WFUserInfo;

@ContextConfiguration(classes = { ConfigurationBeanMock.class })
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class BillingPart2ProcessTest {

	@Before
	public void setup() {
		BasicConfigurator.configure();
	}

	@Autowired
	BillingPart2Process billingPart2Process;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	public void testBillingProcess2() {

		final int submissionNumber = 2007;

		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("submissionNumber", submissionNumber);

		final WFUserInfo before = namedParameterJdbcTemplate.queryForObject(BillingProcessDAOImpl.GET_USER_INFORMATION,
				parameters, new WFUserInfoMapper());

		billingPart2Process.updateUserInfo(submissionNumber);

		final WFUserInfo after = namedParameterJdbcTemplate.queryForObject(BillingProcessDAOImpl.GET_USER_INFORMATION,
				parameters, new WFUserInfoMapper());

		Assert.assertNotEquals(before, after);

	}

}
