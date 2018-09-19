package com.weq.adtech.ads.tracking.exception;

/**
 * The Class ServerErrorException.
 * @author Sayed Hamed
 */
public class ServerErrorException extends WeqAdsTrackingException {
	
    private static final long serialVersionUID = 1;

    public ServerErrorException() {
    	
        super(500);
    }
}