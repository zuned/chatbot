/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp.roster.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcentive.hackathon.botmonitor.xmpp.connection.ConnectionManager;
import com.hcentive.hackathon.botmonitor.xmpp.roster.RosterManager;

/**
 * @author Nitin.Gupta
 *
 */
@Component("rosterManager")
public class RosterManagerImpl implements RosterManager {

	@Autowired
	private ConnectionManager connectionManager;

	/**
	 * Making roster stateful as it will only ever be maintaied for a given user
	 * (i.e. monitor). Hence, it is ok.
	 *//*
		 * private Roster roster;
		 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.botmonitor.xmpp.roster.RosterManager#getRoster()
	 */
	@Override
	public Roster getRoster() throws XMPPException {

		XMPPConnection connection = connectionManager.getConnection();

		Roster roster = connection.getRoster();

		roster.addRosterListener(new RosterListener() {
			// Ignored events public void entriesAdded(Collection<String>
			// addresses) {}
			public void entriesDeleted(Collection<String> addresses) {
			}

			public void entriesUpdated(Collection<String> addresses) {
			}

			public void presenceChanged(Presence presence) {

				String user = presence.getFrom();
				Presence bestPresence = roster.getPresence(user);

				System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
			}

			@Override
			public void entriesAdded(Collection<String> addresses) {
				// TODO Auto-generated method stub

			}
		});

		return roster;
	}

	@Override
	public List<String> getAvailableUsers(String groupName) throws XMPPException {

		Roster roster = getRoster();

		RosterGroup group = roster.getGroup(groupName);

		if (group == null) {
			return new ArrayList<String>(0);
		}

		Collection<RosterEntry> entries = group.getEntries();

		List<String> users = new ArrayList<>(0);

		for (RosterEntry rosterEntry : entries) {

			Presence presence = roster.getPresence(rosterEntry.getUser());

			if (presence.isAvailable()) {
				users.add(rosterEntry.getUser());
			}

		}

		return users;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.botmonitor.xmpp.roster.RosterManager#sendMessage(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMessage(String userName, String message) throws XMPPException {
		Chat chat = connectionManager.getConnection().getChatManager().createChat(userName, new MessageListener() {

			public void processMessage(Chat chat, Message incomingMessage) {
				// just log the message as of now.
				System.out.println("Received message: " + message);
			}
		});
		chat.sendMessage(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.botmonitor.xmpp.roster.RosterManager#sendMessage(
	 * java.util.List, java.lang.String)
	 */
	@Override
	public void sendMessage(List<String> users, String message) throws XMPPException {
		for (String user : users) {
			sendMessage(user, message);
		}
	}
}
