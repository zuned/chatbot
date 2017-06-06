/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp.roster;

import java.util.List;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPException;

/**
 * @author Nitin.Gupta
 *
 */
public interface RosterManager {

	public Roster getRoster() throws XMPPException;

	List<String> getAvailableUsers(String groupName) throws XMPPException;

	public void sendMessage(String user, String message) throws XMPPException;

	public void sendMessage(List<String> users, String message) throws XMPPException;

}
