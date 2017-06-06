/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcentive.hackathon.core.domain.Incident;
import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.domain.MonitoredApplication;
import com.hcentive.hackathon.core.search.model.WidgetContainer;
import com.hcentive.hackathon.core.search.model.WidgetContainer.StatCriteria;
import com.hcentive.hackathon.core.search.model.WidgetContainer.Widget;
import com.hcentive.hackathon.core.service.IncidentService;
import com.hcentive.hackathon.core.service.MonitorService;
import com.hcentive.hackathon.core.service.WidgetService;
import com.hcentive.hackathon.user.model.DashboardCounts;

/**
 * @author Zuned.Ahmed
 *
 */
@Component
public class UserWebServiceImpl implements UserWebService {
	
	public static final String APPLICATION = "Application";

	public static final String INCIDENT = "Incident";

	public static final String MONITOR = "Monitor";

	@Autowired
	private MonitorService monitorService;
	
	@Autowired
	private IncidentService incidentService;
	
	@Autowired
	private WidgetService widgetService;
	
	/* (non-Javadoc)
	 * @see com.hcentive.hackathon.user.service.UserWebService#getStatCount()
	 */
	public List<DashboardCounts> getStatCount() {
		List<DashboardCounts> counts = new ArrayList<>(); 
		WidgetContainer widgetContainer = widgetService.getStatistic();
		for(StatCriteria searchCriteria : WidgetContainer.StatCriteria.values()){
			DashboardCounts dashboardCounts = new DashboardCounts(searchCriteria.name(),widgetContainer.getStatistic().get(searchCriteria));
			counts.add(dashboardCounts);	
		}
		return counts;
	}
	
	/* (non-Javadoc)
	 * @see com.hcentive.hackathon.user.service.UserWebService#getEntityListByType(java.lang.String)
	 */
	public List getEntityListByType(String type) {
		if(type.equals(MONITOR)){
			return monitorService.getAllMonitor();
		}else if(type.equals(INCIDENT)){
			return incidentService.getIncidentAllIncidents();
		}else if(type.equals(APPLICATION)){
			return monitorService.getAllMonitoredApplication();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hcentive.hackathon.user.service.UserWebService#getWidgetByType(java.lang.String)
	 */
	public WidgetContainer getWidgetByType(String widgetType) {
		Widget widget = WidgetContainer.Widget.valueOf(widgetType);
		return widgetService.search(widget);
	}
	
	/* (non-Javadoc)
	 * @see com.hcentive.hackathon.user.service.UserWebService#getEntityTypeDetailViewByIdentifier(java.lang.String, java.lang.String)
	 */
	public Object getEntityTypeDetailViewByIdentifier(String type, String identifier) {
		if(type.equals(MONITOR)){
			return monitorService.getMonitorByExternalIdentifier(identifier);
		}else if(type.equals(INCIDENT)){
			return incidentService.getIncidentByExternalIdentifier(identifier);
		}else if(type.equals(APPLICATION)){
			return monitorService.getMonitoredApplicationByExternalIdentifier(identifier);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hcentive.hackathon.user.service.UserWebService#getIncidentListByEntityTypeAndIndentifier(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Incident> getIncidentListByEntityTypeAndIndentifier(String type, String identifier) {
		if(type.equals(MONITOR)){
			return incidentService.getAllIncidentByMonitorIdentifier(identifier);
		}else if(type.equals(INCIDENT)){
			Incident incident = incidentService.getIncidentByExternalIdentifier(identifier);
			List<Incident> list = new ArrayList<>();
			list.add(incident);
			return list;
		}else if(type.equals(APPLICATION)){
			return incidentService.getAllIncidentByMonitoredAppName(identifier);
		}
		return null;
	}
}
