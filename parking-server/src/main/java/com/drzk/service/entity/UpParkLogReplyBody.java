package com.drzk.service.entity;
//3.8. 上传日志信息回复(主板订阅)实体
public class UpParkLogReplyBody  extends SuperBody{
	private String recordNo;//流水号 
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
}
