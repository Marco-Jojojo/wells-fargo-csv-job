package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFUserInfo;

public interface BillingProcessDAO {

	List<WFUserInfo> getUserInformation(int submissionNumber);

}
