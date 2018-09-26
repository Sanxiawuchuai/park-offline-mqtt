package com.drzk.service.entity;

import java.util.Date;

//3.6. 上传车场状态回复(主板订阅)实体
public class UpParkStateReplyBody  extends SuperBody{
	private Date sysTime; //系统时间

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}

}
