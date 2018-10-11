package com.drzk.service.entity;

import java.io.Serializable;

import com.drzk.vo.ParkCarOut;

public class ParkCarOutRecordBody implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2878332399505852282L;
	private String uId;
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
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}
