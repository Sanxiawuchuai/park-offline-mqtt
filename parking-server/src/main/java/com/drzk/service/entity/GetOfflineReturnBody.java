package com.drzk.service.entity;
//2.14. 提取脱机记录
public class GetOfflineReturnBody extends SuperBody {
	//记录数
	private String recordNo;
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
}
