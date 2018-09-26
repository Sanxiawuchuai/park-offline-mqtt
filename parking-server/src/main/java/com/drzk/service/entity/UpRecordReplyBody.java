package com.drzk.service.entity;
//3.2. 上传记录回复(主板订阅)实体
public class UpRecordReplyBody  extends SuperBody{
	private String recordNo;//流水号uId
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
}
