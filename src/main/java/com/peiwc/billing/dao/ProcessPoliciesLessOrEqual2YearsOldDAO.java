package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * @author jolivarria
 *
 */
public interface ProcessPoliciesLessOrEqual2YearsOldDAO {

	/**
	 * @param twoYearsBefore
	 * @param today
	 * @return List<WFMamSrcFile>
	 */
	List<WFMamSrcFile> findAllPoliciesWithoutOutstandingBills();

	/**
	 * @param today
	 * @return List<WFMamSrcFile>
	 */
	List<WFMamSrcFile> findAllFuturePolicies(String today);

	/**
	 * @param cycleNumber
	 * @param submissionNumber
	 * @return List<WFMamSrcFile>
	 */
	List<WFMamSrcFile> findOneInWFSrcFile(int cycleNumber, int submissionNumber);

	/**
	 * @param wfMamSrcFile
	 */
	void create(WFMamSrcFile wfMamSrcFile);

	/**
	 * @param cycleNumber
	 * @return int
	 */
	int getMaxSequenceNumber(int cycleNumber);
}
