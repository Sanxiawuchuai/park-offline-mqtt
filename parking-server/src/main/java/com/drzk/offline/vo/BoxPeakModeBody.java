package com.drzk.offline.vo;

import java.io.Serializable;

public class BoxPeakModeBody implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8520851626670656368L;
	private String uId;
	private String controlIP;//控制器IP
	private String equipmentID;//硬件设备编号
	private String type;//0,道闸常开。1，道闸常开关闭
		
	/**
	 * 控制器IP
	 * @param controlIP
	 */
	public void setControlIP(String controlIP)
	{
		this.controlIP = controlIP;
	}
	/**
	 * 控制器IP
	 * @return
	 */
	public String getControlIP()
	{
		return controlIP;
	}
	
	/**
	 * 硬件设备编号
	 * @param equipmentID
	 */
	public void setEquipmentID(String equipmentID)
	{
		this.equipmentID=equipmentID;
	}
	/**
	 * 硬件设备编号
	 * @return
	 */
	public String getEquipmentID()
	{
		return equipmentID;
	}
	
	/**
	 * 0,道闸常开。1，道闸常开关闭
	 * @param type
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	/**
	 * 0,道闸常开。1，道闸常开关闭
	 * @return
	 */
	public String getType()
	{
		return type;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
	
	
}
