package com.peiwc.billing.dao;

import com.peiwc.billing.domain.SprInsuredContact;
import com.peiwc.billing.domain.SprLocation;

public interface BillingProcessDAO {
	
	SprInsuredContact getContactInfo(int submissionNumber);

	SprLocation getLocation(int submissionNumber);

	
}
