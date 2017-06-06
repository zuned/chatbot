/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.core.domain;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

/**
 * @author Zuned.Ahmed
 *
 */
@Entity("core_monitored_application")
public class MonitoredApplication extends BaseEntity {

	@Property("app_name")
	String appName;

	@Property("log_file_path")
	String logFilePath;

	@Property("log_file_name")
	String logFileName;

	@Property("log_pattern")
	String logPattern;

	@Property("number_of_lines_read")
	private long numberOfLinesRead;

	@Property("last_incident_report_time")
	private Date lastIncidentReportTime;

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName
	 *            the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @return the logFilePath
	 */
	public String getLogFilePath() {
		return logFilePath;
	}

	/**
	 * @param logFilePath
	 *            the logFilePath to set
	 */
	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}

	/**
	 * @return the logFileName
	 */
	public String getLogFileName() {
		return logFileName;
	}

	/**
	 * @param logFileName
	 *            the logFileName to set
	 */
	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appName == null) ? 0 : appName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonitoredApplication other = (MonitoredApplication) obj;
		if (appName == null) {
			if (other.appName != null)
				return false;
		} else if (!appName.equals(other.appName))
			return false;
		return true;
	}

	/**
	 * @return
	 */
	public Date getLastIncidentReportTime() {
		return this.lastIncidentReportTime;
	}

	/**
	 * @return
	 */
	public long getNumberOfLinesRead() {
		return this.numberOfLinesRead;
	}

	/**
	 * @param numberOfLinesRead the numberOfLinesRead to set
	 */
	public void setNumberOfLinesRead(long numberOfLinesRead) {
		this.numberOfLinesRead = numberOfLinesRead;
	}

	/**
	 * @param lastIncidentReportTime the lastIncidentReportTime to set
	 */
	public void setLastIncidentReportTime(Date lastIncidentReportTime) {
		this.lastIncidentReportTime = lastIncidentReportTime;
	}

	/**
	 * @return the logPattern
	 */
	public String getLogPattern() {
		return logPattern;
	}

	/**
	 * @param logPattern the logPattern to set
	 */
	public void setLogPattern(String logPattern) {
		this.logPattern = logPattern;
	}
	
	
}
