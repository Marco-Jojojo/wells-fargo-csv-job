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
import com.peiwc.billing.domain.WFUserInfo;

@Component
public class BillingPart2Process {

	private static final Logger LOGGER = Logger.getLogger(BillingPart2Process.class);

	@Autowired
	private WFMamSrcFileDAO wfMamSrcFileDAO;

	@Autowired
	WFMamErrLogRepository wfMamErrLogRepository;

	@Autowired
	private BillingInformationProcess billingInformationProcess;

	public void updateUserInfo(final int cycleNumber) {
		BillingPart2Process.LOGGER.debug("Begin billing part2 for current cycle");
		final List<WFMamSrcFile> wfMamList = wfMamSrcFileDAO.findByCycleNumber(cycleNumber);
		for (final WFMamSrcFile srcFile : wfMamList) {
			final int submissionNumber = Integer.parseInt(srcFile.getSecondaryAuth());
			final List<WFUserInfo> users = billingInformationProcess.getUserInformation(submissionNumber);
			final List<WFDBAName> dbaNames = billingInformationProcess.getDBAName(submissionNumber);
			final List<WFSPRName> sprNames = billingInformationProcess.getSPRName(submissionNumber);

			if (CollectionUtils.isEmpty(users)) {
				BillingPart2Process.LOGGER.debug("Billing error, could not get user information");
				final WFMamErrLog error = new WFMamErrLog();
				error.setCycleNumber(cycleNumber);
				error.setSequenceNumber(srcFile.getId().getSequenceNumber());
				error.setDescription("Could not get user information");
				error.setStatus("PENDING_REC");
				wfMamErrLogRepository.saveAndFlush(error);
			} else {
				final WFUserInfo user = users.iterator().next();
				final WFDBAName dbaName = dbaNames.iterator().next();
				final WFSPRName sprName = sprNames.iterator().next();

				String consolidatedName = "";
				if (!StringUtils.isEmpty(dbaName.getDbaName())) {
					consolidatedName = dbaName.getDbaName();
				} else {
					if (!StringUtils.isEmpty(sprName.getEntityName())) {
						consolidatedName = sprName.getEntityName();
					}
				}
				srcFile.setConsolidatedName(consolidatedName);

				final String phone = StringUtils.join(user.getPhoneArea(), user.getPhonePrefix(),
						user.getPhoneSuffix());
				srcFile.setPhone(phone);

				final String email = StringUtils.EMPTY;
				if (!StringUtils.isEmpty(user.getEmail())) {
					srcFile.setEmail(StringUtils.trim(user.getEmail()));
				} else {
					srcFile.setEmail(email);
				}

				srcFile.setAddress(StringUtils.trim(user.getAddress()));

				final String address2 = StringUtils.EMPTY;
				if (!StringUtils.isEmpty(user.getAddress2())) {
					srcFile.setAddress2(StringUtils.trim(user.getAddress2()));
				} else {
					srcFile.setAddress2(address2);
				}

				srcFile.setCity(StringUtils.trim(user.getCity()));
				srcFile.setState(user.getState());
				srcFile.setZip(StringUtils.trim(user.getZip()));

				String status = StringUtils.EMPTY;
				if ("2".equals(user.getStatus())) {
					status = "Expired";
				} else {
					status = "Active";
				}
				srcFile.setStatusInvoice(status);
				if (BillingPart2Process.LOGGER.isDebugEnabled()) {
					BillingPart2Process.LOGGER.debug("srcFile null : " + srcFile == null);
					BillingPart2Process.LOGGER.debug("srcFile values : " + ToStringBuilder.reflectionToString(srcFile));
				}
				wfMamSrcFileDAO.updateSrcFile(srcFile);
			}
		}
	}
}
