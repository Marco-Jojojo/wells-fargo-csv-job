package com.peiwc.billing.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SPR_ENTITY_FILE")
public class SprEntityFile {

	@EmbeddedId
	private SprEntityFilePK id;

	@Column(name = "ENTITY_NAME")
	private String entityName;
}
