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
@Table(name = "SPR_LOCATION")
public class SprLocation {

	@EmbeddedId
	private SprLocationPK id;

	@Column(name = "ADDR_1")
	private String addr1;

	@Column(name = "ADDR_2")
	private String addr2;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "ZIP1")
	private String zip1;

	/**
	 * @return id
	 */
	public SprLocationPK getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(final SprLocationPK id) {
		this.id = id;
	}

	/**
	 * @return addr1
	 */
	public String getAddr1() {
		return addr1;
	}

	/**
	 * @param addr1
	 */
	public void setAddr1(final String addr1) {
		this.addr1 = addr1;
	}

	/**
	 * @return addr2
	 */
	public String getAddr2() {
		return addr2;
	}

	/**
	 * @param addr2
	 */
	public void setAddr2(final String addr2) {
		this.addr2 = addr2;
	}

	/**
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @return zip1
	 */
	public String getZip1() {
		return zip1;
	}

	/**
	 * @param zip1
	 */
	public void setZip1(final String zip1) {
		this.zip1 = zip1;
	}
}
