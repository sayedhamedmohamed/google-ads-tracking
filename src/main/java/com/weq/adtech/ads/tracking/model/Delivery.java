package com.weq.adtech.ads.tracking.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents delivery model
 * @author Sayed Hamed
 * said352013@gmail.com 
 */

@Document(collection = "deliveries")
public class Delivery implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Field("object_id")
	private String objectId;

	@Field("delivery_id")
	private String deliveryId;

	@Field("time")
	private Date time;

	@Field("advertisement_id")
	private Integer advertisementId;

	@Field("browser_name")
	private String browser;

	@Field("operating_system")
	private String os;

	@Field("site")
	private String site;

	@Field("click_id")
	private String clickId;
	
	@Field("click_time")
	private Date clickTime;
	
	@Field("install_id")
	private String installId;
	
	@Field("install_time")
	private Date installTime;

	/** Constructor  */
	public Delivery() {
	}

	public Delivery(Integer advertisementId, String deliveryId, Date time, String browser, String os, String site) {
		this.objectId = deliveryId;
		this.advertisementId = advertisementId;
		this.deliveryId = deliveryId;
		this.time = time;
		this.browser = browser;
		this.os = os;
		this.site = site;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Integer getAdvertisementId() {
		return advertisementId;
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public Date getTime() {
		return time;
	}

	public String getBrowser() {
		return browser;
	}

	public String getOs() {
		return os;
	}

	public String getSite() {
		return site;
	}

	public String getClickId() {
		return clickId;
	}

	public Date getClickTime() {
		return clickTime;
	}

	public String getInstallId() {
		return installId;
	}

	public Date getInstallTime() {
		return installTime;
	}

	public void setClickId(String clickId) {
		this.clickId = clickId;
	}

	public void setClickTime(Date clickTime) {
		this.clickTime = clickTime;
	}

	public void setInstallId(String installId) {
		this.installId = installId;
	}

	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}
}
