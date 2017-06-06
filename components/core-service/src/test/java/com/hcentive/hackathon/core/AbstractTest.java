/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcentive.hackathon.core.domain.Incident;
import com.hcentive.hackathon.core.domain.Incident.IncidentStatus;
import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.domain.MonitoredApplication;
import com.hcentive.hackathon.core.service.MonitorService;
import com.hcentive.hackathon.core.service.IncidentService.INCIDENT_TYPE;

/**
 *@author Zuned.Ahmed
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*-applicationContext.xml" })
public class AbstractTest {
	
	@Autowired
	protected MonitorService monitorService ;
	


	/**
	 * @param serverStat
	 * @return
	 */
	protected Incident createIncident(INCIDENT_TYPE serverStat, Monitor monitor , MonitoredApplication mApp) {
		Incident incident = new Incident();
			incident.setIncidentType(serverStat);
			incident.setAddedBy("JUnit");
			incident.setAddedOn(new Date());
			incident.setIncidentdata("The incident reported by complete data summury is reported by Junit.");
			incident.setIncidentSummary("The incident reported by junit");
			incident.setIncidentReportTime(new Date());
			incident.setIncidentResolvedTime(new Date());
			incident.setMonitoredApplication(mApp);
			incident.setMonitor(monitor);
			incident.setStatus(IncidentStatus.Open);
		return incident;
	}

	/**
	 * @param string
	 * @return
	 */
	protected Monitor createMonitor(String serverIpAddress , String serverName) {
		Monitor monitor = new Monitor();
		monitor.setHostIpAddress(serverIpAddress);
		monitor.setHostName(serverName);
		monitor.setName(serverName);
		monitor.setStatus(true);
		monitor.setAddedBy("JUnit");
		monitor.setAddedOn(new Date());
		monitor.setMonitoredApplications(getMonitoredApplication());
		
		return monitor;
	}

	/**
	 * @return
	 */
	protected Set<MonitoredApplication> getMonitoredApplication() {
		Set<MonitoredApplication> list = new HashSet<MonitoredApplication>();
		MonitoredApplication broker= new MonitoredApplication();
		broker.setAppName("broker");
		broker.setLogFileName("broker-portal.log");
		broker.setLogFilePath("/usr/local/phix/applications/app_logs/logs");
		list.add(broker);
		MonitoredApplication employer = new MonitoredApplication();
			employer.setAppName("employer");
			employer.setLogFileName("employer-portal.log");
			employer.setLogFilePath("/usr/local/phix/applications/app_logs/logs");
		list.add(employer);
		MonitoredApplication employee= new MonitoredApplication();
		employee.setAppName("employee");
		employee.setLogFileName("employee-portal.log");
		employee.setLogFilePath("/usr/local/phix/applications/app_logs/logs");
		list.add(employee);
		MonitoredApplication individual= new MonitoredApplication();
		individual.setAppName("individual");
		individual.setLogFileName("individual-portal.log");
		individual.setLogFilePath("/usr/local/phix/applications/app_logs/logs");
		list.add(individual);
		MonitoredApplication individualDE= new MonitoredApplication();
		individualDE.setAppName("individual");
		individualDE.setLogFileName("cmd-de-portal.log");
		individualDE.setLogFilePath("/usr/local/phix/applications/app_logs/logs");
		list.add(individualDE);

		return list;
	}

	/**
	 * @param monitor
	 */
	public void reportIncidentsOnMonitor(Monitor monitor) {
		for(int i =0 ; i < 50;i++){
			
		}
		
	}
//	protected Incident createIncident(Monitor monitor){}
}
