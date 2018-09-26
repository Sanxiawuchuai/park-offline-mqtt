package com.drzk.offline.vo;

import com.drzk.service.entity.SuperBody;

public class BoxReshootBody extends SuperBody
{

	private String equipmentID;
	private String conIp;
	
	/** 控制器ip */
	public void setConIp(String conIp)
	{
		this.conIp =conIp;
	}
	/** 控制器ip */
	public String getConIp()
	{
		return conIp;
	}
	/** 硬件编号 */
	public void setEquipmentID(String equipmentID)
	{
		this.equipmentID = equipmentID;
	}
	/** 硬件编号 */
	public String getEquipmentID()
	{
		return equipmentID;
	}
	
	
}
