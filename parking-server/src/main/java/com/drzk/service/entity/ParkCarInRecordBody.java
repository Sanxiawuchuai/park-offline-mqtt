package com.drzk.service.entity;

import com.drzk.vo.ParkCarIn;

public class ParkCarInRecordBody extends SuperBody
{

	private ParkCarIn inRecord;
	
	/**
	 * 入场实体
	 * @param model
	 */
	public void setInRecord(ParkCarIn model)
	{
		this.inRecord = model;
	}
	public ParkCarIn getInRecord()
	{
		return inRecord;
	}
}
