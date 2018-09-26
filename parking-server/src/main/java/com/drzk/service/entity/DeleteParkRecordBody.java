package com.drzk.service.entity;
//2.16. 删除车场记录
public class DeleteParkRecordBody  extends SuperBody{
	private String recordNo;//流水号
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
}
