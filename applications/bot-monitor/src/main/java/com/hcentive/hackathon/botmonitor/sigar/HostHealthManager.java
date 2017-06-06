/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.sigar;

import org.hyperic.sigar.SigarException;

import com.hcentive.hackathon.core.domain.HostHealth;

/**
 * @author Nitin.Gupta
 *
 */
public interface HostHealthManager {

	public HostHealth getInformation() throws SigarException;

}
