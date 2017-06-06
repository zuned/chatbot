/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp.connection.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hcentive.hackathon.botmonitor.xmpp.connection.ConnectionManager;

/**
 * @author Nitin.Gupta
 *
 */
@Component("connectionManager")
public class ConnectionManagerImpl implements ConnectionManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManagerImpl.class);

	private XMPPConnection connection;

	@Value("${xmpp.server.host}")
	private String xmppServerHost;

	@Value("${xmpp.server.username}")
	private String xmppServerUsername;

	@Value("${xmpp.server.password}")
	private String xmppServerPassword;

	@Autowired
	private ChatManagerListener chatManagerListener;

	@PostConstruct
	public void init() {
		ConnectionConfiguration config = new ConnectionConfiguration(xmppServerHost, 5222);
		connection = new XMPPConnection(config);
		try {
			connection.getChatManager().addChatListener(chatManagerListener);
			connection.connect();

			LOGGER.debug("Connecting to XMPP server for user {}");

			connection.login(xmppServerUsername, xmppServerPassword);
		} catch (XMPPException e) {
			LOGGER.error("Could not connect with or login to XMPP Server.", e);
		}
	}

	@PreDestroy
	public void cleanup() {
		if (connection == null) {
			return;
		}

		if (connection.isConnected()) {
			connection.disconnect();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.botmonitor.xmpp.ConnectionManager#getConnection()
	 */
	@Override
	public XMPPConnection getConnection() throws XMPPException {

		if (connection == null) {
			init();
		}

		if (!connection.isConnected()) {
			connection.connect();
		}

		return connection;
	}
}
