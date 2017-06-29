package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

public interface CalcUnclearedBilledAmtDAO {

	List<WFMamSrcFile> findAll();

	void create(WFMamSrcFile wfMamSrcFile);

}