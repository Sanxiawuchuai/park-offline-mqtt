package com.drzk.service.entity;

import java.io.Serializable;
import java.util.Date;

//3.6. 上传车场状态回复(主板订阅)实体
public class UpParkStateReplyBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7514361888491499748L;
	private String uId;
	private Date sysTime; //系统时间

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

}
