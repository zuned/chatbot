/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp.messageprocessor.impl;

import javax.annotation.Resource;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.springframework.stereotype.Component;

import com.hcentive.hackathon.botmonitor.xmpp.messageprocessor.MessageProcessorResolver;
import com.hcentive.hackathon.botmonitor.xmpp.messageprocessor.MesssageProcessor;

/**
 * {@link MessageProcessorResolver} implementation which is based off the
 * keyword (i.e. first word) present in the chat message.
 * 
 * @author Nitin.Gupta
 *
 */
@Component("keywordBasedMessageProcessorResolver")
public class KeywordBasedMessageProcessorResolver implements MessageProcessorResolver {

	@Resource(name = "healthKeywordMessageProcessor")
	private KeywordBasedMessageProcessor keywordBasedMessageProcessor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcentive.hackathon.botmonitor.xmpp.messageprocessor.
	 * MessageProcessorResolver#resolve(org.jivesoftware.smack.Chat,
	 * org.jivesoftware.smack.packet.Message)
	 */
	@Override
	public MesssageProcessor resolve(Chat chat, Message message) throws XMPPException {

		/*
		 * have just one stretgey for now; hence resolving like this.
		 */
		if (keywordBasedMessageProcessor.isKeywordMatch(message.getBody())) {
			return keywordBasedMessageProcessor;
		} else {
			return null;
		}
	}
}
