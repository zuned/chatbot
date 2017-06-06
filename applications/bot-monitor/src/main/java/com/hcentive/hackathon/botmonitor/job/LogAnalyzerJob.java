/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.job;

import org.quartz.JobExecutionContext;

import com.hcentive.hackathon.botmonitor.log.LogAnalyzer;
import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.domain.MonitoredApplication;

/**
 * @author Nitin.Gupta
 *
 */
public class LogAnalyzerJob extends Job {

	private LogAnalyzer logAnalyzer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.botmonitor.job.Job#executeJob(org.quartz.
	 * JobExecutionContext)
	 */
	@Override
	public void executeJob(JobExecutionContext context) {

		Monitor monitor = super.getMonitor();

		for (MonitoredApplication application : monitor.getMonitoredApplications()) {
			logAnalyzer.analyzeLog(monitor, application);
		}
	}

	/**
	 * @param logAnalyzer
	 *            the logAnalyzer to set
	 */
	public void setLogAnalyzer(LogAnalyzer logAnalyzer) {
		this.logAnalyzer = logAnalyzer;
	}
}
