package com.weq.adtech.ads.tracking.model;

import java.io.Serializable;

/**
 * The Class Fields.
 * @author Sayed Hamed
 */
public class Fields implements Serializable{

	private static final long serialVersionUID = 1L;

	private String browser;

	private String os;

	public Fields(String browser, String os) {
		this.browser = browser;
		this.os = os;
	}

	public String getBrowser() {
		return browser;
	}

	public String getOs() {
		return os;
	}
}
