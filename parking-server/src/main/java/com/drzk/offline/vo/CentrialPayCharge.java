package com.drzk.offline.vo;

import java.io.Serializable;

import com.drzk.fact.CentreRealTimeBase;
/** 中央收费实体*/
public class CentrialPayCharge implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4228946353111929773L;
	private String uId;
//	private Integer type;
//	private String controlIP;
	private String equipmentID;
	private CentreRealTimeBase chargeData;
	private String boxIp;
	public String getuId()
	{
		return uId;
	}
	public void setuId(String uId)
	{
		this.uId = uId;
	}
	public String getEquipmentID()
	{
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID)
	{
		this.equipmentID = equipmentID;
	}
	public CentreRealTimeBase getChargeData()
	{
		return chargeData;
	}
	public void setChargeData(CentreRealTimeBase chargeData)
	{
		this.chargeData = chargeData;
	}
	public String getBoxIp()
	{
		return boxIp;
	}
	public void setBoxIp(String boxIp)
	{
		this.boxIp = boxIp;
	}
	
	
	
}
