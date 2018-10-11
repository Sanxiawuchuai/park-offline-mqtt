package com.drzk.offline.vo;

import java.io.Serializable;

import com.drzk.vo.ParkCarIn;

public class BoxModifyCarNoBody implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7887459377736059917L;
	private String uId;
	private String controlIP;//控制器IP
	private String equipmentID;//硬件设备编号
	private ParkCarIn parkCarIn;//入场记录实体

	public ParkCarIn getParkCarIn() {
		return parkCarIn;
	}

	public void setParkCarIn(ParkCarIn parkCarIn) {
		this.parkCarIn = parkCarIn;
	}

	public String getControlIP() {
		return controlIP;
	}

	public void setControlIP(String controlIP) {
		this.controlIP = controlIP;
	}

	public String getEquipmentID() {
		return equipmentID;
	}

	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}
		
}
