package com.peiwc.billing.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.peiwc.billing.configuration.ConfigurationBean;
import com.peiwc.billing.domain.WFMamOpHDRTRLR;

/**
 * implementation of {@link WFMamOpHDRTRLR}
 */
@Repository
public class WFMamOpHDRTRLRDAOImpl implements WFMamOpHDRTRLRDAO {

	@PersistenceContext(name = ConfigurationBean.PERSISTENCE_APP_NAME)
	private EntityManager entityManager;

	@Transactional
	@Override
	public WFMamOpHDRTRLR saveCycleForToday(final Date currentDate, final int cycleNumber) {
		final WFMamOpHDRTRLR wfMamOpHDRTRLR = new WFMamOpHDRTRLR();
		wfMamOpHDRTRLR.setCycleNumber(cycleNumber);
		wfMamOpHDRTRLR.setCreationDate(currentDate);
		entityManager.persist(wfMamOpHDRTRLR);
		return wfMamOpHDRTRLR;
	}

}
