/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.botmonitor.log.model;

import java.util.Date;

/**
 * @author Zuned.Ahmed
 *
 */
public class FileMetaData {

	private final String fileName;
	private final String filePath;
	private final Date filelastReadDateTime;
	private long numberOfLineRead;
	private boolean isErrorLogFound;
	private String logSummary;
	private String errorLogString;
	private Date reportDateTime;
	
	/**
	 * @param fileName
	 * @param filePath
	 * @param numberOfLineRead
	 * @param fileCreatedDateTime
	 */
	public FileMetaData(String fileName, String filePath, long numberOfLineRead, Date filelastReadDateTime) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.numberOfLineRead = numberOfLineRead;
		this.filelastReadDateTime = filelastReadDateTime;
		this.isErrorLogFound = false;
	}
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the numberOfLineRead
	 */
	public long getNumberOfLineRead() {
		return numberOfLineRead;
	}

	/**
	 * @param numberOfLineRead the numberOfLineRead to set
	 */
	public void setNumberOfLineRead(long numberOfLineRead) {
		this.numberOfLineRead = numberOfLineRead;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @return the fileCreatedDateTime
	 */
	public Date getFilelastReadDateTime() {
		return filelastReadDateTime;
	}
	
	public String getAbsolutePath(){
		return this.getFilePath() + System.getProperty("file.separator") + this.getFileName();
	}

	/**
	 * @return the isErrorLogFound
	 */
	public boolean isErrorLogFound() {
		return isErrorLogFound;
	}

	/**
	 * @param isErrorLogFound the isErrorLogFound to set
	 */
	public void setErrorLogFound(boolean isErrorLogFound) {
		this.isErrorLogFound = isErrorLogFound;
	}

	/**
	 * @return the errorLogString
	 */
	public String getErrorLogString() {
		return errorLogString;
	}

	/**
	 * @param errorLogString the errorLogString to set
	 */
	public void setErrorLogString(String errorLogString) {
		this.errorLogString = errorLogString;
	}

	/**
	 * @return
	 */
	public Date getReportDateTime() {
		return this.reportDateTime;
	}

	public void setReportDateTime(Date reportDateTime) {
		this.reportDateTime = reportDateTime;
	}

	/**
	 * @return the logSummary
	 */
	public String getLogSummary() {
		return logSummary;
	}

	/**
	 * @param logSummary the logSummary to set
	 */
	public void setLogSummary(String logSummary) {
		this.logSummary = logSummary;
	}
	
	
}
