package com.weq.adtech.ads.tracking.constants;

/**
 * Represents application constants
 * @author Sayed Hamed
 * said352013@gmail.com
 */
public final class AppConstants {

	/**
	 * To avoid creating instance of utility class.
	 */
	private AppConstants() {
		throw new IllegalStateException("Utility class are not meant to be instantiated.");
	}

	/** The Constant SUCCESS. */
	public static final Integer SUCCESS = 200;
	
	/** The Constant FAILURE. */
	public static final Integer FAILURE = 500;
	
	/** The Constant DELIVERY_ID_NOT_FOUND. */
	public static final Integer DELIVERY_ID_NOT_FOUND = 404;
	
	/** The Constant CLICK_ID_NOT_FOUND. */
	public static final Integer CLICK_ID_NOT_FOUND = 404;


}
