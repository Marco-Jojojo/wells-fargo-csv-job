package com.peiwc.billing.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.peiwc.billing.configuration.ConfigurationBean;
import com.peiwc.billing.domain.WFMamSrcFile;

@Repository
public class WFMamSrcFileDAOImpl implements WFMamSrcFileDAO {

	@PersistenceContext(name = ConfigurationBean.PERSISTENCE_APP_NAME)
	private EntityManager entityManager;

	@Override
	public List<WFMamSrcFile> getMamRecordsFromCycleNumber(final int cycleNumber) {
		entityManager.createQuery("Select w from WFMamSrcFile w where w.cycleNumber = :cycleNumber");
		return null;
	}

}
