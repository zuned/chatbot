/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.job;

import java.util.Random;

import org.dom4j.IllegalAddException;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author Nitin.Gupta
 *
 */
public class LogGenerationJob extends QuartzJobBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogGenerationJob.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @seesss
	 * org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.
	 * quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		while (true) {
			Random random = new Random();
			int nextInt = random.nextInt();

			if (nextInt % 3 == 0) {
				LOGGER.error("Illegal access error occurred.", new IllegalAccessError("This operation is not allowed"));
			} else if (nextInt % 5 == 0) {
				LOGGER.error("Not supported operation exception occurred.",
						new UnsupportedOperationException("Unsupported operation. Please check javadocs"));
			} else if (nextInt % 7 == 0) {
				LOGGER.error("A business exception has occurred.",
						new IllegalAddException("Addition of new account is not possible."));
			} else {
				LOGGER.info("Normal execution of the code is happening.");
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
