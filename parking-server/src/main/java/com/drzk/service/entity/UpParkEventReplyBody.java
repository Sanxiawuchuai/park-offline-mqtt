package com.drzk.service.entity;

import java.io.Serializable;

//3.4. 上传车场事件回复(主板订阅)
public class UpParkEventReplyBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7333241495447207143L;
	private String uId;
	private String recordNo;//流水号
	
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
	
}
