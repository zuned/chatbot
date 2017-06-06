/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp.messageprocessor;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

/**
 * Resolves which {@link MesssageProcessor} which will process the incoming XMPP
 * message to this monitor.
 * 
 * @author Nitin.Gupta
 *
 */
public interface MessageProcessorResolver {

	public MesssageProcessor resolve(Chat chat, Message message) throws XMPPException;
}
