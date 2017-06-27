package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFDBAName;
import com.peiwc.billing.domain.WFSPRName;
import com.peiwc.billing.domain.WFUserInfo;

public interface BillingProcessDAO {

	List<WFUserInfo> getUserInformation(int submissionNumber);

	List<WFDBAName> getUserDBAName(final int submissionNumber);

	List<WFSPRName> getUserSPRName(final int submissionNumber);

}
