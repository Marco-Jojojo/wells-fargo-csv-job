package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

public interface ProcessPoliciesLessOrEqual2YearsOldDAO {

	List<WFMamSrcFile> findAll(String twoYearsBefore, String today);
	
	List<WFMamSrcFile> findOneInWFSrcFile(int cycleNumber, String secondaryAuth);
	
	void insert(WFMamSrcFile wfMamSrcFile);
}
