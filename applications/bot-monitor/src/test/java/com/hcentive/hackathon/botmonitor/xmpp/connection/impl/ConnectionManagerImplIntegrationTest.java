/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp.connection.impl;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcentive.hackathon.botmonitor.BaseIntegrationTest;
import com.hcentive.hackathon.botmonitor.xmpp.connection.ConnectionManager;

/**
 * @author Nitin.Gupta
 *
 */
public class ConnectionManagerImplIntegrationTest extends BaseIntegrationTest {

	@Autowired
	ConnectionManager connectionManager;

	@Test
	public void getConnection() throws XMPPException {
		XMPPConnection connection = connectionManager.getConnection();

		System.out.println(connection);
	}
}
