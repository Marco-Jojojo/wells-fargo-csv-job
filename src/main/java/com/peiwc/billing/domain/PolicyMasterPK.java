package com.peiwc.billing.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Objects;

/**
 * Embedded id for PolicyMaster
 *
 */
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

	/**
	 * @return policyNumber
	 */
	public String getPolicyNumber() {
		return policyNumber;
	}

	/**
	 * @param policyNumber
	 */
	public void setPolicyNumber(final String policyNumber) {
		this.policyNumber = policyNumber;
	}

	/**
	 * @return policySuffix
	 */
	public String getPolicySuffix() {
		return policySuffix;
	}

	/**
	 * @param policySuffix
	 */
	public void setPolicySuffix(final String policySuffix) {
		this.policySuffix = policySuffix;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(policyNumber, policySuffix);
	}

	@Override
	public boolean equals(final Object obj) {
		boolean equals = true;
		if (obj == null) {
			equals = false;
		} else {
			if (obj instanceof PolicyMasterPK) {
				equals = false;
			} else {
				final PolicyMasterPK other = (PolicyMasterPK) obj;
				equals = Objects.equal(other.policyNumber, this.policyNumber)
						&& Objects.equal(other.policySuffix, this.policySuffix);

			}
		}
		return equals;
	}

}
