package com.peiwc.billing.process;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.peiwc.billing.dao.BillingProcessDAO;
import com.peiwc.billing.domain.SprInsuredContact;

public class BillingInformationProcess {

	@Autowired
	private BillingProcessDAO billingProcessDAO;

	public String getName(final int submissionNumber) {

		SprInsuredContact contact = billingProcessDAO.getContactInfo(submissionNumber);
		
		String firstName = contact.getFirstName();
		String lastName = contact.getLastName();

		String result = StringUtils.join(firstName, " ", lastName);

		return result;
	}

	public String getPhone(final int submissionNumber) {

		SprInsuredContact contact = billingProcessDAO.getContactInfo(submissionNumber);

		String area = contact.getPhoneAreaCode();
		String prefix = contact.getPhonePrefix();
		String suffix = contact.getPhoneSuffix();

		String result = StringUtils.join(area, " ", prefix, suffix);

		return result;
	}

	public String getEmail(final int submissionNumber) {

		return billingProcessDAO.getContactInfo(submissionNumber).getEmailAddress();
	}

	public String getAddress(final int submissionNumber) {

		return billingProcessDAO.getLocation(submissionNumber).getAddr1();
	}

	public String getAddress2(final int submissionNumber) {

		return billingProcessDAO.getLocation(submissionNumber).getAddr2();
	}

	public String getCity(final int submissionNumber) {

		return billingProcessDAO.getLocation(submissionNumber).getCity();
	}

	public String getState(final int submissionNumber) {

		return billingProcessDAO.getLocation(submissionNumber).getState();
	}

	public String getZip(final int submissionNumber) {

		return billingProcessDAO.getLocation(submissionNumber).getZip1();
	}

}
