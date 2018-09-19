package com.weq.adtech.ads.tracking.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.weq.adtech.ads.tracking.exception.BadRequestException;
import com.weq.adtech.ads.tracking.model.Click;
import com.weq.adtech.ads.tracking.services.WeqAdsTrackingService;

/**
 * Represents Weq ads clicks handler
 * @author Sayed Hamed
 * said352013@gmail.com 
 * @param <T>
 */

public class WeqAdsClicksHandler<T> implements WeqAdsTrackingManager<T>{

	/** The weq ads tracking service. */
	@Autowired
	private WeqAdsTrackingService weqAdsTrackingService;
	
	@Override
	public void handle(T t) throws BadRequestException {
		
		Click delivery = (Click) t;
		
		weqAdsTrackingService.saveAdsClicks(delivery);
	}

}
