package com.drzk.offline.vo;

import java.io.Serializable;

public class BoxReshootBody implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3365687226879867498L;
	private String uId;
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
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
	
}
