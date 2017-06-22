package com.peiwc.billing.process.billing1;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.CalcUnclearedUnBilledCreditAmtDAO;
import com.peiwc.billing.domain.WFMamSrcFile;

@Component("calcUnclearedUnBilledCreditAmt")
public class CalcUnclearedUnBilledCreditAmt {
	
	@Autowired
	private CalcUnclearedUnBilledCreditAmtDAO calcUnclearedUnBilledCreditAmtDAO;

	public void wfMamSrcFileUpdRecDBI1(int cycleNumber) {
		List<WFMamSrcFile> recordsFromCM = this.calcUnclearedUnBilledCreditAmtDAO.findAll();
		for (WFMamSrcFile recordFromCM : recordsFromCM) {
			List<WFMamSrcFile> recordsFromSrcFileDBI1 = this.calcUnclearedUnBilledCreditAmtDAO.findOneByDBI1(cycleNumber, recordFromCM.getSecondaryAuth());
			if (!CollectionUtils.isEmpty(recordsFromSrcFileDBI1)) {
				float amtDueWFSrcFile = recordsFromSrcFileDBI1.iterator().next().getAmountDue();
				float amtApplied;
				float amt_due;
				if ( amtDueWFSrcFile <= Math.abs(recordFromCM.getAmountDue())) {
					amtApplied = amtDueWFSrcFile * (-1);
					amt_due = amtDueWFSrcFile + amtApplied;
					this.calcUnclearedUnBilledCreditAmtDAO.updateDBI1(cycleNumber, recordsFromSrcFileDBI1.iterator().next().getSecondaryAuth(), amt_due);
				} else {
					amtApplied = recordFromCM.getAmountDue();
					amt_due = amtDueWFSrcFile + amtApplied;
					this.calcUnclearedUnBilledCreditAmtDAO.updateDBI1(cycleNumber, recordsFromSrcFileDBI1.iterator().next().getSecondaryAuth(), amt_due);
				}
			} else{
				List<WFMamSrcFile> recordsFromSrcFileDBI0 = this.calcUnclearedUnBilledCreditAmtDAO.findOneByDBI0(cycleNumber, recordFromCM.getSecondaryAuth(), recordFromCM.getInvoiceNumber());
				if (!CollectionUtils.isEmpty(recordsFromSrcFileDBI0)) {
					float amtDueWFSrcFile = recordsFromSrcFileDBI0.iterator().next().getAmountDue();
					float amt_due = amtDueWFSrcFile + recordFromCM.getAmountDue();
					this.calcUnclearedUnBilledCreditAmtDAO.updateDBI0(cycleNumber, recordFromCM.getSecondaryAuth(), recordFromCM.getInvoiceNumber(), amt_due);
				} else {
					this.calcUnclearedUnBilledCreditAmtDAO.create(recordFromCM);
				}
			}			
		}
		
	}

}
