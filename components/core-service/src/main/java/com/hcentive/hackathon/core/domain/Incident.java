package com.hcentive.hackathon.core.domain;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import com.hcentive.hackathon.core.service.IncidentService.INCIDENT_TYPE;

/**
 * 
 * @author Nitin.Gupta
 *
 */
@Entity("core_incident")
public class Incident extends BaseEntity {
	
	public static enum IncidentStatus {Open, In_Progress , Close}
	
	@Property("incident_data")
	private String incidentdata;
	
	@Property("incident_summary")
	private String incidentSummary;
	
	@Property("incident_report_time")
	private Date incidentReportTime;
	
	@Property("incident_resolved_time")
	private Date incidentResolvedTime;
	
	@Property("incident_type")
	private INCIDENT_TYPE incidentType;
	
	@Property("status")
	private IncidentStatus status;
	
	@Reference
	private Monitor monitor;
	
	@Reference
	private MonitoredApplication monitoredApplication;

	/**
	 * @return the startDateTime
	 */
	public Date getIncidentReportTime() {
		return incidentReportTime;
	}

	/**
	 * @param startDateTime the startDateTime to set
	 */
	public void setIncidentReportTime(Date startDateTime) {
		this.incidentReportTime = startDateTime;
	}

	/**
	 * @return the endDateTime
	 */
	public Date getIncidentResolvedTime() {
		return incidentResolvedTime;
	}

	/**
	 * @param endDateTime the endDateTime to set
	 */
	public void setIncidentResolvedTime(Date endDateTime) {
		this.incidentResolvedTime = endDateTime;
	}

	/**
	 * @return the incidentType
	 */
	public INCIDENT_TYPE getIncidentType() {
		return incidentType;
	}

	/**
	 * @param incidentType the incidentType to set
	 */
	public void setIncidentType(INCIDENT_TYPE incidentType) {
		this.incidentType = incidentType;
	}

	/**
	 * @return the monitor
	 */
	public Monitor getMonitor() {
		return monitor;
	}

	/**
	 * @param monitor the monitor to set
	 */
	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	/**
	 * @return the incidentdata
	 */
	public String getIncidentdata() {
		return incidentdata;
	}

	/**
	 * @param incidentdata the incidentdata to set
	 */
	public void setIncidentdata(String incidentdata) {
		this.incidentdata = incidentdata;
	}

	/**
	 * @return the status
	 */
	public IncidentStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(IncidentStatus status) {
		this.status = status;
	}

	/**
	 * @return the monitoredApplication
	 */
	public MonitoredApplication getMonitoredApplication() {
		return monitoredApplication;
	}

	/**
	 * @param monitoredApplication the monitoredApplication to set
	 */
	public void setMonitoredApplication(MonitoredApplication monitoredApplication) {
		this.monitoredApplication = monitoredApplication;
	}

	/**
	 * @return the incidentSummary
	 */
	public String getIncidentSummary() {
		return incidentSummary;
	}

	/**
	 * @param incidentSummary the incidentSummary to set
	 */
	public void setIncidentSummary(String incidentSummary) {
		this.incidentSummary = incidentSummary;
	}
	
}
