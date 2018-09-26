package com.drzk.offline.vo;

import java.util.List;

import com.drzk.fact.OutRealTimeBase;
import com.drzk.service.entity.UpParkEventBody;
import com.drzk.vo.ParkCarIn;

public class ParkOutChanelCorrectingBody extends SuperBody
{
	private String controlIP;
	private String equipmentID;
	private String type;
	private OutRealTimeBase outRecord;//
	//private List<ParkCarIn> similarInRecord;
	
	
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
}
