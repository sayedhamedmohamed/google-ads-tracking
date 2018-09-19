package com.weq.adtech.ads.tracking.model;

import java.io.Serializable;

/**
 * The Class StatsDetails.
 * @author Sayed Hamed
 */
public class StatsDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	private Fields fields;

	private Stats stats;

	public StatsDetails(Fields fields, Stats stats) {
		this.fields = fields;
		this.stats = stats;
	}

	public Fields getFields() {
		return fields;
	}

	public Stats getStats() {
		return stats;
	}
}
