package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamSrcFile;

public interface WFMamSrcFileDAO {

	public List<WFMamSrcFile> getMamRecordsFromCycleNumber(int cycleNumber);

}
