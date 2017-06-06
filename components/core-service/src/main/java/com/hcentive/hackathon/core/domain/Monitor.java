/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.core.domain;

import java.util.HashSet;
import java.util.Set;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

/**
 * @author Zuned.Ahmed
 *
 */
@Entity("core_monitor")
public class Monitor extends BaseEntity {

	@Property("monitor_name")
	private String name;

	@Property("host_name")
	private String hostName;

	@Property("host_ip_address")
	private String hostIpAddress;

	@Property("status")
	private boolean status;

	@Reference(ignoreMissing = true)
	private Set<MonitoredApplication> monitoredApplications = new HashSet<MonitoredApplication>(0);

	@Reference(ignoreMissing = true)
	private HostHealth hostHealth;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the hostIpAddress
	 */
	public String getHostIpAddress() {
		return hostIpAddress;
	}

	/**
	 * @param hostIpAddress
	 *            the hostIpAddress to set
	 */
	public void setHostIpAddress(String hostIpAddress) {
		this.hostIpAddress = hostIpAddress;
	}

	/**
	 * @param monitoredApplications
	 *            the monitoredApplications to set
	 */
	public void setMonitoredApplications(Set<MonitoredApplication> monitoredApplications) {
		this.monitoredApplications = monitoredApplications;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the applications
	 */
	public Set<MonitoredApplication> getMonitoredApplications() {
		return monitoredApplications;
	}

	/**
	 * @return the hostHealth
	 */
	public HostHealth getHostHealth() {
		return hostHealth;
	}

	/**
	 * @param hostHealth
	 *            the hostHealth to set
	 */
	public void setHostHealth(HostHealth hostHealth) {
		this.hostHealth = hostHealth;
	}
}
