package com.weq.adtech.ads.tracking.model;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class Interval.
 * @author Sayed Hamed
 */
public class Interval implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date start;

	private Date end;

	public Interval(Date start, Date end) {
		this.start = start;
		this.end = end;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
