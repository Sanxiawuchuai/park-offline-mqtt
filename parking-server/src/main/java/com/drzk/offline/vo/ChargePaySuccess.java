package com.drzk.offline.vo;

import java.io.Serializable;

public class ChargePaySuccess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6692831246086918933L;
	private String uId; //唯一标志
	private String boxIP; //岗亭 IP
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getBoxIP() {
		return boxIP;
	}
	public void setBoxIP(String boxIP) {
		this.boxIP = boxIP;
	}
}
