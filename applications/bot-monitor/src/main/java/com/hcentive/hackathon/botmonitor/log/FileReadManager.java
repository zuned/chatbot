/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hcentive.hackathon.botmonitor.log.model.FileMetaData;
import com.hcentive.hackathon.commons.collections.CustomFixedSizeQueque;

/**
 * @author Zuned.Ahmed
 *
 */
@Component
public class FileReadManager {

	private String searchForErrString = "ERROR [";
	
	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private int maxErroLogLineLenth = 40;
	/**
	 * @param fileMeta
	 */
	public void processFile(FileMetaData fileMeta) {
		 getErrorLog(fileMeta);
	}

	/**
	 * @param fileMeta
	 * @return
	 */
	private void getErrorLog(FileMetaData fileMeta) {
		BufferedReader logeInputReader = null;
		CustomFixedSizeQueque<String> customQuue = new CustomFixedSizeQueque<>(maxErroLogLineLenth);
		
		try {
			logeInputReader = new BufferedReader(new FileReader(fileMeta.getAbsolutePath()));
			logeInputReader.lines().skip(fileMeta.getNumberOfLineRead()).forEach(line -> {
				fileMeta.setNumberOfLineRead(fileMeta.getNumberOfLineRead() + 1);
				customQuue.add(line);
				if (line.contains(searchForErrString)) {
					fileMeta.setErrorLogString(customQuue.toString());
					fileMeta.setErrorLogFound(true);
					fileMeta.setLogSummary(line);
					logger.debug("Logging String Found :: {} ", line);
					fileMeta.setReportDateTime(new Date());
					return;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (logeInputReader != null)
				try {
					logeInputReader.close();
				} catch (IOException e) {
				}
		}
	}

}
