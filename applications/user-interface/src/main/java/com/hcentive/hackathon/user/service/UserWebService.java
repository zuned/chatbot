/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.user.service;

import java.util.List;

import com.hcentive.hackathon.core.domain.Incident;
import com.hcentive.hackathon.core.search.model.WidgetContainer;
import com.hcentive.hackathon.user.model.DashboardCounts;

/** 
 * @author Zuned.Ahmed
 *
 */
public interface UserWebService {

	/**
	 * @return
	 */
	List<DashboardCounts> getStatCount();

	/**
	 * @param type
	 * @return
	 */
	List getEntityListByType(String type);

	/**
	 * @param widgetType
	 * @return
	 */
	WidgetContainer getWidgetByType(String widgetType);


	/**
	 * @param type
	 * @param identifier
	 * @return
	 */
	List<Incident> getIncidentListByEntityTypeAndIndentifier(String type, String identifier);

	/**
	 * @param type
	 * @param identifier
	 * @return
	 */
	Object getEntityTypeDetailViewByIdentifier(String type, String identifier);

}
