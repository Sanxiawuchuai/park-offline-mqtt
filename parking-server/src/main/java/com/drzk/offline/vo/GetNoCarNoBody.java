package com.drzk.offline.vo;

import java.io.Serializable;

public class GetNoCarNoBody implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7982234009514245497L;
	private String uId;
	private String controlIP;//控制器IP
	private String equipmentID;//硬件设备编号
	private String nopCarNo;//无牌车车牌
		
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
	 * 无牌车车牌
	 * @return
	 */
	public String getNopCarNo() {
		return nopCarNo;
	}
	/**
	 * 无牌车车牌
	 * @param nopCarNo
	 */
	public void setNopCarNo(String nopCarNo) {
		this.nopCarNo = nopCarNo;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
	
	
	
}
