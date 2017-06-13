package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * DAO For retrieving {@link WFMamSrcFile } .
 */
public interface WFMamSrcFileDAO {

	/**
	 * gets all the {@link WFMamSrcFile} related to the current cycle number.
	 *
	 * @param cycleNumber
	 *            cycle number that contains
	 * @return list of {@link WFMamSrcFile} related to cycle number
	 */
	public List<WFMamSrcFile> getMamRecordsFromCycleNumber(int cycleNumber);

}
