/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcentive.hackathon.core.dao.MorphiaDataStore;
import com.hcentive.hackathon.core.domain.Incident;
import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.domain.MonitoredApplication;
import com.hcentive.hackathon.core.service.IncidentService;

/**
 * 
 * @author Nitin.Gupta
 *
 */
@Component("incidentService")
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private MorphiaDataStore morphiaDataStore;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.core.service.IncidentService#reportIncident(com.
	 * hcentive.hackathon.core.domain.Incident)
	 */
	@Override
	public Incident reportIncident(Incident incident) {
		morphiaDataStore.save(incident);
		return incident;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.core.service.IncidentService#
	 * getIncidentByIncidentType(com.hcentive.hackathon.core.service.
	 * IncidentService.INCIDENT_TYPE)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Incident> getIncidentByIncidentType(INCIDENT_TYPE incidentType) {
		return (List<Incident>) morphiaDataStore.find(Incident.class, "incidentType", incidentType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.core.service.IncidentService#
	 * getIncidentAllIncidents()
	 */
	@Override
	public List<Incident> getIncidentAllIncidents() {
		Query<Incident> incidentList = this.morphiaDataStore.find(Incident.class).order("-id").limit(100);
		if (incidentList != null) {
			return incidentList.asList();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.core.service.IncidentService#
	 * getTotalIncidentCounts()
	 */
	@Override
	public long getTotalIncidentCounts() {
		return this.morphiaDataStore.getCount(Incident.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.core.service.IncidentService#
	 * getIncidentByExternalIdentifier(java.lang.String)
	 */
	@Override
	public Incident getIncidentByExternalIdentifier(String identifier) {
		Query<Incident> incident = morphiaDataStore.find(Incident.class, "externalIdentifier", identifier);
		if (incident != null) {
			return incident.get();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hcentive.hackathon.core.service.IncidentService#getAllIncidentByMonitoredAppIdentifier(java.lang.String)
	 */
	@Override
	public List<Incident> getAllIncidentByMonitoredAppIdentifier(String identifier) {
		Query<MonitoredApplication> monitorQuery=  morphiaDataStore.find(MonitoredApplication.class, "externalIdentifier", identifier);
		if(monitorQuery == null){
			return null;
		}
		return morphiaDataStore.createQuery(Incident.class).filter("monitoredApplication", monitorQuery.get()).asList();//, identifier).asList();
	}
	
	/* (non-Javadoc)
	 * @see com.hcentive.hackathon.core.service.IncidentService#getAllIncidentByMonitoredAppName(java.lang.String)
	 */
	@Override
	public List<Incident> getAllIncidentByMonitoredAppName(String appName) {
		Query<MonitoredApplication> monitorQuery=  morphiaDataStore.find(MonitoredApplication.class, "appName", appName);
		if(monitorQuery == null){
			return null;
		}
		Set<Incident> incidents = new HashSet<>();
		monitorQuery.asList().stream().forEach(mApp->{
			incidents.addAll(morphiaDataStore.createQuery(Incident.class).filter("monitoredApplication", mApp).asList());
		});
		
		return new ArrayList<Incident>(incidents);//, identifier).asList();
	}
	
	/* (non-Javadoc)
	 * @see com.hcentive.hackathon.core.service.IncidentService#getAllIncidentByMonitorIdentifier(java.lang.String)
	 */
	@Override
	public List<Incident> getAllIncidentByMonitorIdentifier(String identifier) {
		Query<Monitor> monitorQuery=  morphiaDataStore.find(Monitor.class, "externalIdentifier", identifier);
		if(monitorQuery == null){
			return null;
		}
		Monitor monitor = monitorQuery.get();
		return morphiaDataStore.createQuery(Incident.class).filter("monitor", monitor).asList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.core.service.IncidentService#findIncidents(com.
	 * hcentive.hackathon.core.domain.Monitor, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Incident> findIncidents(Monitor monitor, Date from, Date to) {

		List<Incident> incidents = morphiaDataStore.createQuery(Incident.class).filter("incidentReportTime >=", from)
				.filter("incidentReportTime < ", to).filter("monitor", monitor).asList();

		return incidents;
	}
}
