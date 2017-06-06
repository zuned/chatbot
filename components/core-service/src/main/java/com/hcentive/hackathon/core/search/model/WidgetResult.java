package com.hcentive.hackathon.core.search.model;
import java.util.LinkedHashMap;
/**
 * All rights reserved. hCentive Hackathon.
 */
import java.util.Map;

import com.hcentive.hackathon.core.domain.Incident.IncidentStatus;

/**
 * @author Zuned.Ahmed
 *
 */
public class WidgetResult {
	
	private String identifier;
	private String displayName;
	private Map<String,Integer> statusWiseCount;
	
	public WidgetResult(String identifier){
		this(identifier , new LinkedHashMap<String,Integer>(0));
		for(IncidentStatus staus: IncidentStatus.values()){
			this.statusWiseCount.put(staus.name(), 0);
		}
	}
	public WidgetResult(String identifier ,Map<String,Integer> statusWiseCount ){
		this.identifier = identifier;
		this.statusWiseCount = statusWiseCount;
	}
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	/**
	 * @return the statusWiseCount
	 */
	public Map<String, Integer> getStatusWiseCount() {
		return statusWiseCount;
	}
	/**
	 * @param statusWiseCount the statusWiseCount to set
	 */
	public void setStatusWiseCount(Map<String, Integer> statusWiseCount) {
		this.statusWiseCount = statusWiseCount;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
}
