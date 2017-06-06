/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp.connection;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * @author Nitin.Gupta
 *
 */
public interface ConnectionManager {

	XMPPConnection getConnection() throws XMPPException;

}
