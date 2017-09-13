package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author alfonso.pech Entity for billing part 2
 *
 */
@Entity
@Table(name = "SPR_INSURED_CONTACT_")
public class SprInsuredContact {

	@EmbeddedId
	private SprInsuredContactPK id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name = "PHONE_AREA_CODE")
	private String phoneAreaCode;

	@Column(name = "PHONE_PREFIX")
	private String phonePrefix;

	@Column(name = "PHONE_SUFFIX")
	private String phoneSuffix;

	/**
	 * @return id
	 */
	public SprInsuredContactPK getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(final SprInsuredContactPK id) {
		this.id = id;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 */
	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return phoneAreaCode
	 */
	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}

	/**
	 * @param phoneAreaCode
	 */
	public void setPhoneAreaCode(final String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
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

}
