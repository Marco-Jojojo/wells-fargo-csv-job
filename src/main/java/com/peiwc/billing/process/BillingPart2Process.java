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
import com.peiwc.billing.domain.WFMamErrLog;
import com.peiwc.billing.domain.WFMamSrcFile;
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
			if (CollectionUtils.isEmpty(users)) {
				BillingPart2Process.LOGGER.debug("Billing error, could not get user information");
				final WFMamErrLog error = new WFMamErrLog();
				error.setCycleNumber(cycleNumber);
				error.setSequenceNumber(srcFile.getId().getSequenceNumber());
				error.setDescription("Billing 2 error");
				error.setStatus("PENDING_REC");
				wfMamErrLogRepository.saveAndFlush(error);
			} else {
				final WFUserInfo user = users.iterator().next();
				final String consolidatedName = StringUtils.join(user.getFirstName().trim(), " ",
						user.getLastName().trim());
				final String phone = StringUtils.join(user.getPhoneArea(), " ", user.getPhonePrefix(),
						user.getPhoneSuffix());
				srcFile.setConsolidatedName(consolidatedName);
				srcFile.setPhone(phone);
				final String email = " ";
				if (user.getEmail().isEmpty())
					srcFile.setEmail(email);
				else
					srcFile.setEmail(user.getEmail().trim());

				srcFile.setAddress(user.getAddress().trim());
				final String address2 = " ";
				if (user.getAddress2().isEmpty())
					srcFile.setAddress2(address2);
				else
					srcFile.setAddress2(user.getAddress2().trim());
				srcFile.setCity(user.getCity().trim());
				srcFile.setState(user.getState());
				srcFile.setZip(user.getZip());
				String status = "";
				if (user.getStatus() == "2") {
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
