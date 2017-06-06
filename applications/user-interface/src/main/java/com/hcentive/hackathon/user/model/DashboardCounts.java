/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.user.model;

/**
 * @author Anubhav.Kapoor
 *
 */
public class DashboardCounts {

	private String type;
	
	private long count;

	/**
	 * @param type
	 * @param count
	 */
	public DashboardCounts(String type, long count) {
		super();
		this.type = type;
		this.count = count;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}
	
	

}
