/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.job;

import org.hyperic.sigar.SigarException;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcentive.hackathon.botmonitor.sigar.HostHealthManager;
import com.hcentive.hackathon.core.domain.HostHealth;
import com.hcentive.hackathon.core.domain.Monitor;

/**
 * @author Nitin.Gupta
 *
 */
public class HostHealthMonitorJob extends Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(HostHealthMonitorJob.class);

	private HostHealthManager hostHealthManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.botmonitor.job.Job#executeJob(org.quartz.
	 * JobExecutionContext)
	 */
	@Override
	public void executeJob(JobExecutionContext context) {
		try {
			HostHealth hostHealth = hostHealthManager.getInformation();

			LOGGER.info("Fetched health information for the monitor successfully.");

			Monitor monitor = getMonitor();

			if (monitor == null) {
				LOGGER.info("Could not update host health as no moniotr found.");
				return;
			}

			monitor.setHostHealth(hostHealth);

			getMonitorService().updateMonitor(monitor);

			LOGGER.info("Updated health information for the monitor {} successfully.", monitor.getName());

		} catch (SigarException e) {
			LOGGER.error("Exception occured while fetching host health information.", e);
		}
	}

	/**
	 * @param hostHealthManager
	 *            the hostHealthManager to set
	 */
	public void setHostHealthManager(HostHealthManager hostHealthManager) {
		this.hostHealthManager = hostHealthManager;
	}
}
