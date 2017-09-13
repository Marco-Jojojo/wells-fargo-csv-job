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

	@Column(name = "POLICY_PREFIX_1")
	private String policyPrefix1;

	@Column(name = "POLICY_PREFIX_2")
	private String policyPrefix2;

	@Column(name = "POLICY_NUMBER")
	private String policyNumber;

	@Column(name = "POLICY_SUFFIX")
	private String policySuffix;

	/**
	 * @return policyPrefix1
	 */
	public String getPolicyPrefix1() {
		return policyPrefix1;
	}

	/**
	 * @param policyPrefix1
	 */
	public void setPolicyPrefix1(final String policyPrefix1) {
		this.policyPrefix1 = policyPrefix1;
	}

	/**
	 * @return policyPrefix2
	 */
	public String getPolicyPrefix2() {
		return policyPrefix2;
	}

	/**
	 * @param policyPrefix2
	 */
	public void setPolicyPrefix2(final String policyPrefix2) {
		this.policyPrefix2 = policyPrefix2;
	}

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
				equals = Objects.equal(other.policyPrefix1, this.policyPrefix1)
						&& Objects.equal(other.policyPrefix2, this.policyPrefix2)
						&& Objects.equal(other.policyNumber, this.policyNumber)
						&& Objects.equal(other.policySuffix, this.policySuffix);

			}
		}
		return equals;
	}

}
