package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * DAO For retrieving {@link WFMamSrcFile } .
 */
public interface WFMamSrcFileDAO {

	List<WFMamSrcFile> findByCycleNumber(int cycleNumber);

	void insertSrcFile(WFMamSrcFile srcFile);

	void updateSrcFile(WFMamSrcFile srcFile);

}
