package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

public interface CalcUnclearedUnBilledCreditAmtDAO {
	
	List<WFMamSrcFile> findAll();

}
