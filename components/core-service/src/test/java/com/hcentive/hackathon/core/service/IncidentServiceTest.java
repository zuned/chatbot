/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.core.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcentive.hackathon.core.AbstractTest;
import com.hcentive.hackathon.core.dao.MorphiaDataStore;
import com.hcentive.hackathon.core.domain.Incident;
import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.service.IncidentService.INCIDENT_TYPE;

import junit.framework.Assert;

/**
 * @author Zuned.Ahmed
 */
public class IncidentServiceTest extends AbstractTest {

	@Autowired
	private IncidentService incidentService;

	@Autowired
	private MonitorService monitorService;
	
	@Autowired
	private MorphiaDataStore morphiaDataStore;
	
	@Test
	public void dropSchemaTest() {
		morphiaDataStore.getDB().dropDatabase();
	}

	@Test
	public void reportIncidentTest() {
		for (int i = 124; i <= 133; i++) {
			String serverIp = "10.10.0." + i;
			Monitor monitor = this.monitorService.getMonitorByIPAddress(serverIp);//
			monitor.getMonitoredApplications().forEach(mApp -> {
				Incident key = incidentService
						.reportIncident(super.createIncident(INCIDENT_TYPE.Server, monitor, mApp));
				 key = incidentService
						.reportIncident(super.createIncident(INCIDENT_TYPE.Application, monitor, mApp));
				Assert.assertNotNull(key);

			});
		}

	}

	@Test
	public void testCreateIncident() {

		List<Monitor> monitors = monitorService.getAllMonitor();

		for (Monitor monitor : monitors) {

			// String serverIp = "10.10.0.1";
			monitor.getMonitoredApplications().forEach(mApp -> {
				Incident key = incidentService
						.reportIncident(super.createIncident(INCIDENT_TYPE.Server, monitor, mApp));
				Assert.assertNotNull(key);
			});
		}
	}
	
	@Test
	public void testWebService(){
		Incident incident = morphiaDataStore.createQuery(Incident.class).field("external_identifier").equal("192441d3-05b8-4a96-86e0-85836590bd9c").get();
		List<Incident> monitorWiseIncidentList = incidentService.getAllIncidentByMonitorIdentifier(incident.getMonitor().getExternalIdentifier());
		List<Incident> appWiseIncidentList = incidentService.getAllIncidentByMonitoredAppIdentifier(incident.getMonitoredApplication().getExternalIdentifier()
				);
	}
	
	@Test
	public void incidentByAppNameTest(){
		List<Incident>  incendents = incidentService.getAllIncidentByMonitoredAppName("broker");
		
	}
}
