/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.hcentive.hackathon.core.domain.Incident;
import com.hcentive.hackathon.core.domain.Incident.IncidentStatus;
import com.hcentive.hackathon.core.search.model.WidgetContainer;
import com.hcentive.hackathon.core.search.model.WidgetContainer.StatCriteria;
import com.hcentive.hackathon.core.search.model.WidgetContainer.Widget;
import com.hcentive.hackathon.core.search.model.WidgetResult;
import com.hcentive.hackathon.core.service.IncidentService;
import com.hcentive.hackathon.core.service.MonitorService;
import com.hcentive.hackathon.core.service.WidgetService;

/**
 * @author Zuned.Ahmed
 *
 */
@Component
public class WidgetServiceImpl implements WidgetService {
	
	@Autowired
	private IncidentService incidentService;
	
	@Autowired
	private MonitorService monitorService;

	/* (non-Javadoc)
	 * @see com.hcentive.hackathon.core.service.WidgetService#search(com.hcentive.hackathon.core.search.model.WidgetContainer.Widget)
	 */
	@Override
	public WidgetContainer search(Widget widget) {
		WidgetContainer widgetContainer = new WidgetContainer();
		switch(widget){
			case  MONITOR_INCIDENT_COUNT :
				{
					widgetContainer.setSearchResults(getIncidentToStatusCount(Widget.MONITOR_INCIDENT_COUNT));
				}break;
			case  APPLICATION_INCIDENT_COUNT:
				{
					widgetContainer.setSearchResults(getIncidentToStatusCount(Widget.APPLICATION_INCIDENT_COUNT));
				}break;
			default :
				{
					widgetContainer.setWidgetErrorMessage("Widget is not defined.");
				}
		}
		return widgetContainer;
	}
	
	/* (non-Javadoc)
	 * @see com.hcentive.hackathon.core.service.WidgetService#getStatistic()
	 */
	@Override
	public WidgetContainer getStatistic() {
		WidgetContainer widgetContiner= new WidgetContainer();
		widgetContiner.getStatistic().put(StatCriteria.Incident, incidentService.getTotalIncidentCounts());
		widgetContiner.getStatistic().put(StatCriteria.Monitor, monitorService.getTotalMonitorCounts());
		widgetContiner.getStatistic().put(StatCriteria.Application, monitorService.getTotalApplicationCounts());
		return widgetContiner;
	}

	/**
	 * @param applicationIncidentCount
	 * @return
	 */
	private List<WidgetResult> getIncidentToStatusCount(Widget applicationIncidentCount) {
		List<Incident>  incidents = incidentService.getIncidentAllIncidents();
		if(!CollectionUtils.isEmpty(incidents)){
			Map<String, WidgetResult> identiferToWidget = new HashMap<>();
			incidents.stream().forEach(incident->{
				String key = null;
				switch(applicationIncidentCount){
					case  APPLICATION_INCIDENT_COUNT:key = incident.getMonitoredApplication()!=null?incident.getMonitoredApplication().getAppName():null; break;
					case MONITOR_INCIDENT_COUNT: key = incident.getMonitor()!=null?incident.getMonitor().getHostName():null;break;
				}
				if(key == null){
					return;
				}
				WidgetResult widget =  identiferToWidget.get(key);
				if(widget == null){
					widget =  new WidgetResult(key);
					widget.setIdentifier(incident.getMonitor().getExternalIdentifier());
					widget.setDisplayName(key);
					identiferToWidget.put(key, widget);
				}
				if(incident.getStatus() == null)incident.setStatus(IncidentStatus.Open);
				int currentCount = widget.getStatusWiseCount().get(incident.getStatus().name())==null? 0 :widget.getStatusWiseCount().get(incident.getStatus().name());
				widget.getStatusWiseCount().put(incident.getStatus().name(),currentCount+1 );
			});
			return new ArrayList<>(identiferToWidget.values());
			
		}
		return null;
	}
}
