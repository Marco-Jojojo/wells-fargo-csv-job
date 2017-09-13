package com.peiwc.billing.process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.BillingProcessDAO;
import com.peiwc.billing.domain.WFDBAName;
import com.peiwc.billing.domain.WFSPRName;
import com.peiwc.billing.domain.WFSPROptional;
import com.peiwc.billing.domain.WFUserInfo;

/**
 * @author alfonso.pech Process to collect the user information by
 *         submissionNumber
 *
 */
@Component
public class BillingInformationProcess {

	@Autowired
	private BillingProcessDAO billingProcessDAO;

	/**
	 * @param submissionNumber
	 * @return List<WFUserInfo>
	 */
	public List<WFUserInfo> getUserInformation(final int submissionNumber) {
		final List<WFUserInfo> result = billingProcessDAO.getUserInformation(submissionNumber);

		return result;
	}

	/**
	 * @param submissionNumber
	 * @return List<WFUserInfo>
	 */
	public List<WFUserInfo> getUserInformationSPRLocation(final int submissionNumber) {
		final List<WFUserInfo> result = billingProcessDAO.getUserInformationSPRLocation(submissionNumber);

		return result;
	}

	/**
	 * @param submissionNumber
	 * @return List<WFDBAName>
	 */
	public List<WFDBAName> getDBAName(final int submissionNumber) {
		final List<WFDBAName> result = billingProcessDAO.getUserDBAName(submissionNumber);

		return result;
	}

	/**
	 * @param submissionNumber
	 * @return List<WFSPRName>
	 */
	public List<WFSPRName> getSPRName(final int submissionNumber) {
		final List<WFSPRName> result = billingProcessDAO.getUserSPRName(submissionNumber);

		return result;
	}

	/**
	 * @param submissionNumber
	 * @return List<WFSPROptional>
	 */
	public List<WFSPROptional> getOptional(final int submissionNumber) {
		final List<WFSPROptional> result = billingProcessDAO.getUserOptional(submissionNumber);

		return result;
	}

}
