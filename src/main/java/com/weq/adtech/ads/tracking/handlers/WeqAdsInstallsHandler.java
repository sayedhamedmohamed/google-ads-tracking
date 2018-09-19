package com.weq.adtech.ads.tracking.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.weq.adtech.ads.tracking.exception.BadRequestException;
import com.weq.adtech.ads.tracking.model.Install;
import com.weq.adtech.ads.tracking.services.WeqAdsTrackingService;


/**
 * Represents Weq ads installs handler
 * @author Sayed Hamed
 * said352013@gmail.com 
 * @param <T> the generic type
 */
public class WeqAdsInstallsHandler<T> implements WeqAdsTrackingManager<T>{

	/** The weq ads tracking service. */
	@Autowired
	private WeqAdsTrackingService weqAdsTrackingService;

	@Override
	public void handle(T t) throws BadRequestException{

		Install provided = (Install) t; 

		weqAdsTrackingService.saveAdsInstall(provided);
	}

}
