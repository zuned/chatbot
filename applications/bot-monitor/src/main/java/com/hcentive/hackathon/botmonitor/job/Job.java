/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.hcentive.hackathon.botmonitor.config.MonitorConfigurer;
import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.service.MonitorService;

/**
 * @author Nitin.Gupta
 * 
 */
public abstract class Job extends QuartzJobBean {

	private Logger logger = LoggerFactory.getLogger(Job.class);

	private MonitorConfigurer monitorConfigurer;

	private MonitorService monitorService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org
	 * .quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		logger.debug("Execution of {} job begins.", context.getJobDetail().getFullName());

		try {
			executeJob(context);
		} catch (Exception e) {
			logger.error("Execution of {} job resulted in exception.", context.getJobDetail().getFullName(), e);
			throw new JobExecutionException(e);

		} finally {
			logger.debug("Execution of {} job ends.", context.getJobDetail().getFullName());

		}
	}

	/**
	 * @return the monitorConfigurer
	 */
	public MonitorConfigurer getMonitorConfigurer() {
		return monitorConfigurer;
	}

	/**
	 * @param monitorConfigurer
	 *            the monitorConfigurer to set
	 */
	public void setMonitorConfigurer(MonitorConfigurer monitorConfigurer) {
		this.monitorConfigurer = monitorConfigurer;
	}

	/**
	 * @param monitorService
	 *            the monitorService to set
	 */
	public void setMonitorService(MonitorService monitorService) {
		this.monitorService = monitorService;
	}

	public Monitor getMonitor() {
		return monitorService.getMonitorByName(monitorConfigurer.getMonitorName());
	}

	/**
	 * @return the monitorService
	 */
	public MonitorService getMonitorService() {
		return monitorService;
	}

	public abstract void executeJob(JobExecutionContext context);

}