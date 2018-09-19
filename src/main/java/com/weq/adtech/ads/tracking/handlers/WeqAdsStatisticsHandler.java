package com.weq.adtech.ads.tracking.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.weq.adtech.ads.tracking.model.AdsStatistics;
import com.weq.adtech.ads.tracking.services.WeqAdsTrackingService;

/**
 * Represents Weq ads statistics handler
 * @author Sayed Hamed
 * said352013@gmail.com 
 */
public class WeqAdsStatisticsHandler {

	/** The weq ads tracking service. */
	@Autowired
	private WeqAdsTrackingService weqAdsTrackingService;
	
	/**
	 * Handle.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the ads statistics
	 */
	public AdsStatistics handle(String start, String end) {
		
		return weqAdsTrackingService.findStatsByStartAndEndDates(start, end);
	}
	
	/**
	 * Handle.
	 *
	 * @param start the start
	 * @param end the end
	 * @param category the category
	 * @return the ads statistics
	 */
	public AdsStatistics handle(String start, String end, String[] category) {
		
		return weqAdsTrackingService.findStatsByStartAndEndDatesGroupBy(start, end, category);
	}
	

}
