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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WFMamOpHDRTRLR saveCycleForToday(final Date currentDate, final int cycleNumber) {
		final WFMamOpHDRTRLR wfMamOpHDRTRLR = new WFMamOpHDRTRLR();
		wfMamOpHDRTRLR.setCycleNumber(cycleNumber);
		wfMamOpHDRTRLR.setCreationDate(currentDate);
		entityManager.persist(wfMamOpHDRTRLR);
		entityManager.flush();
		return wfMamOpHDRTRLR;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public void saveTotalRecordsProcessed(final int nextCycle, final int totalRecordCount) {
		final WFMamOpHDRTRLR wfMamOpHDRTRLR = entityManager.find(WFMamOpHDRTRLR.class, nextCycle);
		wfMamOpHDRTRLR.setTotalRecordCount(totalRecordCount);
		entityManager.persist(wfMamOpHDRTRLR);
		entityManager.flush();
	}

}
