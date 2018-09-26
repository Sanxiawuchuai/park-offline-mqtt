package com.drzk.service.entity;
//3.4. 上传车场事件回复(主板订阅)
public class UpParkEventReplyBody  extends SuperBody{
	private String recordNo;//流水号
	
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	
	
}
