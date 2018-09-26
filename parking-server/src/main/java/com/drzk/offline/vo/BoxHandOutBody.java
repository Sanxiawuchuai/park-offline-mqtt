package com.drzk.offline.vo;
/** 手工出场实体 */
public class BoxHandOutBody extends SuperBody
{

	private String carNo;//车牌
	private String ID;//入场记录号
	private String pInCarNo;//原入场车牌
	private String controlIP;//控制器IP
	private String equipmentID;//硬件设备编号
	
	/**
	 * 车牌号码
	 * @param carNo
	 */
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	/**
	 * 车牌号码
	 * @return
	 */
	public String getCarNo()
	{
		return carNo;
	}
	
	/**
	 * 入场记录号
	 * @param ID
	 */
	public void setID(String ID)
	{
		this.ID = ID;
	}
	/**
	 * 入场记录号
	 * @return
	 */
	public String getID()
	{
		return ID;
	}
	
	/**
	 * 原入场车牌
	 * @param pInCarNo
	 */
	public void setPInCarNo(String pInCarNo)
	{
		this.pInCarNo = pInCarNo;
	}
	/**
	 * 原入场车牌
	 * @return
	 */
	public String getPInCarNo()
	{
		return pInCarNo;
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
