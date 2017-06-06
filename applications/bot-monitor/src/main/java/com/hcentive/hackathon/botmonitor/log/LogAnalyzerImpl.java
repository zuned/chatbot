/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.log;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcentive.hackathon.botmonitor.log.model.FileMetaData;
import com.hcentive.hackathon.core.domain.Incident;
import com.hcentive.hackathon.core.domain.Incident.IncidentStatus;
import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.domain.MonitoredApplication;
import com.hcentive.hackathon.core.service.IncidentService;
import com.hcentive.hackathon.core.service.IncidentService.INCIDENT_TYPE;
import com.hcentive.hackathon.core.service.MonitorService;

/**
 * @author Nitin.Gupta
 *
 */
@Component("logAnalyzer")
public class LogAnalyzerImpl implements LogAnalyzer {

	@Autowired
	private IncidentService incidentService;
	
	@Autowired
	private MonitorService monitorService;
	
	@Autowired
	FileReadManager fileReadManger;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.botmonitor.log.LogAnalyzer#analyzeLog(com.hcentive
	 * .hackathon.core.domain.Monitor,
	 * com.hcentive.hackathon.core.domain.MonitoredApplication)
	 */
	@Override
	public void analyzeLog(Monitor monitor, MonitoredApplication monitoredApp) {
		FileMetaData fileMetaData = new FileMetaData(monitoredApp.getLogFileName(), 
				monitoredApp.getLogFilePath(),monitoredApp.getNumberOfLinesRead() , 
				monitoredApp.getLastIncidentReportTime());
		fileReadManger.processFile(fileMetaData);
		if(fileMetaData.isErrorLogFound()){
			monitoredApp.setNumberOfLinesRead(fileMetaData.getNumberOfLineRead());
			monitoredApp.setLastIncidentReportTime(fileMetaData.getReportDateTime());
			monitorService.updateMonitoredApplication(monitoredApp);
			incidentService.reportIncident(getIncident(monitor,monitoredApp,fileMetaData));
		}
	}

	/**
	 * @param monitor
	 * @param application
	 * @param fileMetaData
	 * @return
	 */
	private Incident getIncident(Monitor monitor, MonitoredApplication application, FileMetaData fileMetaData) {
		Incident incident = new Incident();
			incident.setAddedBy("ChatBot");
			incident.setAddedOn(new Date());
			incident.setIncidentdata(fileMetaData.getErrorLogString());
			incident.setIncidentReportTime(fileMetaData.getReportDateTime());
			incident.setIncidentSummary(fileMetaData.getLogSummary());
			incident.setIncidentType(INCIDENT_TYPE.Application);
			incident.setMonitor(monitor);
			incident.setMonitoredApplication(application);
			incident.setStatus(IncidentStatus.Open);
		return incident;
	}

}
