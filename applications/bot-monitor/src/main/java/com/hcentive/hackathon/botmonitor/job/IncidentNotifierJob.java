/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.job;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.jivesoftware.smack.XMPPException;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcentive.hackathon.botmonitor.xmpp.roster.RosterManager;
import com.hcentive.hackathon.core.domain.Incident;
import com.hcentive.hackathon.core.service.IncidentService;

/**
 * @author Nitin.Gupta
 *
 */
public class IncidentNotifierJob extends Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(IncidentNotifierJob.class);

	private RosterManager rosterManager;

	private IncidentService incidentService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.botmonitor.job.Job#executeJob(org.quartz.
	 * JobExecutionContext)
	 */
	@Override
	public void executeJob(JobExecutionContext context) {

		Date to = Calendar.getInstance().getTime();
		Date from = DateUtils.addMinutes(to, -1);
		// from = DateUtils.ceiling(from, Calendar.MINUTE);

		List<Incident> incidents = incidentService.findIncidents(getMonitor(), from, to);

		try {
			List<String> adminUsers = rosterManager.getAvailableUsers("admins");

			for (Incident incident : incidents) {
				rosterManager.sendMessage(adminUsers, getMesssage(incident));
			}

		} catch (XMPPException e) {
			LOGGER.error("Exception Occurred while sending incident message to users.", e);
		}
	}

	/**
	 * @param incident
	 * @return
	 */
	private String getMesssage(Incident incident) {

		StringBuilder sb = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		sb.append("** Incident Alert **").append(newLine);

		if (incident.getMonitoredApplication() != null) {
			sb.append(String.format("Application: %10s", incident.getMonitoredApplication().getAppName()))
					.append(newLine);
		}

		sb.append(incident.getIncidentSummary());

		return sb.toString();
	}

	/**
	 * @param rosterManager
	 *            the rosterManager to set
	 */
	public void setRosterManager(RosterManager rosterManager) {
		this.rosterManager = rosterManager;
	}

	/**
	 * @param incidentService
	 *            the incidentService to set
	 */
	public void setIncidentService(IncidentService incidentService) {
		this.incidentService = incidentService;
	}
}
