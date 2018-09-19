package com.weq.adtech.ads.tracking.model;

import java.io.Serializable;
import java.util.List;

/**
 * The Class AdsStatistics.
 * @author Sayed Hamed
 */

public class AdsStatistics implements Serializable {

	private static final long serialVersionUID = 1L;

	private Interval interval;

	private List<StatsDetails> data;
	
	private Stats stats;
	
	public AdsStatistics(Interval interval, Stats stats) {
		this.interval = interval;
		this.stats = stats;
	}
	public AdsStatistics(Interval interval, List<StatsDetails> data) {
		this.interval = interval;
		this.data = data;
	}

	public Interval getInterval() {
		return interval;
	}

	public List<StatsDetails> getDate() {
		return data;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public void setDate(List<StatsDetails> data) {
		this.data = data;
	}
	
	public Stats getStats() {
		return stats;
	}
}
