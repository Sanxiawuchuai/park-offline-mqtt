package com.drzk.offline.vo;

import java.io.Serializable;

public class BoxComputerGateBody implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7562645822414659528L;
	private String uId;
	private String controlIP;//控制器IP
	private String equipmentID;//硬件设备编号
	private String type;//0,开闸 1,停闸,2,关闸
	private String carNo;//车牌号码
	private String reason;//开闸原因
	
	
	
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
	 * 0,开闸 1,停闸,2,关闸
	 * @param type
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	/**
	 * 0,开闸 1,停闸,2,关闸
	 * @return
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * 车牌
	 * @param carNo
	 */
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	/**
	 * 车牌
	 * @return
	 */
	public String getCarNo()
	{
		return carNo;
	}
	
	/**
	 * 开闸原因
	 * @param reason
	 */
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	/**
	 * 开闸原因
	 * @return
	 */
	public String getReason()
	{
		return reason;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
	
}
