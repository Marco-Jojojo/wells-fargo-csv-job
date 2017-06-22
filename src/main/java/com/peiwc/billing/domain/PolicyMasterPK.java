package com.peiwc.billing.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PolicyMasterPK implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "POLICY_NUMBER")
	private String policyNumber;

	@Column(name = "POLICY_SUFFIX")
	private String policySuffix;

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(final String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicySuffix() {
		return policySuffix;
	}

	public void setPolicySuffix(final String policySuffix) {
		this.policySuffix = policySuffix;
	}

}
