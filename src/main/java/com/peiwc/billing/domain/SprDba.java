package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SPR_DBA")
public class SprDba {

	@EmbeddedId
	private SprDbaPK id;

	@Column(name = "DBA_NAME")
	private String dbaName;

	public SprDbaPK getId() {
		return id;
	}

	public void setId(final SprDbaPK id) {
		this.id = id;
	}

	public String getDbaName() {
		return dbaName;
	}

	public void setDbaName(final String dbaName) {
		this.dbaName = dbaName;
	}

}
