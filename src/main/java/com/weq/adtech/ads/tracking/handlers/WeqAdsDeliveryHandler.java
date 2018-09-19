package com.weq.adtech.ads.tracking.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.weq.adtech.ads.tracking.constants.AppConstants;
import com.weq.adtech.ads.tracking.exception.BadRequestException;
import com.weq.adtech.ads.tracking.model.Delivery;
import com.weq.adtech.ads.tracking.services.WeqAdsTrackingService;

/**
 * Represents Weq ads delivery handler
 * @author Sayed Hamed
 * said352013@gmail.com 
 * @param <T>
 */

public class WeqAdsDeliveryHandler<T> implements WeqAdsTrackingManager<T>{

	
	/** The weq ads tracking service. */
	@Autowired
	private WeqAdsTrackingService weqAdsTrackingService;
	
	@Override
	public void handle(T t) throws BadRequestException{
		
		Delivery providedDelivery = (Delivery) t;
		
		if(providedDelivery.getDeliveryId() == null)
			throw new BadRequestException(AppConstants.DELIVERY_ID_NOT_FOUND);

		
		providedDelivery = new Delivery(providedDelivery.getAdvertisementId(), providedDelivery.getDeliveryId(),
				providedDelivery.getTime(), providedDelivery.getBrowser(), providedDelivery.getOs(), providedDelivery.getSite());
		
		weqAdsTrackingService.saveAdsDelivery(providedDelivery);
	}


}
