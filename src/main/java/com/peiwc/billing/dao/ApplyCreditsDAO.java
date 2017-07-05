package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * @author jolivarria
 *
 */
public interface ApplyCreditsDAO {

	/**
	 * @param cycleNumber
	 * @return List<WFMamSrcFile>
	 */
	List<WFMamSrcFile> findAll(int cycleNumber);

	/**
	 * @param cycleNumber
	 * @param secondaryAuth
	 * @return List<WFMamSrcFile>
	 */
	List<WFMamSrcFile> getAmountsDue(int cycleNumber, String secondaryAuth);

	/**
	 * @param wfMamSrcFile
	 */
	void updateAmtDue(WFMamSrcFile wfMamSrcFile);

}
