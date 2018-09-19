package com.weq.adtech.ads.tracking.exception;



/**
 * The Class BadRequestException.
 * @author Sayed Hamed
 */
public class BadRequestException extends WeqAdsTrackingException {
	
    private static final long serialVersionUID = 1;

    public BadRequestException(Integer code) {
        super(400);
    }
}

