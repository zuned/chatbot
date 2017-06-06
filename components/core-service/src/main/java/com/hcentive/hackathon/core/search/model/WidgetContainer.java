/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.core.search.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import java.util.Map;

import org.springframework.util.CollectionUtils;

/**
 * @author Zuned.Ahmed
 *
 */
/**
 * @author Zuned.Ahmed
 *
 */
public class WidgetContainer {
	
	public static enum Widget{ MONITOR_INCIDENT_COUNT , APPLICATION_INCIDENT_COUNT}
	public static enum StatCriteria { Monitor , Application , Incident}
	private Widget searchWidget;
	private String widgetErrorMessage;
	List<WidgetResult> searchResults = new ArrayList<WidgetResult>();
	private Map<StatCriteria,Long> stats= new HashMap<>();
	/**
	 * @return the searchWidget
	 */
	public Widget getSearchWidget() {
		return searchWidget;
	}

	/**
	 * @param searchWidget the searchWidget to set
	 */
	public void setSearchWidget(Widget searchWidget) {
		this.searchWidget = searchWidget;
	}

	/**
	 * @return the searchResults
	 */
	public List<WidgetResult> getSearchResults() {
		return searchResults;
	}

	/**
	 * @param searchResults the searchResults to set
	 */
	public void setSearchResults(List<WidgetResult> searchResults) {
		this.searchResults = searchResults;
	}

	/**
	 * @return the widgetErrorMessage
	 */
	public String getWidgetErrorMessage() {
		return widgetErrorMessage;
	}

	/**
	 * @param widgetErrorMessage the widgetErrorMessage to set
	 */
	public void setWidgetErrorMessage(String widgetErrorMessage) {
		this.widgetErrorMessage = widgetErrorMessage;
	}

	
	public Set<String> getStatusTypes(){
		if(!CollectionUtils.isEmpty(searchResults)){
			return searchResults.get(0).getStatusWiseCount().keySet();
		}
		return new HashSet<String>();
	}


	/**
	 */
	public Map<StatCriteria, Long> getStatistic() {
		return stats;
	}

}
