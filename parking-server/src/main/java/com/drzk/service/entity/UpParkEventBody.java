package com.drzk.service.entity;

import java.io.Serializable;

//3.3. 上传车场事件(服务器订阅)实体
public class UpParkEventBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2987038369509319247L;
	private String uId;
	private String parkingNo;//车场编号
	private String equipmentID;//设备编号
	private String conIp;//控制器IP
	private String recordNo;//流水号
	private String eventType;//事件类型
	private String eventTime;//事件时间
	private String carNo;  //车牌号
	private String carType;  //车牌颜色类型0蓝色1黄色2白色3黑色
	private String backCarNo;  //后车牌
	private String mediumType;  //介质类型 0-无，1-IC卡，2-IC转ID卡，3-ID卡，4-ETC，5-CPU，6-纸票，7-身份证UID
	private String mediumData;  //附加介质数据 十六进制，右对齐
	private String frontPic;  //前车牌图片路径
	private String smallFrontPic;  //前车牌小图片路径
	private String backPic;  //后车牌图片路径
	private String smallBackPic;  //后车牌小图片路径
	public String getParkingNo() {
		return parkingNo;
	}
	public void setParkingNo(String parkingNo) {
		this.parkingNo = parkingNo;
	}
	public String getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}
	public String getConIp() {
		return conIp;
	}
	public void setConIp(String conIp) {
		this.conIp = conIp;
	}
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	
	/**
	 * @return the carType
	 */
	public String getCarType() {
		return carType;
	}
	/**
	 * @param carType the carType to set
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getBackCarNo() {
		return backCarNo;
	}
	public void setBackCarNo(String backCarNo) {
		this.backCarNo = backCarNo;
	}
	public String getMediumType() {
		return mediumType;
	}
	public void setMediumType(String mediumType) {
		this.mediumType = mediumType;
	}
	public String getMediumData() {
		return mediumData;
	}
	public void setMediumData(String mediumData) {
		this.mediumData = mediumData;
	}
	public String getFrontPic() {
		return frontPic;
	}
	public void setFrontPic(String frontPic) {
		this.frontPic = frontPic;
	}
	public String getSmallFrontPic() {
		return smallFrontPic;
	}
	public void setSmallFrontPic(String smallFrontPic) {
		this.smallFrontPic = smallFrontPic;
	}
	public String getBackPic() {
		return backPic;
	}
	public void setBackPic(String backPic) {
		this.backPic = backPic;
	}
	public String getSmallBackPic() {
		return smallBackPic;
	}
	public void setSmallBackPic(String smallBackPic) {
		this.smallBackPic = smallBackPic;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}
