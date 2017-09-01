package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * @author jolivarria
 *
 */
public interface ProcessPoliciesZeroAndFutureDAO {

	/**
	 * @param twoYearsBefore
	 * @param today
	 * @return List<WFMamSrcFile>
	 */
	List<WFMamSrcFile> findAllPoliciesForZeroBills();

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

}