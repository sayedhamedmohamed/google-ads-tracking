package com.weq.adtech.ads.tracking.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents install
 * @author sayedhamed
 *
 */
public class Install implements Serializable {

	private static final long serialVersionUID = 1L;

	private String installId;
	
	private String clickId;
	
	private Date time;

	public Install() {
	}

	public Install(String installId, String clickId, Date time) {
		this.installId = installId;
		this.clickId = clickId;
		this.time = time;
	}

	public String getInstallId() {
		return installId;
	}

	public String getClickId() {
		return clickId;
	}

	public Date getTime() {
		return time;
	}
}
