package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

public interface ProcessPoliciesLessOrEqual2YearsOldDAO {

	List<WFMamSrcFile> findAll(String twoYearsFromTodayFormatted, String todayFormatted);
	int insertBill(WFMamSrcFile obj);
}
