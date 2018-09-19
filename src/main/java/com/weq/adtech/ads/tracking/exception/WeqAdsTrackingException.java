package com.weq.adtech.ads.tracking.exception;

/**
 * The Class WeqAdsTrackingException.
 *
 * @author sayedhamed
 */
public class WeqAdsTrackingException extends Exception {
	
	private static final long serialVersionUID = 1L;

    private final Integer code;

    
    public WeqAdsTrackingException(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}

