package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity for billing part 2
 *
 */
@Entity
@Table(name = "SPR_DBA")
public class SprDba {

	@EmbeddedId
	private SprDbaPK id;

	@Column(name = "DBA_NAME")
	private String dbaName;

	/**
	 * @return id
	 */
	public SprDbaPK getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(final SprDbaPK id) {
		this.id = id;
	}

	/**
	 * @return dbaName
	 */
	public String getDbaName() {
		return dbaName;
	}

	/**
	 * @param dbaName
	 */
	public void setDbaName(final String dbaName) {
		this.dbaName = dbaName;
	}

}
