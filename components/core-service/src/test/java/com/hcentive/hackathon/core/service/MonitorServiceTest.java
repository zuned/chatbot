/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.core.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcentive.hackathon.core.AbstractTest;
import com.hcentive.hackathon.core.domain.Monitor;

/**
 * @author Zuned.Ahmed
 *
 */
public class MonitorServiceTest extends AbstractTest {

	/**
	 * Register Monitor
	 * @param monitor
	 */
	@Test
	public void registerMonitorTest() {
		Monitor monitor = monitorService.registerMonitor(super.createMonitor("10.10.0.124" ,"dev24"));
//		super.reportIncidentsOnMonitor(monitor);
		monitor = monitorService.registerMonitor(super.createMonitor("10.10.0.125", "dev25"));
//		super.reportIncidentsOnMonitor(monitor);
		monitor = monitorService.registerMonitor(super.createMonitor("10.10.0.126", "dev26"));
//		super.reportIncidentsOnMonitor(monitor);
		monitor = monitorService.registerMonitor(super.createMonitor("10.10.0.127", "dev27"));
//		super.reportIncidentsOnMonitor(monitor);
		monitor = monitorService.registerMonitor(super.createMonitor("10.10.0.128", "dev28"));
//		super.reportIncidentsOnMonitor(monitor);
		monitor = monitorService.registerMonitor(super.createMonitor("10.10.0.129" ,"dev29"));
//		super.reportIncidentsOnMonitor(monitor);
		monitor = monitorService.registerMonitor(super.createMonitor("10.10.0.130", "dev30"));
//		super.reportIncidentsOnMonitor(monitor);
		monitor = monitorService.registerMonitor(super.createMonitor("10.10.0.131", "dev31"));
//		super.reportIncidentsOnMonitor(monitor);
		monitor = monitorService.registerMonitor(super.createMonitor("10.10.0.132", "dev32"));
//		super.reportIncidentsOnMonitor(monitor);
		monitor = monitorService.registerMonitor(super.createMonitor("10.10.0.133", "dev33"));
//		super.reportIncidentsOnMonitor(monitor);
	}
	
	/**
	 * Update monitor
	 * @param monitor
	 */
	@Test
	public void updateMonitorTest() {
	}
	
	/**
	 * Activate Monitor
	 * @param monitor
	 */
	@Test
	public void activateMonitorTest(){
		
	}
	/**
	 * Deactivate Monitor
	 * @param monitor
	 */
	@Test
	public void deactivateMonitorTest(){

	}
	/**
	 *Get List Of Active Monitors 
	 * @return
	 */
	@Test
	public void getAllActiveMonitorTest() {
		monitorService.getAllActiveMonitor();
	}
	/**
	 * Get List Of  Monitors
	 * @return
	 */
	@Test
	public void getAllMonitorTest(){
		monitorService.getAllMonitor();
	}
	
	@Test
	public void testFindMonitorByName(){
		Monitor monitor = monitorService.getMonitorByName("Monitor 1");
		System.out.println(monitor);
	}
	
	@Test
	public void testUpdateMonitor(){
		Monitor monitor = monitorService.getMonitorByName("Monitor 1");
		monitor.setStatus(true);
		
		monitorService.updateMonitor(monitor);
		
		Monitor _monitor = monitorService.getMonitorByName("Monitor 1");
		
		System.out.println(_monitor);
	}

	@Test
	public void getTotalApplicationCountTest() {
		monitorService.getTotalApplicationCounts();
	}
	
}
