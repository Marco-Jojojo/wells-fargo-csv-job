package com.peiwc.billing.process;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.WFMamErrLogRepository;
import com.peiwc.billing.dao.WFMamSrcFileDAO;
import com.peiwc.billing.domain.WFDBAName;
import com.peiwc.billing.domain.WFMamErrLog;
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFSPRName;
import com.peiwc.billing.domain.WFSPROptional;
import com.peiwc.billing.domain.WFUserInfo;

/**
 * @author alfonso.pech Process to fill WF_MAM_SRC_FILE with the user
 *         information by providing the cycleNumber.
 *
 */
@Component
public class BillingPart2Process {

	private static final Logger LOGGER = Logger.getLogger(BillingPart2Process.class);
	private static final String USER_NOT_FOUND = "Could not get user information";
	private static final String NAME_NOT_FOUND = "Could not get consolidated name";
	private static final String REPLACE = "";
	private static final String WF_REGEX = "([\\[\\])}/$*%\\\\^:\";~|\\{(])";
	private static final String PENDING_REC = "PENDING_REC";
	private static final String STATUS_CODE_EXPIRED = "2";
	private static final String STATUS_EXPIRED = "Expired";
	private static final String STATUS_ACTIVE = "Active";
	private static final String STATUS_DASH = "-";

	@Autowired
	private WFMamSrcFileDAO wfMamSrcFileDAO;
	@Autowired
	WFMamErrLogRepository wfMamErrLogRepository;
	@Autowired
	private BillingInformationProcess billingInformationProcess;

	/**
	 * @param cycleNumber
	 * @return boolean
	 */
	public boolean updateUserInfo(final int cycleNumber) {
		final boolean hasRunSuccessfully = true;
		boolean updateFlag = true;
		BillingPart2Process.LOGGER.debug("Begin billing part2 for current cycle");
		final List<WFMamSrcFile> wfMamList = wfMamSrcFileDAO.findByCycleNumber(cycleNumber);
		for (final WFMamSrcFile srcFile : wfMamList) {

			final int submissionNumber = srcFile.getSubmissionNumber();
			final List<WFUserInfo> users = billingInformationProcess.getUserInformation(submissionNumber);
			final List<WFDBAName> dbaNames = billingInformationProcess.getDBAName(submissionNumber);
			String consolidatedName = StringUtils.EMPTY;
			String address = StringUtils.EMPTY;
			String address2 = StringUtils.EMPTY;
			String city = StringUtils.EMPTY;
			String state = StringUtils.EMPTY;
			String zip = StringUtils.EMPTY;
			String statusDB = StringUtils.EMPTY;

			if (!CollectionUtils.isEmpty(users)) {
				final WFUserInfo user = users.iterator().next();
				zip = user.getZip();
				address = StringUtils.trim(user.getAddress());
				if (!StringUtils.isEmpty(user.getAddress2())) {
					address2 = StringUtils.trim(user.getAddress2());
				}
				city = user.getCity();
				state = user.getState();
				statusDB = user.getStatus();

			} else {
				final List<WFUserInfo> sprUsers = billingInformationProcess
						.getUserInformationSPRLocation(submissionNumber);
				if (!CollectionUtils.isEmpty(sprUsers)) {
					BillingPart2Process.LOGGER.debug("using SPR LOCATION for submissionNumber: " + submissionNumber);
					final WFUserInfo sprUser = sprUsers.iterator().next();

					zip = sprUser.getZip();
					address = StringUtils.trim(sprUser.getAddress());
					if (!StringUtils.isEmpty(sprUser.getAddress2())) {
						address2 = StringUtils.trim(sprUser.getAddress2());
					}
					city = sprUser.getCity();
					state = sprUser.getState();
					statusDB = sprUser.getStatus();

				} else {
					updateFlag = false;
					sendError(cycleNumber, srcFile.getId().getSequenceNumber(), BillingPart2Process.USER_NOT_FOUND);
				}
			}

			if (!CollectionUtils.isEmpty(dbaNames)) {
				final WFDBAName dbaName = dbaNames.iterator().next();
				if (!StringUtils.isEmpty(dbaName.getDbaName())) {
					consolidatedName = StringUtils.trim(dbaName.getDbaName());
				}
			} else {
				final List<WFSPRName> sprNames = billingInformationProcess.getSPRName(submissionNumber);
				if (!CollectionUtils.isEmpty(sprNames)) {
					final WFSPRName sprName = sprNames.iterator().next();
					if (!StringUtils.isEmpty(sprName.getEntityName())) {
						consolidatedName = StringUtils.trim(sprName.getEntityName());
					}
				} else {
					updateFlag = false;
					sendError(cycleNumber, srcFile.getId().getSequenceNumber(), BillingPart2Process.NAME_NOT_FOUND);
				}
			}

			srcFile.setSecondaryAuth(zip);
			srcFile.setConsolidatedName(removeSpecialCharacters(consolidatedName));
			srcFile.setAddress(removeSpecialCharacters(address));
			srcFile.setAddress2(removeSpecialCharacters(address2));
			srcFile.setCity(city);
			srcFile.setState(state);
			srcFile.setZip(zip);

			String status = StringUtils.EMPTY;
                                                      if (srcFile.getAmountDue() == 0) {
                                                           status = BillingPart2Process.STATUS_EXPIRED;
                                                      } else {
                                                            if (BillingPart2Process.STATUS_CODE_EXPIRED.equals(statusDB)) {
                                                                    status = BillingPart2Process.STATUS_EXPIRED;
                                                            } else {
                                                                    status = BillingPart2Process.STATUS_ACTIVE;
                                                            }
                                                      }
			srcFile.setStatusInvoice(status);

			if (BillingPart2Process.LOGGER.isDebugEnabled()) {
				BillingPart2Process.LOGGER.debug("srcFile null : " + srcFile == null);
				BillingPart2Process.LOGGER.debug("srcFile values : " + ToStringBuilder.reflectionToString(srcFile));
			}

			final List<WFSPROptional> optionals = billingInformationProcess.getOptional(submissionNumber);
			if (!CollectionUtils.isEmpty(optionals)) {
				final WFSPROptional optional = optionals.iterator().next();
				final String phone = StringUtils.EMPTY;
				if (!StringUtils.isEmpty(optional.getPhoneArea())) {
					srcFile.setPhone(StringUtils.join(optional.getPhoneArea(), BillingPart2Process.STATUS_DASH,
							optional.getPhonePrefix(), BillingPart2Process.STATUS_DASH, optional.getPhoneSuffix()));
				} else {
					srcFile.setPhone(phone);
				}
				final String email = StringUtils.EMPTY;
				if (!StringUtils.isEmpty(StringUtils.trim(optional.getEmail()))) {
					srcFile.setEmail(StringUtils.trim(optional.getEmail()));
				} else {
					srcFile.setEmail(email);
				}
			} else {
				srcFile.setPhone(StringUtils.EMPTY);
				srcFile.setEmail(StringUtils.EMPTY);
			}

			if (updateFlag) {
				wfMamSrcFileDAO.updateSrcFile(srcFile);
			}
		}

		return hasRunSuccessfully;
	}

	private void sendError(final int cycleNumber, final int sequenceNumber, final String errorMsg) {
		BillingPart2Process.LOGGER.debug("Billing error, could not get user information");
		final WFMamErrLog error = new WFMamErrLog();
		error.setCycleNumber(cycleNumber);
		error.setSequenceNumber(sequenceNumber);
		error.setDescription(errorMsg);
		error.setStatus(BillingPart2Process.PENDING_REC);
		wfMamErrLogRepository.saveAndFlush(error);

	}

	private String removeSpecialCharacters(final String string) {

		final String result = string.replaceAll(BillingPart2Process.WF_REGEX, BillingPart2Process.REPLACE);
		return result;
	}
}
