/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.sigar.impl;

import org.hyperic.sigar.SigarException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcentive.hackathon.botmonitor.BaseIntegrationTest;
import com.hcentive.hackathon.botmonitor.sigar.HostHealthManager;
import com.hcentive.hackathon.core.domain.HostHealth;

/**
 * @author Nitin.Gupta
 *
 */
public class HostHealthManagerImplIntegrationTest extends BaseIntegrationTest {

	@Autowired
	private HostHealthManager hostHealthManager;

	@Test
	public void testGetHealthInformation() throws SigarException {

		HostHealth information = hostHealthManager.getInformation();

		System.out.println(information);

	}

}
