/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.xmpp;

import javax.annotation.Resource;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcentive.hackathon.botmonitor.xmpp.messageprocessor.MessageProcessorResolver;
import com.hcentive.hackathon.botmonitor.xmpp.messageprocessor.MesssageProcessor;

/**
 * @author Nitin.Gupta
 *
 */
@Component
public class StandardChatMessageListener implements ChatManagerListener, MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardChatMessageListener.class);

	@Resource(name = "keywordBasedMessageProcessorResolver")
	private MessageProcessorResolver messageProcessorResolver;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jivesoftware.smack.ChatManagerListener#chatCreated(org.jivesoftware.
	 * smack.Chat, boolean)
	 */
	@Override
	public void chatCreated(Chat chat, boolean createdLocally) {
		chat.addMessageListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jivesoftware.smack.MessageListener#processMessage(org.jivesoftware.
	 * smack.Chat, org.jivesoftware.smack.packet.Message)
	 */
	@Override
	public void processMessage(Chat chat, Message message) {
		String sender = message.getFrom();
		String senderName = sender;
		LOGGER.debug("Received Message from sender {}", senderName);

		try {
			MesssageProcessor messsageProcessor = messageProcessorResolver.resolve(chat, message);

			if (messsageProcessor == null) {
				chat.sendMessage("Message not understood. Please check help content or contact system admin.");
			}

			messsageProcessor.processMessage(chat, message);
		} catch (XMPPException e) {
			LOGGER.error("Exception Occurred while replying to sender {}.", senderName, e);
		}
	}
}