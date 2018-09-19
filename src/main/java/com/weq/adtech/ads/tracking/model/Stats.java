package com.weq.adtech.ads.tracking.model;

import java.io.Serializable;

/**
 * The Class Stats.
 * @author Sayed Hamed
 */
public class Stats implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long  deliveries;
	
	private Long clicks;
	
	private Long installs;
	
	public Long getDeliveries() {
		return deliveries;
	}

	public Long getClicks() {
		return clicks;
	}

	public Long getInstalls() {
		return installs;
	}

	public Stats(Long deliveries, Long clicks, Long installs) {
		this.deliveries = deliveries;
		this.clicks = clicks;
		this.installs = installs;
	}
	
	
}
