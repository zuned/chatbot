/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.user.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcentive.hackathon.core.domain.Incident;
import com.hcentive.hackathon.core.search.model.WidgetContainer;
import com.hcentive.hackathon.user.model.DashboardCounts;
import com.hcentive.hackathon.user.service.UserWebService;

/**
 * @author Anubhav.Kapoor
 *
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private UserWebService userWebService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getCounts")
	public List<DashboardCounts> getCounts() {
		return userWebService.getStatCount();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/widget/{type}")
	public WidgetContainer monitorVsIncidents(@PathVariable("type") String widgetType) {
		return userWebService.getWidgetByType(widgetType);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET, value = "/getEntityListByType/{type}")
	public List getEntityListByType(@PathVariable("type") String type) {
		return userWebService.getEntityListByType(type);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getEntityListByType/{type}/{identifier}")
	public List<Incident> getEntityListByTypeAndIdentifier(@PathVariable("type") String type, @PathVariable("identifier") String identifier) {
		return userWebService.getIncidentListByEntityTypeAndIndentifier(type , identifier); 
	}

	@RequestMapping(method = RequestMethod.GET, value = "/detail/{type}/{identifier}")
	public Object getEntityTypeDetailViewByIdentifier(@PathVariable("type")String type, @PathVariable("identifier")String identifier) {
		return userWebService.getEntityTypeDetailViewByIdentifier(type,identifier);
	}

}
