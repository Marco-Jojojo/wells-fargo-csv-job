package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * DAO For retrieving {@link WFMamSrcFile } .
 */
public interface WFMamSrcFileDAO {
	/**
	 * find mam src files by cycle number.
	 *
	 * @param cycleNumber
	 * @return a list of {@link WFMamSrcFile}}
	 */
	List<WFMamSrcFile> findByCycleNumber(int cycleNumber);

	/**
	 * Inserts a mamsrc file into database.
	 *
	 * @param srcFile
	 */
	void insertSrcFile(WFMamSrcFile srcFile);

	/**
	 * Updates mamsrcfile in database.
	 * 
	 * @param srcFile
	 */
	void updateSrcFile(WFMamSrcFile srcFile);
}
