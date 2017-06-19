package com.peiwc.billing.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.peiwc.billing.domain.WFMamSrcFile;

@Repository("processPoliciesLessOrEqual2YearsOldDAOImpl")
public class ProcessPoliciesLessOrEqual2YearsOldDAOImpl implements ProcessPoliciesLessOrEqual2YearsOldDAO{


	@Override
	public List<WFMamSrcFile> findAll(String twoYearsFromTodayFormatted, String todayFormatted) {
		// TODO: query to get the process policies between 2YearsBefore and today
		return null;
	}

	@Override
	public int insertBill(WFMamSrcFile obj) {
		// TODO:query to insert into WF_MAM_SRC_FILE
		return 0;
	}

}
