package com.drzk.service.entity;

import com.drzk.vo.ParkCarOut;

public class ParkCarOutRecordBody extends SuperBody
{
	private ParkCarOut outRecord;
	
	/**
	 * 入场实体
	 * @param model
	 */
	public void setOutRecord(ParkCarOut model)
	{
		this.outRecord = model;
	}
	public ParkCarOut getOutRecord()
	{
		return outRecord;
	}
}
