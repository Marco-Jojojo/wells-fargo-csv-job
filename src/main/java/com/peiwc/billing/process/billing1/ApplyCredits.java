package com.peiwc.billing.process.billing1;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.ApplyCreditsDAO;
import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * @author jolivarria
 *
 */
@Component("applyCredits")
public class ApplyCredits {

	private static final Logger LOGGER = Logger.getLogger(ApplyCredits.class);

	@Autowired
	private ApplyCreditsDAO applyCreditsDAO;

	/**
	 * calculate Amount due for WF_MAM_SRC_FILE
	 * 
	 * @param cycleNumber
	 */
	public void applyCreditsProcess(final int cycleNumber) {
		ApplyCredits.LOGGER.info("PROCESS STATUS: Starting ApplyCredits.applyCreditsProcess");
		final List<WFMamSrcFile> recordsFromWFMamSrcFile = this.applyCreditsDAO.findAll(cycleNumber);
		ApplyCredits.LOGGER.info("PROCESS STATUS: Getting all records: " + recordsFromWFMamSrcFile.size());
		String tempSecondaryAuth = "";
		NegPosAmounts negPosAmounts = new NegPosAmounts();
		for (final WFMamSrcFile wfMamSrcFile : recordsFromWFMamSrcFile) {
			if (tempSecondaryAuth.equals(wfMamSrcFile.getSecondaryAuth())) {
				tempSecondaryAuth = wfMamSrcFile.getSecondaryAuth();
				negPosAmounts = this.setNegPosAmounts(wfMamSrcFile, negPosAmounts);
			} else {
				tempSecondaryAuth = wfMamSrcFile.getSecondaryAuth();
				negPosAmounts.setTotalNegAmtDueBySubmission(0);
				negPosAmounts.setTotalPosAmtDueBySubmission(0);
				final List<WFMamSrcFile> amountsFromSrcFile = this.applyCreditsDAO.getAmountsDue(cycleNumber,
						wfMamSrcFile.getSecondaryAuth());
				for (final WFMamSrcFile amountFromSF : amountsFromSrcFile) {
					final float amtDue = amountFromSF.getAmountDue();
					if (amtDue < 0) {
						negPosAmounts
								.setTotalNegAmtDueBySubmission(negPosAmounts.getTotalNegAmtDueBySubmission() + amtDue);
					} else {
						negPosAmounts
								.setTotalPosAmtDueBySubmission(negPosAmounts.getTotalPosAmtDueBySubmission() + amtDue);
					}
				}
				negPosAmounts = this.setNegPosAmounts(wfMamSrcFile, negPosAmounts);
			}
		}
		ApplyCredits.LOGGER.info("PROCESS STATUS: Ending ApplyCredits.applyCreditsProcess");

	}

	private NegPosAmounts setNegPosAmounts(final WFMamSrcFile wfMamSrcFile, final NegPosAmounts negPosAmounts) {
		if (negPosAmounts.getTotalNegAmtDueBySubmission() != 0 && negPosAmounts.getTotalPosAmtDueBySubmission() != 0) {
			if (negPosAmounts.getTotalPosAmtDueBySubmission() >= Math
					.abs(negPosAmounts.getTotalNegAmtDueBySubmission())) {
				if (wfMamSrcFile.getAmountDue() <= 0) {
					wfMamSrcFile.setAmountDue(0);
				} else {
					if (wfMamSrcFile.getAmountDue() >= Math.abs(negPosAmounts.getTotalNegAmtDueBySubmission())) {
						wfMamSrcFile.setAmountDue(
								wfMamSrcFile.getAmountDue() + negPosAmounts.getTotalNegAmtDueBySubmission());
						negPosAmounts.setTotalNegAmtDueBySubmission(0);
					} else {
						negPosAmounts.setTotalNegAmtDueBySubmission(
								negPosAmounts.getTotalNegAmtDueBySubmission() + wfMamSrcFile.getAmountDue());
						wfMamSrcFile.setAmountDue(0);
					}

				}
			} else {
				if (wfMamSrcFile.getAmountDue() >= 0) {
					wfMamSrcFile.setAmountDue(0);
				} else {
					if (Math.abs(wfMamSrcFile.getAmountDue()) >= negPosAmounts.getTotalPosAmtDueBySubmission()) {
						wfMamSrcFile.setAmountDue(
								wfMamSrcFile.getAmountDue() + negPosAmounts.getTotalPosAmtDueBySubmission());
						negPosAmounts.setTotalPosAmtDueBySubmission(0);
					} else {
						negPosAmounts.setTotalPosAmtDueBySubmission(
								negPosAmounts.getTotalPosAmtDueBySubmission() + wfMamSrcFile.getAmountDue());
						wfMamSrcFile.setAmountDue(0);
					}
				}
			}
			this.applyCreditsDAO.updateAmtDue(wfMamSrcFile);
		}

		return negPosAmounts;
	}
}
