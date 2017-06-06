/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.core.domain;

import java.util.Date;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Property;

/**
 * @author Zuned.Ahmed
 *
 */
public abstract class BaseEntity {
	
	@Id
	@Property("id")
	protected ObjectId id;
	
	@Property("added_on")
	protected Date addedOn = new Date();
	
	@Property("added_by")
	protected String addedBy = "System";
	
	@Property("external_identifier")
	private String externalIdentifier;

	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

	/**
	 * @return the addedOn
	 */
	public Date getAddedOn() {
		return addedOn;
	}

	/**
	 * @param addedOn the addedOn to set
	 */
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	/**
	 * @return the addedBy
	 */
	public String getAddedBy() {
		return addedBy;
	}

	/**
	 * @param addedBy the addedBy to set
	 */
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	@PrePersist
	public void init() {
		this.externalIdentifier = UUID.randomUUID().toString();
	}

	/**
	 * @return the externalIdentifier
	 */
	public String getExternalIdentifier() {
		return externalIdentifier;
	}

	/**
	 * @param externalIdentifier the externalIdentifier to set
	 */
	public void setExternalIdentifier(String externalIdentifier) {
		this.externalIdentifier = externalIdentifier;
	}
}
