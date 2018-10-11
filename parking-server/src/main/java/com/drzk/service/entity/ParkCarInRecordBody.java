package com.drzk.service.entity;

import java.io.Serializable;

import com.drzk.vo.ParkCarIn;

public class ParkCarInRecordBody implements Serializable
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 201055089309685980L;
	private String uId;
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
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}
