/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.core.service;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcentive.hackathon.core.AbstractTest;
import com.hcentive.hackathon.core.search.model.WidgetContainer;
import com.hcentive.hackathon.core.search.model.WidgetContainer.Widget;

/**
 * @author Zuned.Ahmed
 *
 */
public class WidgetServiceTest extends AbstractTest {
	
	@Autowired
	private WidgetService widgetService;

	@Test
	public void searchTest() {
		WidgetContainer  widgetContainer  = widgetService.search(Widget.MONITOR_INCIDENT_COUNT);
		if(StringUtils.isNotBlank(widgetContainer.getWidgetErrorMessage())){
			System.out.println(widgetContainer.getWidgetErrorMessage());
		}else{
			System.out.println(widgetContainer.getSearchResults());
		}
	}
	

}
