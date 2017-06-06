/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.core.service;

import com.hcentive.hackathon.core.search.model.WidgetContainer;
import com.hcentive.hackathon.core.search.model.WidgetContainer.Widget;

/**
 * @author Zuned.Ahmed
 *
 */
public interface WidgetService {

	WidgetContainer search(Widget widget);

	/**
	 * 
	 */
	WidgetContainer getStatistic();
}
