/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp.messageprocessor;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

/**
 * Handles incoming message and does custom processing of the message.
 * 
 * @author Nitin.Gupta
 *
 */
public interface MesssageProcessor {

	public void processMessage(Chat chat, Message message) throws XMPPException;
}
