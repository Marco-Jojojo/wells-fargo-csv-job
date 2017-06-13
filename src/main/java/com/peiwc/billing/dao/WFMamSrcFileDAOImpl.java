package com.peiwc.billing.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.peiwc.billing.configuration.ConfigurationBean;
import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * implementation of {@link WFMamSrcFileDAO}
 */
@Repository
public class WFMamSrcFileDAOImpl implements WFMamSrcFileDAO {

	@PersistenceContext(name = ConfigurationBean.PERSISTENCE_APP_NAME)
	private EntityManager entityManager;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WFMamSrcFile> getMamRecordsFromCycleNumber(final int cycleNumber) {
		final Query query = entityManager.createNamedQuery("WFMamSrcFile.findByCycleNumber");
		query.setParameter("cycleNumber", cycleNumber);
		final List<WFMamSrcFile> list = query.getResultList();
		return list;
	}

}
