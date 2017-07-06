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
@Table(name = "SPR_ENTITY_FILE")
public class SprEntityFile {

	@EmbeddedId
	private SprEntityFilePK id;

	@Column(name = "ENTITY_NAME")
	private String entityName;

	/**
	 * @return id
	 */
	public SprEntityFilePK getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(final SprEntityFilePK id) {
		this.id = id;
	}

	/**
	 * @return entityName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @param entityName
	 */
	public void setEntityName(final String entityName) {
		this.entityName = entityName;
	}
}
