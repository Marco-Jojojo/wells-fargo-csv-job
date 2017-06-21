package com.peiwc.billing.process.billingP1;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("wfMamSrcGenRecs")
public class WFMamSrcGenRecs {
	
	private static final Logger LOGGER = Logger.getLogger(WFMamSrcGenRecs.class);
	
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
