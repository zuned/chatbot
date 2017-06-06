/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.config;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.hcentive.hackathon.core.domain.Monitor;
import com.hcentive.hackathon.core.domain.MonitoredApplication;
import com.hcentive.hackathon.core.service.MonitorService;

/**
 * @author Nitin.Gupta
 *
 */
@Component("monitorConfigurer")
public class MonitorConfigurer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MonitorConfigurer.class);

	@Value("${monitor.configuration.yaml.path}")
	private String monitorConfigurationFilePath;

	@Autowired
	private MonitorService monitorService;

	private String monitorName;

	@PostConstruct
	public void init() {

		YamlReader reader;
		try {
			reader = new YamlReader(new FileReader(monitorConfigurationFilePath));
			Monitor monitor = reader.read(Monitor.class);

			monitorName = monitor.getName();

			LOGGER.debug("Configuration file at {} read successfully to configure monitor {}.",
					monitorConfigurationFilePath, monitor.getName());

			monitorService.registerMonitor(monitor);

			LOGGER.info("Monitor {} successfully registered.", monitor.getName());

		} catch (FileNotFoundException e) {
			LOGGER.error("Monitor configuration file not found at {}.", monitorConfigurationFilePath, e);
			throw new BeanInitializationException("Monitor configuration file not found.");
		} catch (YamlException e) {
			LOGGER.error("Exception occurred parsing YAML file at {}.", monitorConfigurationFilePath, e);
			throw new BeanInitializationException("Exception occurred parsing YAML file.");
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getMonitorName() {
		return monitorName;
	}
}
