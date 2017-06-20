package com.peiwc.billing.process;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.peiwc.billing.dao.BillingProcessDAO;
import com.peiwc.billing.domain.SprInsuredContact;
import com.peiwc.billing.domain.SprLocation;

public class BillingInformationProcess {

	@Autowired
	private BillingProcessDAO billingProcessDAO;

	public String getName(final String submissionNumber) {

		SprInsuredContact contact = billingProcessDAO.getContactInfo(submissionNumber);

		String firstName = contact.getFirst_name();
		String lastName = contact.getLast_name();

		String result = StringUtils.join(firstName, " ", lastName);

		return result;
	}

	public String getPhone(final String submissionNumber) {

		SprInsuredContact contact = billingProcessDAO.getContactInfo(submissionNumber);

		String area = contact.getPhone_area_code();
		String prefix = contact.getPhone_prefix();
		String suffix = contact.getPhone_suffix();

		String result = StringUtils.join(area, " ", prefix, suffix);

		return result;
	}

	public String getEmail(final String submissionNumber) {

		return billingProcessDAO.getContactInfo(submissionNumber).getEmail_address();
	}

	public String getAddress(final String submissionNumber) {

		return billingProcessDAO.getLocation(submissionNumber).getAddr1();
	}

	public String getAddress2(final String submissionNumber) {

		return billingProcessDAO.getLocation(submissionNumber).getAddr_2();
	}

	public String getCity(final String submissionNumber) {

		return billingProcessDAO.getLocation(submissionNumber).getCity();
	}

	public String getState(final String submissionNumber) {

		return billingProcessDAO.getLocation(submissionNumber).getState();
	}

	public String getZip(final String submissionNumber) {

		return billingProcessDAO.getLocation(submissionNumber).getZip1();
	}

}
