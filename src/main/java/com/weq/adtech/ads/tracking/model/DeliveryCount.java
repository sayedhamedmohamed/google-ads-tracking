package com.weq.adtech.ads.tracking.model;

/**
 * The Class DeliveryCount.
 * @author Sayed Hamed
 */
public class DeliveryCount {
	
	private String browser;
	
	private String os;

	private long count;

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
	}
