package com.drzk.offline.vo;

import com.drzk.vo.ParkCarIn;

public class BoxModifyCarNoBody extends SuperBody
{
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
		
}
