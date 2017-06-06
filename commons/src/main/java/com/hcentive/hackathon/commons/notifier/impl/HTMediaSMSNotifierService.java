/**
 * All rights reserved.hCentive.
 */
package com.hcentive.hackathon.commons.notifier.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.hcentive.hackathon.commons.notifier.NotificationContext;
import com.hcentive.hackathon.commons.notifier.NotifierService;
import com.hcentive.hackathon.commons.notifier.impl.htmedia.Message;
import com.hcentive.hackathon.commons.notifier.impl.htmedia.ObjectFactory;
import com.hcentive.hackathon.commons.notifier.impl.htmedia.Message.Submit;
import com.hcentive.hackathon.commons.notifier.impl.htmedia.Message.Submit.Da;
import com.hcentive.hackathon.commons.notifier.impl.htmedia.Message.Submit.From;
import com.hcentive.hackathon.commons.notifier.impl.htmedia.Message.Submit.Oa;
import com.hcentive.hackathon.commons.utils.PhoneNumberUtils;

/**
 * @author Nitin.Gupta
 * 
 */
@Service("htMediaSMSNotifierService")
public class HTMediaSMSNotifierService implements NotifierService {

	private static final Logger logger = LoggerFactory
			.getLogger(HTMediaSMSNotifierService.class);

	private RestTemplate restTemplate;
	private ObjectFactory objectFactory;

	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
		objectFactory = new ObjectFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hCentive.commons.notifier.NotifierService#sendNotification(com.hCentive
	 * .commons.notifier.NotificationContext)
	 */
	@Override
	public void sendNotification(NotificationContext context) {

		if (context == null || context.getRecipients() == null) {
			logger.warn("SMS notification could not be sent as either context is null or there are no recipients.");
		}

		logger.debug(
				"Sending SMS Notifications to {} recipients for reference Id {}",
				context.getRecipients().size(), context.getReferenceKey());

		preProcessRecipients(context.getRecipients());

		for (String recipient : context.getRecipients()) {

			String validPhoneNumber = PhoneNumberUtils.getValidPhoneNumber(
					recipient, context.getIsoCountryCode());

			if (validPhoneNumber == null) {
				logger.info(
						"SMS Notifications to recipient with phone number {} for reference Id {} could not be sent as it is not a valid mobile number.",
						recipient, context.getReferenceKey());

				continue;
			}

			Message message = objectFactory.createMessage();
			Submit submit = objectFactory.createMessageSubmit();
			Da da = objectFactory.createMessageSubmitDa();
			From from = objectFactory.createMessageSubmitFrom();
			Oa oa = objectFactory.createMessageSubmitOa();

			message.setSubmit(submit);
			submit.setDa(da);
			submit.setFrom(from);
			submit.setOa(oa);

			submit.setUd(context.getMessage());

			oa.setNumber("ISHMYA");
			from.setUsername("ishima");
			from.setPassword("ishimatrans");

			da.setNumber(recipient);

			try {
				logger.info(
						"Sending SMS Notification to recipient at {} for reference Id {}",
						recipient, context.getReferenceKey());

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.TEXT_XML);

				HttpEntity<Message> entity = new HttpEntity<Message>(message,
						headers);

				ResponseEntity<String> response = restTemplate
						.exchange(
								new URI(
										"http://124.153.79.193/smsapi/smsproducer.php"),
								HttpMethod.POST, entity, String.class);

				logger.debug(
						"SMS Notification sent to recipient at {} for reference Id {}",
						recipient, context.getReferenceKey());

			} catch (RestClientException e) {
				logger.error(
						"SMS Notification to recipient at {} could not be sent",
						recipient, e);
				throw e;
			} catch (URISyntaxException e) {
				logger.error(
						"SMS Notification to recipient at {} could not be sent",
						recipient, e);
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * @param recipients
	 */
	private void preProcessRecipients(List<String> recipients) {
		recipients.add("919716017701");
		recipients.add("919999944889");
		recipients.add("919810777737");
	}
}
