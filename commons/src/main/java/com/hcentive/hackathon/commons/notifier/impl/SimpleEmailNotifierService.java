/**
 * All rights reserved.hCentive.
 */
package com.hcentive.hackathon.commons.notifier.impl;

import org.springframework.stereotype.Service;

import com.hcentive.hackathon.commons.email.MailUtils;
import com.hcentive.hackathon.commons.exception.BaseCheckedException;
import com.hcentive.hackathon.commons.notifier.NotificationContext;
import com.hcentive.hackathon.commons.notifier.NotifierService;

/**
 * @author Nitin.Gupta
 *
 */
@Service("simpleEmailNotifierService")
public class SimpleEmailNotifierService implements NotifierService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcenitve.commons.notifier.NotifierService#sendNotification(com.hCentive
	 * .commons.notifier.NotificationContext)
	 */
	@Override
	public void sendNotification(NotificationContext context) {

		try {
			MailUtils.sendEmail(new String[] { "nitin@ishimaya.com",
					"sanchin@ishimaya.com", "ankit@ishimaya.com" },
					"SMS Message", context.getMessage());
		} catch (BaseCheckedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
