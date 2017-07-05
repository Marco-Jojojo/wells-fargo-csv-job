package com.peiwc.billing.domain;

/**
 * @author alfonso.pech
 *
 */
public class WFSPROptional {

	private String phoneArea;
	private String phonePrefix;
	private String phoneSuffix;
	private String email;

	/**
	 * @return phoneArea
	 */
	public String getPhoneArea() {
		return phoneArea;
	}

	/**
	 * @param phoneArea
	 */
	public void setPhoneArea(final String phoneArea) {
		this.phoneArea = phoneArea;
	}

	/**
	 * @return phonePrefix
	 */
	public String getPhonePrefix() {
		return phonePrefix;
	}

	/**
	 * @param phonePrefix
	 */
	public void setPhonePrefix(final String phonePrefix) {
		this.phonePrefix = phonePrefix;
	}

	/**
	 * @return phoneSuffix
	 */
	public String getPhoneSuffix() {
		return phoneSuffix;
	}

	/**
	 * @param phoneSuffix
	 */
	public void setPhoneSuffix(final String phoneSuffix) {
		this.phoneSuffix = phoneSuffix;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

}
