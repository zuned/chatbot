/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.user.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hcentive.hackathon.core.domain.Incident;
import com.hcentive.hackathon.core.domain.Incident.IncidentStatus;
import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.domain.MonitoredApplication;
import com.hcentive.hackathon.core.service.IncidentService.INCIDENT_TYPE;
import com.hcentive.hackathon.user.model.DashboardCounts;

/**
 * @author Zuned.Ahmed
 *
 */
public class TestDataUtils {
	public static void  getCountsTest(List<DashboardCounts> counts) {
		DashboardCounts dashboardCounts = new DashboardCounts("Monitor",33);
		counts.add(dashboardCounts);
		dashboardCounts = new DashboardCounts("Application",45);
		counts.add(dashboardCounts);
		dashboardCounts = new DashboardCounts("Incident",60);
		counts.add(dashboardCounts);
	}
	
	public static List getEntityListByTypeTest(String type) {
		if(type.equals(Monitor.class.getName())){
			return prepareMonitorList();
		}else if(type.equals(Incident.class.getName())){
			return prepareIncidentList();
		}else if(type.equals(MonitoredApplication.class.getName())){
			return prepareMonitoredApplicationList();
		}
		return null;
	}
	
	private static List prepareMonitorList() {
		List<Monitor> objects = new ArrayList<>();
		Monitor monitor = new Monitor();
		for(int i=0;i<10;i++){
			monitor.setName("anubhav"+i);
			monitor.setHostIpAddress("address"+i);
			monitor.setStatus(true);
			monitor.setHostName("Kapoor"+i);
			objects.add(monitor);
		}
		return objects;
	}
	
	private static List prepareMonitoredApplicationList() {
		List<MonitoredApplication> objects = new ArrayList<>();
		MonitoredApplication monitor = new MonitoredApplication();
		for(int i=0;i<10;i++){
			monitor.setAppName("anubhav"+i);
			monitor.setLogFileName("address"+i);
			monitor.setAddedOn(new Date());
			monitor.setLogFilePath("Kapoor"+i);
			objects.add(monitor);
		}
		return objects;
	}

	
	private static List prepareIncidentList() {
		List<Incident> objects = new ArrayList<>();
		Incident monitor = new Incident();
		for(int i=0;i<3;i++){
			monitor.setStatus(IncidentStatus.Open);
			monitor.setIncidentType(INCIDENT_TYPE.Application);
			monitor.setIncidentReportTime(new Date());
			monitor.setIncidentResolvedTime(new Date());
			objects.add(monitor);
		}
		
		for(int i=3;i<6;i++){
			monitor.setStatus(IncidentStatus.In_Progress);
			monitor.setIncidentType(INCIDENT_TYPE.Application);
			monitor.setIncidentReportTime(new Date());
			monitor.setIncidentResolvedTime(new Date());
			objects.add(monitor);
		}
		
		for(int i=6;i<9;i++){
			monitor.setStatus(IncidentStatus.Close);
			monitor.setIncidentType(INCIDENT_TYPE.Application);
			monitor.setIncidentReportTime(new Date());
			monitor.setIncidentResolvedTime(new Date());
			objects.add(monitor);
		}
		return objects;
	}

}
