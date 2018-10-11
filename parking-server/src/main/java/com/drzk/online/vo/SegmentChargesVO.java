package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 分段收费
 */
public class SegmentChargesVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5749183378694772916L;
	private Integer time;
	private Double charge;

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}
}
