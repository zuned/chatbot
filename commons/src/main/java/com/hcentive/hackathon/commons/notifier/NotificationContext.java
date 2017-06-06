/**
 * All rights reserved.hCentive.
 */
package com.hcentive.hackathon.commons.notifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nitin.Gupta
 * 
 */
public class NotificationContext {

	public static final String NOTIFICATION_TYPE_TRANSACTIONAL = "transactional";

	public static final String NOTIFICATION_TYPE_PROMOTIONAL = "promotional";

	private List<String> recipients = new ArrayList<String>(0);

	private String message;

	private String referenceKey;

	private Map<String, NOTIFICATION_STATUS> status = new HashMap<String, NotificationContext.NOTIFICATION_STATUS>(
			0);

	public enum NOTIFICATION_STATUS {
		SENT(1), DELIVERED(6), SEND_FAILED(3), DELIVERY_FAILED(5), DND_NUMBER(4), INVALID_NUMBER(
				2);

		private int value;

		private NOTIFICATION_STATUS(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	};

	/**
	 * transactional or marketing
	 */
	private String notificationType = NOTIFICATION_TYPE_TRANSACTIONAL;

	/** code of the country where the notification is to be sent. **/
	private String isoCountryCode = "IN";

	/**
	 * @return the recipients
	 */
	public List<String> getRecipients() {
		return recipients;
	}

	/**
	 * @param recipients
	 *            the recipients to set
	 */
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the referenceKey
	 */
	public String getReferenceKey() {
		return referenceKey;
	}

	/**
	 * @param referenceKey
	 *            the referenceKey to set
	 */
	public void setReferenceKey(String referenceKey) {
		this.referenceKey = referenceKey;
	}

	public void addRecipient(String recipient) {
		this.recipients.add(recipient);
	}

	/**
	 * @return the isoCountryCode
	 */
	public String getIsoCountryCode() {
		return isoCountryCode;
	}

	/**
	 * @param isoCountryCode
	 *            the isoCountryCode to set
	 */
	public void setIsoCountryCode(String isoCountryCode) {
		this.isoCountryCode = isoCountryCode;
	}

	/**
	 * @return the notificationType
	 */
	public String getNotificationType() {
		return notificationType;
	}

	/**
	 * @param notificationType
	 *            the notificationType to set
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * @return the status
	 */
	public Map<String, NOTIFICATION_STATUS> getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Map<String, NOTIFICATION_STATUS> status) {
		this.status = status;
	}

	public void addRecipientStatus(String recipient, NOTIFICATION_STATUS status) {
		this.status.put(recipient, status);
	}

	public NOTIFICATION_STATUS getRecipientStatus(String recipient) {
		return this.status.get(recipient);
	}
}