package com.weq.adtech.ads.tracking.handlers;

import com.weq.adtech.ads.tracking.exception.BadRequestException;


/**
 * The Interface WeqAdsTrackingManager.
 * @author Sayed Hamed
 * @param <T> the generic type
 */
@FunctionalInterface
public interface WeqAdsTrackingManager<T> {

	/**
	 * Handle.
	 *
	 * @param t the t
	 * @throws BadRequestException the bad request exception
	 */
	public void handle(T t) throws BadRequestException;
}
