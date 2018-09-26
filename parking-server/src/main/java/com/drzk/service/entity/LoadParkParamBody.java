package com.drzk.service.entity;

import java.util.Date;

import org.joda.time.DateTime;

//加载车场系统参数实体
public class LoadParkParamBody  extends SuperBody{

	private String temporaryCarWorkingModel = "0";
	private String monthlyCarWorkingModel = "0";
	private String storedValueCarWorkingModel = "0";
	private String vipCarWorkingModel = "0";
	private String entranceType = "0";
	private String isCarReadCard = "0";
	private String noCarNoPass = "0";
	private String supplyGateControl = "0";
	private String roadGateInterface = "0";
	private String geteSense = "0";
	private String mainCameraType = "0";
	private String viceCameraType = "0";
	private String lastCameraType = "0";
	private String outNoCharge = "0";
	private String channelType = "0";
	private String fixedCardWantonly = "0";
	private String monthToTempType = "0";
	private String monthOverDays = "0";
	private Date startValidity = new DateTime(2016, 5, 1, 0, 0, 0, 0).toDate();
	private Date endValidity = new DateTime(2022, 5, 1, 0, 0, 0, 0).toDate();
	private String effectiveDays = "3600";
	private String ftpUser = "ftpuser";
	private String ftpPassWord = "123456";
	private String parkingNo;

	

	
	/**
	* parkingNo.
	*
	* @return the parkingNo
	* @since JDK 1.8
	*/
	public String getParkingNo() {
		return parkingNo;
	}

	
	/**
	* parkingNo.
	*
	* @param parkingNo the parkingNo to set
	* @since JDK 1.8
	*/
	public void setParkingNo(String parkingNo) {
		this.parkingNo = parkingNo;
	}

	public void setTemporaryCarWorkingModel(String temporaryCarWorkingModel) {
		this.temporaryCarWorkingModel = temporaryCarWorkingModel;
	}

	public String getTemporaryCarWorkingModel() {
		return temporaryCarWorkingModel;
	}

	public void setMonthlyCarWorkingModel(String monthlyCarWorkingModel) {
		this.monthlyCarWorkingModel = monthlyCarWorkingModel;
	}

	public String getMonthlyCarWorkingModel() {
		return monthlyCarWorkingModel;
	}

	public void setStoredValueCarWorkingModel(String storedValueCarWorkingModel) {
		this.storedValueCarWorkingModel = storedValueCarWorkingModel;
	}

	public String getStoredValueCarWorkingModel() {
		return storedValueCarWorkingModel;
	}

	public void setVipCarWorkingModel(String vipCarWorkingModel) {
		this.vipCarWorkingModel = vipCarWorkingModel;
	}

	public String getVipCarWorkingModel() {
		return vipCarWorkingModel;
	}

	public void setEntranceType(String entranceType) {
		this.entranceType = entranceType;
	}

	public String getEntranceType() {
		return entranceType;
	}

	public void setIsCarReadCard(String isCarReadCard) {
		this.isCarReadCard = isCarReadCard;
	}

	public String getIsCarReadCard() {
		return isCarReadCard;
	}

	public void setNoCarNoPass(String noCarNoPass) {
		this.noCarNoPass = noCarNoPass;
	}

	public String getNoCarNoPass() {
		return noCarNoPass;
	}


	public void setSupplyGateControl(String supplyGateControl) {
		this.supplyGateControl = supplyGateControl;
	}

	public String getSupplyGateControl() {
		return supplyGateControl;
	}

	public void setRoadGateInterface(String roadGateInterface) {
		this.roadGateInterface = roadGateInterface;
	}

	public String getRoadGateInterface() {
		return roadGateInterface;
	}

	public void setGeteSense(String geteSense) {
		this.geteSense = geteSense;
	}

	public String getGeteSense() {
		return geteSense;
	}

	public void setMainCameraType(String mainCameraType) {
		this.mainCameraType = mainCameraType;
	}

	public String getMainCameraType() {
		return mainCameraType;
	}

	public void setViceCameraType(String viceCameraType) {
		this.viceCameraType = viceCameraType;
	}

	public String getViceCameraType() {
		return viceCameraType;
	}

	public void setLastCameraType(String lastCameraType) {
		this.lastCameraType = lastCameraType;
	}

	public String getLastCameraType() {
		return lastCameraType;
	}

	public void setOutNoCharge(String outNoCharge) {
		this.outNoCharge = outNoCharge;
	}

	public String getOutNoCharge() {
		return outNoCharge;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setFixedCardWantonly(String fixedCardWantonly) {
		this.fixedCardWantonly = fixedCardWantonly;
	}

	public String getFixedCardWantonly() {
		return fixedCardWantonly;
	}

	public void setMonthToTempType(String monthToTempType) {
		this.monthToTempType = monthToTempType;
	}

	public String getMonthToTempType() {
		return monthToTempType;
	}

	public void setMonthOverDays(String monthOverDays) {
		this.monthOverDays = monthOverDays;
	}

	public String getMonthOverDays() {
		return monthOverDays;
	}

	public void setStartValidity(Date startValidity) {
		this.startValidity = startValidity;
	}

	public Date getStartValidity() {
		return startValidity;
	}

	public void setEndValidity(Date endValidity) {
		this.endValidity = endValidity;
	}

	public Date getEndValidity() {
		return endValidity;
	}

	public void setEffectiveDays(String effectiveDays) {
		this.effectiveDays = effectiveDays;
	}

	public String getEffectiveDays() {
		return effectiveDays;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	public String getFtpUser() {
		return ftpUser;
	}

	public void setFtpPassWord(String ftpPassWord) {
		this.ftpPassWord = ftpPassWord;
	}

	public String getFtpPassWord() {
		return ftpPassWord;
	}

}
