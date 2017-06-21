package com.peiwc.billing.process.billing1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.CalcUnclearedUnBilledCreditAmtDAO;
import com.peiwc.billing.domain.WFMamSrcFile;

@Component("calcUnclearedUnBilledCreditAmt")
public class CalcUnclearedUnBilledCreditAmt {

	@Autowired
	private CalcUnclearedUnBilledCreditAmtDAO calcUnclearedUnBilledCreditAmtDAO;

	public void wfMamSrcFileUpdRecDBI1(final int cycleNumber) {
		final List<WFMamSrcFile> rows = this.calcUnclearedUnBilledCreditAmtDAO.findAll();
		for (final WFMamSrcFile row : rows) {
			// TODO: continue...
		}

	}

}
