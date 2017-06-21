package com.peiwc.billing.process.billing1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("wfMamSrcGenRecs")
public class WFMamSrcGenRecs {
	
	@Autowired
	private CalcUnclearedBilledAmt calcUnclearedBilledAmt;
	
	@Autowired
	private CalcUnclearedUnBilledCreditAmt calcUnclearedUnBilledCreditAmt;
	
	@Autowired
	private ProcessPoliciesLessOrEqual2YearsOld processPoliciesLessOrEqual2YearsOld;
	
	@Autowired
	private ApplyCredits applyCredits;
	
	public void billingProcess(int cycleNumber){
		this.calcUnclearedBilledAmt.updWFMamSrcFileRec(cycleNumber);
		this.calcUnclearedUnBilledCreditAmt.wfMamSrcFileUpdRecDBI1(cycleNumber);
		this.processPoliciesLessOrEqual2YearsOld.processPolicies(cycleNumber);
	}

}
