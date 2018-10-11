package com.drzk.offline.vo;

import java.io.Serializable;

public class HandCapBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7400690552487477101L;
	private String uId;
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
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}
