package com.drzk.service.entity;

import java.io.Serializable;

import com.drzk.fact.InRealTimeBase;

public class BoxInIsOpenBody implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1437218318143649537L;
	private String controlIP;
	private String equipmentID;
	private String type;
	private InRealTimeBase inRecord;
	private String uId;
	
	/** 车场入场实体 8 */
	public void setInRecord(InRealTimeBase inRecord)
	{
		this.inRecord = inRecord;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	/** 车场入场实体 8 */
	public InRealTimeBase getInRecord()
	{
		return inRecord;
	}
	
	/**  0 表示需要处理，1，表示不需处理，当服务端发送岗亭端，此时弹框取消 */
	public void setType(String type)
	{
		this.type =type;
	}
	/**  0 表示需要处理，1，表示不需处理，当服务端发送岗亭端，此时弹框取消 */
	public String getType()
	{
		return type;
	}
	
	/** 硬件设备编号 */
	public void setEquipmentID(String equipmentID)
	{
		this.equipmentID =equipmentID;
	}
	/** 硬件设备编号 */
	public String getEquipmentID()
	{
		return equipmentID;
	}
	
	/** 控制器IP */
	public void setControlIP(String controlIP)
	{
		this.controlIP =controlIP;
	}
	/** 控制器IP */
	public String getControlIP()
	{
		return controlIP;
	}
}
