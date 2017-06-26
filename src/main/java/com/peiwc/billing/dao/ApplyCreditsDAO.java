package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

public interface ApplyCreditsDAO {

	List<WFMamSrcFile> findAll(int cycleNumber);

	float getAmtDue(int cycleNumber, String secondaryAuth);

	void updateAmtDue(WFMamSrcFile wfMamSrcFile);

}
