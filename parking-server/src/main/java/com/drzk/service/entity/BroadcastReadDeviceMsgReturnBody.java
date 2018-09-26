package com.drzk.service.entity;
////2.1. 广播读取设备信息返回实体
public class BroadcastReadDeviceMsgReturnBody  extends SuperBody{
	private String equipmentModel;//设备型号
	private String equipmentIP;//设备IP
	private String equipmentName;//设备名称
	private String equipmentID;//设备编号
	private String equipmentVersion;//设备版本号
	private String equipmentDescription;//设备说明
	public String getEquipmentModel() {
		return equipmentModel;
	}
	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	public String getEquipmentIP() {
		return equipmentIP;
	}
	public void setEquipmentIP(String equipmentIP) {
		this.equipmentIP = equipmentIP;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}
	public String getEquipmentVersion() {
		return equipmentVersion;
	}
	public void setEquipmentVersion(String equipmentVersion) {
		this.equipmentVersion = equipmentVersion;
	}
	public String getEquipmentDescription() {
		return equipmentDescription;
	}
	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}
	
	
}
