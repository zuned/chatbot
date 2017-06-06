/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp.messageprocessor.impl;

import org.apache.commons.lang.StringUtils;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcentive.hackathon.botmonitor.config.MonitorConfigurer;
import com.hcentive.hackathon.botmonitor.xmpp.messageprocessor.MesssageProcessor;
import com.hcentive.hackathon.core.domain.HostHealth;
import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.service.MonitorService;

/**
 * @author Nitin.Gupta
 *
 */
public abstract class KeywordBasedMessageProcessor implements MesssageProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeywordBasedMessageProcessor.class);

	private String keyword;

	@Autowired
	private MonitorConfigurer monitorConfigurer;

	@Autowired
	private MonitorService monitorService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.botmonitor.xmpp.messageprocessor.MesssageProcessor
	 * #processMessage(org.jivesoftware.smack.Chat,
	 * org.jivesoftware.smack.packet.Message)
	 */
	@Override
	public void processMessage(Chat chat, Message message) throws XMPPException {

		try {
			chat.sendMessage(getMessage());
		} catch (XMPPException e) {
			LOGGER.error("Exception occurred processing incoming message with keyword {}", getKeyword());
			throw e;
		}
	}

	/**
	 * @return
	 */
	protected abstract String getMessage();

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	protected boolean isKeywordMatch(String keyword) {
		if (StringUtils.equalsIgnoreCase(this.keyword, keyword)) {
			return true;
		}

		return false;
	}

	public Monitor getMonitor() {
		return monitorService.getMonitorByName(monitorConfigurer.getMonitorName());
	}
}
