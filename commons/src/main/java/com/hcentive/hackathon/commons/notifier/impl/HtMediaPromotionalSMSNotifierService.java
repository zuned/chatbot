/**
 * All rights reserved.hCentive.
 */
package com.hcentive.hackathon.commons.notifier.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.hcentive.hackathon.commons.notifier.NotificationContext;
import com.hcentive.hackathon.commons.notifier.NotifierService;
import com.hcentive.hackathon.commons.notifier.NotificationContext.NOTIFICATION_STATUS;
import com.hcentive.hackathon.commons.utils.PhoneNumberUtils;

/**
 * @author Nitin.Gupta
 * 
 */
@Service("htMediaPromotionalSMSNotifierService")
public class HtMediaPromotionalSMSNotifierService implements NotifierService {

	private static final Logger logger = LoggerFactory
			.getLogger(HtMediaPromotionalSMSNotifierService.class);

	private RestTemplate restTemplate;

	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
		// objectFactory = new ObjectFactory();
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

				context.addRecipientStatus(recipient,
						NOTIFICATION_STATUS.INVALID_NUMBER);

				continue;
			}

			logger.info(
					"Sending SMS Notification to recipient at {} for reference Id {}",
					recipient, context.getReferenceKey());

			Map<String, String> uriVariables = new HashMap<String, String>(0);
			uriVariables.put("accesskey", "sA1jk1OA6hF4V4lT3YKIalvyV9nPgY");
			uriVariables.put("to", recipient);
			uriVariables.put("text", context.getMessage());
			uriVariables.put("from", "ISHMYA");

			try {
				restTemplate
						.exchange(
								"http://180.179.104.9/smspush-enterprise/api/push?accesskey={accesskey}&to={to}&text={text}&from={from}",
								HttpMethod.GET, null, String.class,
								uriVariables);

				logger.debug(
						"SMS Notification sent to recipient at {} for reference Id {}",
						recipient, context.getReferenceKey());

				context.addRecipientStatus(recipient, NOTIFICATION_STATUS.SENT);

			} catch (RestClientException e) {
				logger.error(
						"SMS Notification to recipient at {} could not be sent",
						recipient, e);

				if (e instanceof HttpClientErrorException) {
					HttpClientErrorException clientErrorException = (HttpClientErrorException) e;

					if (clientErrorException.getStatusCode().equals(
							HttpStatus.FORBIDDEN)
							&& clientErrorException
									.getStatusText()
									.contains(
											"Mobile number exists under NCPR database.")) {
						context.addRecipientStatus(recipient,
								NOTIFICATION_STATUS.DND_NUMBER);

						continue;
					}
				}
				context.addRecipientStatus(recipient,
						NOTIFICATION_STATUS.SEND_FAILED);
			}
		}
	}

	/**
	 * @param recipients
	 */
	private void preProcessRecipients(List<String> recipients) {
		/*
		 * recipients.add("919716017701"); recipients.add("919999944889");
		 * recipients.add("919810777737");
		 */
	}

}
