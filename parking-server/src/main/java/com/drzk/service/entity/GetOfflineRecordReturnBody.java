package com.drzk.service.entity;

import java.io.Serializable;
import java.util.Date;

//2.15. 获取车场记录返回实体
public class GetOfflineRecordReturnBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1891855123668170542L;
	private String parkingNo; //车场编号
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
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

	public String getCarColorType() {
		return carColorType;
	}

	public void setCarColorType(String carColorType) {
		this.carColorType = carColorType;
	}

	public String getGenuineAndSham() {
		return genuineAndSham;
	}

	public void setGenuineAndSham(String genuineAndSham) {
		this.genuineAndSham = genuineAndSham;
	}

	public String getIsBackCarNo() {
		return isBackCarNo;
	}

	public void setIsBackCarNo(String isBackCarNo) {
		this.isBackCarNo = isBackCarNo;
	}


	private String equipmentID; // 设备编号
	private String conIp;       //控制器Ip
	private String recordNo;// 流水号
	private String recordType;// 记录类型
	private String carType;// 车辆类型
	private String carColorType="0";  //车牌颜色类型 0蓝色1黄色2白色3黑色
	private String carNo;// 车牌号
	private String genuineAndSham ; //真假车牌 0表真车牌，1假车牌]
	private String isBackCarNo ; //0前车牌识别，1后车牌认别
	private String mediumType;// 介质类型 0-无，1-IC卡，2-IC转ID卡，3-ID卡，4-ETC，5-CPU，6-纸票，7-身份证UID
	private String mediumData;// 附加介质数据 十六进制，右对齐
	private Date inTime;// 入场时间 年月日时分秒
	private String centerChargeTime;// 入场时间 年月日时分秒
	private Date discountTime;// 入场时间 年月日时分秒
	private Date outTime;// 入场时间 年月日时分秒
	private String chargeableMoney;// 应收费金额 单位为分
	private String realChargeMoney;// 实收费金额 单位为分
	private String storedValueRemain;// 储值余额 单位为分
	private String discountNumber;// 打折机号
	private String discountID;// 折扣ID
	private String inPic;// 入场图片
	private String smallInPic;// 入场小车牌图片
	private String outPic;// 出场图片
	private String smallOutPic;// 出场小车牌图片

	private String carEventNo;// 车辆事件序号

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
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

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public String getCenterChargeTime() {
		return centerChargeTime;
	}

	public void setCenterChargeTime(String centerChargeTime) {
		this.centerChargeTime = centerChargeTime;
	}

	public Date getDiscountTime() {
		return discountTime;
	}

	public void setDiscountTime(Date discountTime) {
		this.discountTime = discountTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public String getChargeableMoney() {
		return chargeableMoney;
	}

	public void setChargeableMoney(String chargeableMoney) {
		this.chargeableMoney = chargeableMoney;
	}

	public String getRealChargeMoney() {
		return realChargeMoney;
	}

	public void setRealChargeMoney(String realChargeMoney) {
		this.realChargeMoney = realChargeMoney;
	}

	public String getStoredValueRemain() {
		return storedValueRemain;
	}

	public void setStoredValueRemain(String storedValueRemain) {
		this.storedValueRemain = storedValueRemain;
	}

	public String getDiscountNumber() {
		return discountNumber;
	}

	public void setDiscountNumber(String discountNumber) {
		this.discountNumber = discountNumber;
	}

	public String getDiscountID() {
		return discountID;
	}

	public void setDiscountID(String discountID) {
		this.discountID = discountID;
	}

	public String getCarEventNo() {
		return carEventNo;
	}

	public void setCarEventNo(String carEventNo) {
		this.carEventNo = carEventNo;
	}

	
	/**
	* inPic.
	*
	* @return the inPic
	* @since JDK 1.8
	*/
	public String getInPic() {
		return inPic;
	}

	
	/**
	* inPic.
	*
	* @param inPic the inPic to set
	* @since JDK 1.8
	*/
	public void setInPic(String inPic) {
		this.inPic = inPic;
	}

	
	/**
	* smallInPic.
	*
	* @return the smallInPic
	* @since JDK 1.8
	*/
	public String getSmallInPic() {
		return smallInPic;
	}

	
	/**
	* smallInPic.
	*
	* @param smallInPic the smallInPic to set
	* @since JDK 1.8
	*/
	public void setSmallInPic(String smallInPic) {
		this.smallInPic = smallInPic;
	}

	
	/**
	* outPic.
	*
	* @return the outPic
	* @since JDK 1.8
	*/
	public String getOutPic() {
		return outPic;
	}

	
	/**
	* outPic.
	*
	* @param outPic the outPic to set
	* @since JDK 1.8
	*/
	public void setOutPic(String outPic) {
		this.outPic = outPic;
	}

	
	/**
	* smallOutPic.
	*
	* @return the smallOutPic
	* @since JDK 1.8
	*/
	public String getSmallOutPic() {
		return smallOutPic;
	}

	
	/**
	* smallOutPic.
	*
	* @param smallOutPic the smallOutPic to set
	* @since JDK 1.8
	*/
	public void setSmallOutPic(String smallOutPic) {
		this.smallOutPic = smallOutPic;
	}
	
	

}
