package com.drzk.offline.vo;

import com.drzk.service.entity.SuperBody;

public class HandCapBody extends SuperBody
{

	private String camIP;
	private String equipmentID;
	
	public String getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}
	public void setCamIP(String camIP)
	{
		this.camIP = camIP;
	}
	public  String getCamIP()
	{
		return camIP;
	}
}
