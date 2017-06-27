package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "POLICY_MASTER")
public class SprDba {

	@EmbeddedId
	private SprDbaPK id;

	@Column(name = "DBA_NAME")
	private String dbaName;

}
