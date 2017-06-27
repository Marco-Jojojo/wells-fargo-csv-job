package com.peiwc.billing.process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.BillingProcessDAO;
import com.peiwc.billing.domain.WFDBAName;
import com.peiwc.billing.domain.WFSPRName;
import com.peiwc.billing.domain.WFUserInfo;

@Component
public class BillingInformationProcess {

	@Autowired
	private BillingProcessDAO billingProcessDAO;

	public List<WFUserInfo> getUserInformation(final int submissionNumber) {
		final List<WFUserInfo> result = billingProcessDAO.getUserInformation(submissionNumber);

		return result;
	}

	public List<WFDBAName> getDBAName(final int submissionNumber) {
		final List<WFDBAName> result = billingProcessDAO.getUserDBAName(submissionNumber);

		return result;
	}

	public List<WFSPRName> getSPRName(final int submissionNumber) {
		final List<WFSPRName> result = billingProcessDAO.getUserSPRName(submissionNumber);

		return result;
	}

}
