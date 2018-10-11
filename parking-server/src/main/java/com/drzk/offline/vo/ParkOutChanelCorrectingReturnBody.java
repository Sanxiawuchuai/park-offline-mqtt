package com.drzk.offline.vo;


import java.io.Serializable;

import com.drzk.fact.OutRealTimeBase;
import com.drzk.service.entity.UpParkEventBody;
import com.drzk.vo.ParkCarIn;

public class ParkOutChanelCorrectingReturnBody implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -488813325645980989L;
	private String uId;
	private String controlIP;
	private String equipmentID;
	private String type;
	private OutRealTimeBase outRecord;//
	private ParkCarIn similarInRecord;
	
	/** 匹配的入场记录 */
	public ParkCarIn getSimilarInRecord()
	{
		return similarInRecord;
	}
	public void setSimilarInRecord(ParkCarIn similarInRecord)
	{
		this.similarInRecord = similarInRecord;
	}
	public String getControlIP()
	{
		return controlIP;
	}
	public void setControlIP(String controlIP)
	{
		this.controlIP = controlIP;
	}
	public String getEquipmentID()
	{
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID)
	{
		this.equipmentID = equipmentID;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public OutRealTimeBase getOutRecord()
	{
		return outRecord;
	}
	public void setOutRecord(OutRealTimeBase outRecord)
	{
		this.outRecord = outRecord;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}
