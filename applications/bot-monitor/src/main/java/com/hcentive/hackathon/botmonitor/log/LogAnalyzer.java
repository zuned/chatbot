/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.log;

import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.domain.MonitoredApplication;

/**
 * @author Nitin.Gupta
 *
 */
public interface LogAnalyzer {

	public void analyzeLog(Monitor monitor, MonitoredApplication application);

}
