/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.core.service;

import java.util.List;

import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.domain.MonitoredApplication;

/**
 * @author Zuned.Ahmed
 *
 */
public interface MonitorService {
	/**
	 * Register Monitor
	 * @param monitor
	 */
	public Monitor registerMonitor(Monitor monitor) ;
	
	/**
	 * Update monitor
	 * @param monitor
	 */
	public void updateMonitor(Monitor monitor) ;
	
	/**
	 * Activate Monitor
	 * @param monitor
	 */
	public void activateMonitor(Monitor monitor);
	/**
	 * Deactivate Monitor
	 * @param monitor
	 */
	public void deactivateMonitor(Monitor monitor);
	/**
	 *Get List Of Active Monitors 
	 * @return
	 */
	public List<Monitor> getAllActiveMonitor();
	/**
	 * Get List Of  Monitors
	 * @return
	 */
	public List<Monitor> getAllMonitor();

	/**
	 * @param string
	 * @return
	 */
	public Monitor getMonitorByIPAddress(String ipAddress);

	/**
	 * @param name
	 * @return
	 */
	Monitor getMonitorByName(String name);

	/**
	 * @return
	 */
	public long getTotalMonitorCounts();

	/**
	 * @return
	 */
	public long getTotalApplicationCounts();

	/**
	 * @return
	 */
	public List<MonitoredApplication> getAllMonitoredApplication();

	/**
	 * @param identifier
	 * @return
	 */
	public MonitoredApplication getMonitoredApplicationByExternalIdentifier(String identifier);

	/**
	 * @param identifier
	 * @return
	 */
	public Monitor getMonitorByExternalIdentifier(String identifier);

	/**
	 * @param monitoredApp
	 */
	public void updateMonitoredApplication(MonitoredApplication monitoredApp);

}
