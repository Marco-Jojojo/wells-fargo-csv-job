package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFDBAName;
import com.peiwc.billing.domain.WFSPRName;
import com.peiwc.billing.domain.WFSPROptional;
import com.peiwc.billing.domain.WFUserInfo;

/**
 * @author alfonso.pech
 *
 */
public interface BillingProcessDAO {

	/**
	 * @param submissionNumber
	 * @return List<WFUserInfo>
	 */
	List<WFUserInfo> getUserInformation(int submissionNumber);

	/**
	 * @param submissionNumber
	 * @return List<WFDBAName>
	 */
	List<WFDBAName> getUserDBAName(int submissionNumber);

	/**
	 * @param submissionNumber
	 * @return List<WFSPRName>
	 */
	List<WFSPRName> getUserSPRName(int submissionNumber);

	/**
	 * @param submissionNumber
	 * @return List<WFSPROptional>
	 */
	List<WFSPROptional> getUserOptional(int submissionNumber);

}
