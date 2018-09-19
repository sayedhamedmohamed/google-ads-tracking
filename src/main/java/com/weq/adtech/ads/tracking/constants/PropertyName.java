package com.weq.adtech.ads.tracking.constants;

/**
 * The Class PropertyName.
 *
 * @author sayedhamed
 */
public final class PropertyName {
	
	/**
	 * To avoid creating instance of utility class.
	 */
	private PropertyName() {
		throw new IllegalStateException("Utility class are not meant to be instantiated.");
	}
	
	/** The Constant DELIVERY_ID. */
	public static final String DELIVERY_ID = "deliveryId";

	/** The Constant CLICK_ID. */
	public static final String CLICK_ID = "clickId";

	/** The Constant INSTALL_ID. */
	public static final String INSTALL_ID = "installId";

	/** The Constant COUNT. */
	public static final String COUNT = "count";
	
	/** The Constant TIME. */
	public static final String TIME = "time";
}
