/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp.roster.impl;

import java.util.Collection;
import java.util.List;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcentive.hackathon.botmonitor.BaseIntegrationTest;
import com.hcentive.hackathon.botmonitor.xmpp.roster.RosterManager;

/**
 * @author Nitin.Gupta
 *
 */

public class RoasterManagerImplIntegrationTest extends BaseIntegrationTest {

	@Autowired
	private RosterManager rosterManager;

	@Test
	public void getRoster() throws XMPPException {
		Roster roster = rosterManager.getRoster();
		Presence presence;

		Collection<RosterEntry> entries = roster.getEntries();

		for (RosterEntry rosterEntry : entries) {

			Collection<RosterGroup> groups = rosterEntry.getGroups();

			for (RosterGroup rosterGroup : groups) {
				System.out.println(rosterEntry.getUser() + " is in " + rosterGroup.getName());
			}

			presence = roster.getPresence(rosterEntry.getUser());
			System.out.println(rosterEntry.getUser());
			System.out.println(presence.getType().name());
			System.out.println(presence.getStatus());
		}
	}
	
	@Test
	public void testGetAvailableUsers() throws XMPPException{
		List<String> availableUsers = rosterManager.getAvailableUsers("admins");
		
		for (String user : availableUsers) {
			System.out.println(user);
		}
	}

}
