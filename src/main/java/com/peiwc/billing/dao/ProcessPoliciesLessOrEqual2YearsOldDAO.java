package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

public interface ProcessPoliciesLessOrEqual2YearsOldDAO {

	List<WFMamSrcFile> findAllTwoYearsOldPolicies(String twoYearsBefore, String today);

	List<WFMamSrcFile> findAllFuturePolicies(String today);

	List<WFMamSrcFile> findOneInWFSrcFile(int cycleNumber, int submissionNumber);

	void create(WFMamSrcFile wfMamSrcFile);

	int getMaxSequenceNumber(int cycleNumber);
}
