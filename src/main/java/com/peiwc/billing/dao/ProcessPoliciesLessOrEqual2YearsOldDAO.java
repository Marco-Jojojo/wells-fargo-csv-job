package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

public interface ProcessPoliciesLessOrEqual2YearsOldDAO {

	List<WFMamSrcFile> findAll(String twoYearsBefore);

	List<WFMamSrcFile> findOneInWFSrcFile(int cycleNumber, String secondaryAuth);

	void create(WFMamSrcFile wfMamSrcFile);

	float getSequenceNumberFromCM(String referenceNumber);
}
