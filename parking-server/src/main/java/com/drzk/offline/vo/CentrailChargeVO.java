package com.drzk.offline.vo;

import com.drzk.vo.ParkCarIn;

/** 岗亭申请中央收费实体 */
public class CentrailChargeVO
{

	private String uId;
	private String boxIP;
	private String equipmentID;
	private ParkCarIn parkCarIn;
	
	public String getuId()
	{
		return uId;
	}
	public void setuId(String uId)
	{
		this.uId = uId;
	}
	public String getBoxIP()
	{
		return boxIP;
	}
	public void setBoxIP(String boxIP)
	{
		this.boxIP = boxIP;
	}
	public String getEquipmentID()
	{
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID)
	{
		this.equipmentID = equipmentID;
	}
	public ParkCarIn getParkCarIn()
	{
		return parkCarIn;
	}
	public void setParkCarIn(ParkCarIn parkCarIn)
	{
		this.parkCarIn = parkCarIn;
	}
	
	
}
