package com.peiwc.billing.process;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
		BillingPart2Process.LOGGER.info("Begin billing part2 for current cycle");
		final List<WFMamSrcFile> wfMamList = wfMamSrcFileDAO.findByCycleNumber(cycleNumber);

		for (final WFMamSrcFile wfMamSrcFile : wfMamList) {

			final int submissionNumber = Integer.parseInt(wfMamSrcFile.getSecondaryAuth());
			final List<WFUserInfo> users = billingInformationProcess.getUserInformation(submissionNumber);
			final WFMamSrcFile srcFile = new WFMamSrcFile();
			if (CollectionUtils.isEmpty(users)) {
				BillingPart2Process.LOGGER.info("Billing error, could not get user information");
				final WFMamErrLog error = new WFMamErrLog();
				error.setCycleNumber(cycleNumber);
				error.setSequenceNumber(wfMamSrcFile.getId().getSequenceNumber());
				error.setDescription("Billing 2 error");
				error.setStatus("N");
				wfMamErrLogRepository.saveAndFlush(error);
			}

			else {

				final WFUserInfo user = users.iterator().next();
				final String consolidatedName = StringUtils.join(user.getFirstName(), " ", user.getLastName());
				final String phone = StringUtils.join(user.getPhoneArea(), " ", user.getPhonePrefix(),
						user.getPhoneSuffix());
				srcFile.setConsolidatedName(consolidatedName);
				srcFile.setPhone(phone);
				srcFile.setEmail(user.getEmail());
				srcFile.setAddress(user.getAddress());
				srcFile.setAddress2(user.getAddress2());
				srcFile.setCity(user.getCity());
				srcFile.setState(user.getState());
				srcFile.setZip(user.getZip());

				wfMamSrcFileDAO.saveAndFlush(srcFile);
			}
		}

	}

}
