package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * @author jolivarria
 *
 */
public interface CalcUnclearedBilledAmtDAO {

	/**
	 * @return List<WFMamSrcFile>
	 */
	List<WFMamSrcFile> findAll();

	/**
	 * @param wfMamSrcFile
	 */
	void create(WFMamSrcFile wfMamSrcFile);

}