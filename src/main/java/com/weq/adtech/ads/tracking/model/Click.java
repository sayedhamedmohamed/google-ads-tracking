package com.weq.adtech.ads.tracking.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents click
 * @author sayedhamed
 *
 */
public class Click implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date time;

	private String clickId;
	
	private String deliveryId;

	public Click() {
	}

	public Click(Date time, String clickId, String deliveryId) {
		this.time = time;
		this.clickId = clickId;
		this.deliveryId = deliveryId;
	}

	public Date getTime() {
		return time;
	}

	public String getClickId() {
		return clickId;
	}
	
	public String getDeliveryId() {
		return deliveryId;
	}
}
