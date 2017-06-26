package com.peiwc.billing.process.billing1;

public class NegPosAmounts {

	private float totalPosAmtDueBySubmission = 0;
	private float totalNegAmtDueBySubmission = 0;

	public float getTotalPosAmtDueBySubmission() {
		return totalPosAmtDueBySubmission;
	}

	public void setTotalPosAmtDueBySubmission(final float totalPosAmtDueBySubmission) {
		this.totalPosAmtDueBySubmission = totalPosAmtDueBySubmission;
	}

	public float getTotalNegAmtDueBySubmission() {
		return totalNegAmtDueBySubmission;
	}

	public void setTotalNegAmtDueBySubmission(final float totalNegAmtDueBySubmission) {
		this.totalNegAmtDueBySubmission = totalNegAmtDueBySubmission;
	}

}
