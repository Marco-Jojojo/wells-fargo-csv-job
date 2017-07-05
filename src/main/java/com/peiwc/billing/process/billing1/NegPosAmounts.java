package com.peiwc.billing.process.billing1;

/**
 * @author jolivarria
 *
 */
public class NegPosAmounts {

	private float totalPosAmtDueBySubmission = 0;
	private float totalNegAmtDueBySubmission = 0;

	/**
	 * @return totalPosAmtDueBySubmission
	 */
	public float getTotalPosAmtDueBySubmission() {
		return totalPosAmtDueBySubmission;
	}

	/**
	 * @param totalPosAmtDueBySubmission
	 */
	public void setTotalPosAmtDueBySubmission(final float totalPosAmtDueBySubmission) {
		this.totalPosAmtDueBySubmission = totalPosAmtDueBySubmission;
	}

	/**
	 * @return totalNegAmtDueBySubmission
	 */
	public float getTotalNegAmtDueBySubmission() {
		return totalNegAmtDueBySubmission;
	}

	/**
	 * @param totalNegAmtDueBySubmission
	 */
	public void setTotalNegAmtDueBySubmission(final float totalNegAmtDueBySubmission) {
		this.totalNegAmtDueBySubmission = totalNegAmtDueBySubmission;
	}

}
