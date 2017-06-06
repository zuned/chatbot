/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.core.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.hcentive.hackathon.core.dao.MorphiaDataStore;
import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.domain.MonitoredApplication;
import com.hcentive.hackathon.core.service.MonitorService;

/**
 * @author Zuned.Ahmed
 *
 */
@Component("monitorService")
public class MonitorServiceImpl implements MonitorService {

	@Autowired
	private MorphiaDataStore morphiaDataStore;

	@Autowired
	@Qualifier("coreDozerMapper")
	private DozerBeanMapper dozerMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.core.service.MonitorService#registerMonitor(com.
	 * hcentive.hackathon.core.domain.Monitor)
	 */
	@Override
	public Monitor registerMonitor(Monitor monitor) {

		if (StringUtils.isEmpty(monitor.getName())) {
			throw new IllegalArgumentException("Monitor Name cannot be null or empty");
		}

		Monitor existingMonitor = getMonitorByName(monitor.getName());

		if (existingMonitor != null) {
			dozerMapper.map(monitor, existingMonitor);
			updateMonitor(existingMonitor);
		} else {
			existingMonitor = monitor;
			persistDependency(monitor);
			morphiaDataStore.save(monitor);
		}

		return existingMonitor;
	}

	/**
	 * @param monitor
	 * @param existingMonitor
	 */
	private void persistDependency(Monitor monitor) {
		if(monitor.getMonitoredApplications()!=null){
			for (MonitoredApplication application : monitor.getMonitoredApplications()) {
				morphiaDataStore.save(application);
			}
		}

		if (monitor.getHostHealth() != null) {
			morphiaDataStore.save(monitor.getHostHealth());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.core.service.MonitorService#updateMonitor(com.
	 * hcentive.hackathon.core.domain.Monitor)
	 */
	@Override
	public void updateMonitor(Monitor monitor) {

		persistDependency(monitor);
		morphiaDataStore.merge(monitor);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.core.service.MonitorService#activateMonitor(com.
	 * hcentive.hackathon.core.domain.Monitor)
	 */
	@Override
	public void activateMonitor(Monitor monitor) {
		monitor.setStatus(true);
		morphiaDataStore.merge(monitor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.core.service.MonitorService#deactivateMonitor(com.
	 * hcentive.hackathon.core.domain.Monitor)
	 */
	@Override
	public void deactivateMonitor(Monitor monitor) {
		monitor.setStatus(false);
		morphiaDataStore.merge(monitor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.core.service.MonitorService#getAllActiveMonitor()
	 */
	@Override
	public List<Monitor> getAllActiveMonitor() {
		Query<Monitor> list = morphiaDataStore.find(Monitor.class, "status", Boolean.TRUE);
		if (list != null) {
			return list.asList();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.core.service.MonitorService#getAllMonitor()
	 */
	@Override
	public List<Monitor> getAllMonitor() {
		Query<Monitor> list = morphiaDataStore.find(Monitor.class);
		if (list != null) {
			return list.asList();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.core.service.MonitorService#getMonitorByIPAddress(
	 * java.lang.String)
	 */
	@Override
	public Monitor getMonitorByIPAddress(String ipAddress) {
		Query<Monitor> monitor = morphiaDataStore.find(Monitor.class, "hostIpAddress", ipAddress);
		if (monitor != null) {
			return monitor.get();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.core.service.MonitorService#getMonitorByIPAddress(
	 * java.lang.String)
	 */
	@Override
	public Monitor getMonitorByName(String name) {
		Query<Monitor> query = morphiaDataStore.find(Monitor.class);
		query.field("monitor_name").equal(Pattern.compile(name, Pattern.CASE_INSENSITIVE));
		return query.get();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.core.service.MonitorService#getTotalMonitorCounts(
	 * )
	 */
	@Override
	public long getTotalMonitorCounts() {
		return morphiaDataStore.getCount(Monitor.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.core.service.MonitorService#
	 * getTotalApplicationCounts()
	 */
	@Override
	public long getTotalApplicationCounts() {
		List<MonitoredApplication> monitorApp = morphiaDataStore.find(MonitoredApplication.class).asList();
		Set<String> appNames = new HashSet<>();
		if(!CollectionUtils.isEmpty(monitorApp)){
			monitorApp.stream().forEach(mApp->{
				appNames.add(mApp.getAppName());
			});
		}
		return appNames.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.core.service.MonitorService#
	 * getAllMontioredApplicant()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MonitoredApplication> getAllMonitoredApplication() {
		Query<MonitoredApplication> monitoredApplication = morphiaDataStore.find(MonitoredApplication.class);
		List<MonitoredApplication> distinctMonitors = new ArrayList<>();
		if (monitoredApplication != null) {
			List<MonitoredApplication> monitors = monitoredApplication.asList();
			Set<String> appName = new HashSet<>();
		
			if(!CollectionUtils.isEmpty(monitors)){
				monitors.forEach(ma->{
					if(appName.add(ma.getAppName())){
						distinctMonitors.add(ma);
					}
				});
			}
		}
		return distinctMonitors;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.core.service.MonitorService#
	 * getMonitoredApplicationByExternalIdentifier(java.lang.String)
	 */
	@Override
	public MonitoredApplication getMonitoredApplicationByExternalIdentifier(String identifier) {
		Query<MonitoredApplication> monitoredApplication = morphiaDataStore.find(MonitoredApplication.class,
				"externalIdentifier", identifier);
		if (monitoredApplication != null) {
			return monitoredApplication.get();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.core.service.MonitorService#
	 * getMonitorByExternalIdentifier(java.lang.String)
	 */
	@Override
	public Monitor getMonitorByExternalIdentifier(String identifier) {
		Query<Monitor> monitor = morphiaDataStore.find(Monitor.class, "externalIdentifier", identifier);
		if (monitor != null) {
			return monitor.get();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hcentive.hackathon.core.service.MonitorService#updateMonitoredApplication(com.hcentive.hackathon.core.domain.MonitoredApplication)
	 */
	@Override
	public void updateMonitoredApplication(MonitoredApplication monitoredApp) {
		morphiaDataStore.merge(monitoredApp);
	}
	
}
