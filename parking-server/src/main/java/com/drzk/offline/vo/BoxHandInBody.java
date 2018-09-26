package com.drzk.offline.vo;

public class BoxHandInBody extends SuperBody
{

	private String carNo;//车牌
	private String carNoType;//车牌颜色 0—蓝色,1-黄，2—白,3-黑
	private String controlIP;//控制器IP
	private String equipmentID;//硬件设备编号
	
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	public String getCarNo()
	{
		return carNo;
	}
	
	public void setCarNoType(String carNoType)
	{
		this.carNoType = carNoType;
	}
	public String getCarNoType()
	{
		return carNoType;
	}
	
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
		this.equipmentID = equipmentID;
	}
	/**
	 * 硬件设备编号
	 * @return
	 */
	public String getEquipmentID()
	{
		return equipmentID;
	}
	
}
