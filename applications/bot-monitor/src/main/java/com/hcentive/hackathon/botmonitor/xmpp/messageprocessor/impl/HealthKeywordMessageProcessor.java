/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp.messageprocessor.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hcentive.hackathon.core.domain.HostHealth;
import com.hcentive.hackathon.core.domain.Monitor;

/**
 * @author Nitin.Gupta
 *
 */
@Component("healthKeywordMessageProcessor")
public class HealthKeywordMessageProcessor extends KeywordBasedMessageProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(HealthKeywordMessageProcessor.class);

	@PostConstruct
	public void init() {
		this.setKeyword("health");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.botmonitor.xmpp.messageprocessor.impl.
	 * KeywordBasedMessageProcessor#getMessage()
	 */
	@Override
	protected String getMessage() {
		Monitor monitor = getMonitor();

		HostHealth hostHealth = monitor.getHostHealth();

		if(hostHealth == null){
			return "Information not found. Please check back later.";
		}
		return hostHealth.toString();
	}
}
