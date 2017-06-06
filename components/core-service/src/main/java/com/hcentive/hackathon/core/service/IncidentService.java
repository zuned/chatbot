/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.core.service;

import java.util.Date;
import java.util.List;

import com.hcentive.hackathon.core.domain.Incident;
import com.hcentive.hackathon.core.domain.Monitor;

/**
 * 
 * @author Nitin.Gupta
 *
 */
public interface IncidentService {

	public enum INCIDENT_TYPE {
		Server, Application
	}

	public Incident reportIncident(Incident incident);

	public List<Incident> getIncidentByIncidentType(INCIDENT_TYPE incidentType);

	public List<Incident> getIncidentAllIncidents();

	/**
	 * 
	 */
	public long getTotalIncidentCounts();

	/**
	 * @param identifier
	 * @return
	 */
	public Incident getIncidentByExternalIdentifier(String identifier);

	/**
	 * @param identifier
	 * @return
	 */
	public List<Incident> getAllIncidentByMonitorIdentifier(String identifier);

	/**
	 * @param identifier
	 * @return
	 */
	public List<Incident> getAllIncidentByMonitoredAppIdentifier(String identifier);
	
	/**
	 * @param identifier
	 * @return
	 */
	public List<Incident> getAllIncidentByMonitoredAppName(String appName);
	

	public List<Incident> findIncidents(Monitor monitor, Date from, Date to);

}
