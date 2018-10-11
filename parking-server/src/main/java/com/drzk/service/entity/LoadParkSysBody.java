package com.drzk.service.entity;

import java.io.Serializable;

//加载车场系统参数实体
public class LoadParkSysBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7634792774281914096L;
	private String uId;
	// 临时车工作模式 00-自动，01-确认
	private String temporaryCarWorkingModel;
	// 月租车工作模式 0-自动，1-确认 2-受控
	private String monthlyCarWorkingModel;
	// 储值车工作模式 00-自动，01-确认
	private String storedValueCarWorkingModel;
	// 免费车工作模式 00-自动， 01-在线
	private String vipCarWorkingModel;
	// 出入口类型 0-标准入口，1-标准出口，2-嵌套入口，3-嵌套出口，4-半嵌套入口，5-半嵌套出口，6-双向通行口
	private String entranceType;
//	/有车读卡 0 禁止,1,允许出入场
	private String isCarReadCard;
	// 无牌车通行 00,禁止 1，允许出入场,02 取卡入场，3 票入场，4-扫码入场
	private String noCarNoPass;
	// 月租车工作模式 00-自动，01-在线，02-受控
	private String supplyGateControl;
	// 道闸接口 0-电平，1-485接口，2-CAN接口
	private String roadGateInterface;
	// 道闸地感 0-禁止 1使能
	private String geteSense;
	// 主摄像机类型 0-无，1-大华，2-臻识，3-海康，4-华夏智信，5-信路威
	private String mainCameraType;
	// 副摄像机类型 0-无，1-大华，2-臻识，3-海康，4-华夏智信，5-信路威
	private String viceCameraType;
	// 尾摄像机类型 0-无，1-大华，2-臻识，3-海康，4-华夏智信，5-信路威
	private String lastCameraType;
	//主摄像机 触发类型
	private String mainCameraTrigger;
	// 副摄像机触发类型
	private String viceCameraTrigger;
	// 尾摄像机触发类型 
	private String lastCameraTrigger;
	
	
	// 禁止收费 0可能收费，1 不可收费
	private String outNoCharge;
	// 通道类型 0-综合通道，1-固定车道 2-临时通道
	private String channelType;
	// 固定卡多进多出 0 禁止，1允许
	private String fixedCardWantonly;
	// 月租车转临时车 0-禁止，1-4对应临时卡A-D，9-转成对应卡类
	private String monthToTempType;
	// 仅当月租车转临时车为禁止时有效，即配置为转临则按临时车处理
	private String monthOverDays;
	private String startValidity; // 车场有效开始日期
	private String endValidity; // 车场有效结束日期
	private String effectiveDays; // 车场有效天数
	private String ftpIP; // ftp服务器访问IP
	private String ftpUser; // ftp服务器访问用户名
	private String ftpPassWord; // ftp服务器访问用户名
	private String parkingNo; // 车场编号

	public String getTemporaryCarWorkingModel() {
		return temporaryCarWorkingModel;
	}

	public void setTemporaryCarWorkingModel(String temporaryCarWorkingModel) {
		this.temporaryCarWorkingModel = temporaryCarWorkingModel;
	}

	public String getStoredValueCarWorkingModel() {
		return storedValueCarWorkingModel;
	}

	public void setStoredValueCarWorkingModel(String storedValueCarWorkingModel) {
		this.storedValueCarWorkingModel = storedValueCarWorkingModel;
	}

	public String getVipCarWorkingModel() {
		return vipCarWorkingModel;
	}

	public void setVipCarWorkingModel(String vipCarWorkingModel) {
		this.vipCarWorkingModel = vipCarWorkingModel;
	}

	public String getEntranceType() {
		return entranceType;
	}

	public void setEntranceType(String entranceType) {
		this.entranceType = entranceType;
	}

	public String getIsCarReadCard() {
		return isCarReadCard;
	}

	public void setIsCarReadCard(String isCarReadCard) {
		this.isCarReadCard = isCarReadCard;
	}

	public String getNoCarNoPass() {
		return noCarNoPass;
	}

	public void setNoCarNoPass(String noCarNoPass) {
		this.noCarNoPass = noCarNoPass;
	}

	public String getSupplyGateControl() {
		return supplyGateControl;
	}

	public void setSupplyGateControl(String supplyGateControl) {
		this.supplyGateControl = supplyGateControl;
	}

	public String getRoadGateInterface() {
		return roadGateInterface;
	}

	public void setRoadGateInterface(String roadGateInterface) {
		this.roadGateInterface = roadGateInterface;
	}

	public String getGeteSense() {
		return geteSense;
	}

	public void setGeteSense(String geteSense) {
		this.geteSense = geteSense;
	}

	public String getMainCameraType() {
		return mainCameraType;
	}

	public void setMainCameraType(String mainCameraType) {
		this.mainCameraType = mainCameraType;
	}

	public String getViceCameraType() {
		return viceCameraType;
	}

	public void setViceCameraType(String viceCameraType) {
		this.viceCameraType = viceCameraType;
	}

	public String getLastCameraType() {
		return lastCameraType;
	}

	public void setLastCameraType(String lastCameraType) {
		this.lastCameraType = lastCameraType;
	}

	public String getOutNoCharge() {
		return outNoCharge;
	}

	public void setOutNoCharge(String outNoCharge) {
		this.outNoCharge = outNoCharge;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getFixedCardWantonly() {
		return fixedCardWantonly;
	}

	public void setFixedCardWantonly(String fixedCardWantonly) {
		this.fixedCardWantonly = fixedCardWantonly;
	}

	public String getMonthToTempType() {
		return monthToTempType;
	}

	public void setMonthToTempType(String monthToTempType) {
		this.monthToTempType = monthToTempType;
	}

	public String getMonthOverDays() {
		return monthOverDays;
	}

	public void setMonthOverDays(String monthOverDays) {
		this.monthOverDays = monthOverDays;
	}

	
	/**
	* monthlyCarWorkingModel.
	*
	* @return the monthlyCarWorkingModel
	* @since JDK 1.8
	*/
	public String getMonthlyCarWorkingModel() {
		return monthlyCarWorkingModel;
	}

	
	/**
	* monthlyCarWorkingModel.
	*
	* @param monthlyCarWorkingModel the monthlyCarWorkingModel to set
	* @since JDK 1.8
	*/
	public void setMonthlyCarWorkingModel(String monthlyCarWorkingModel) {
		this.monthlyCarWorkingModel = monthlyCarWorkingModel;
	}

	
	/**
	* startValidity.
	*
	* @return the startValidity
	* @since JDK 1.8
	*/
	public String getStartValidity() {
		return startValidity;
	}

	
	/**
	* startValidity.
	*
	* @param startValidity the startValidity to set
	* @since JDK 1.8
	*/
	public void setStartValidity(String startValidity) {
		this.startValidity = startValidity;
	}

	
	/**
	* endValidity.
	*
	* @return the endValidity
	* @since JDK 1.8
	*/
	public String getEndValidity() {
		return endValidity;
	}

	
	/**
	* endValidity.
	*
	* @param endValidity the endValidity to set
	* @since JDK 1.8
	*/
	public void setEndValidity(String endValidity) {
		this.endValidity = endValidity;
	}

	
	/**
	* effectiveDays.
	*
	* @return the effectiveDays
	* @since JDK 1.8
	*/
	public String getEffectiveDays() {
		return effectiveDays;
	}

	
	/**
	* effectiveDays.
	*
	* @param effectiveDays the effectiveDays to set
	* @since JDK 1.8
	*/
	public void setEffectiveDays(String effectiveDays) {
		this.effectiveDays = effectiveDays;
	}

	
	/**
	* ftpIP.
	*
	* @return the ftpIP
	* @since JDK 1.8
	*/
	public String getFtpIP() {
		return ftpIP;
	}

	
	/**
	* ftpIP.
	*
	* @param ftpIP the ftpIP to set
	* @since JDK 1.8
	*/
	public void setFtpIP(String ftpIP) {
		this.ftpIP = ftpIP;
	}

	
	/**
	* ftpUser.
	*
	* @return the ftpUser
	* @since JDK 1.8
	*/
	public String getFtpUser() {
		return ftpUser;
	}

	
	/**
	* ftpUser.
	*
	* @param ftpUser the ftpUser to set
	* @since JDK 1.8
	*/
	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	
	/**
	* ftpPassWord.
	*
	* @return the ftpPassWord
	* @since JDK 1.8
	*/
	public String getFtpPassWord() {
		return ftpPassWord;
	}

	
	/**
	* ftpPassWord.
	*
	* @param ftpPassWord the ftpPassWord to set
	* @since JDK 1.8
	*/
	public void setFtpPassWord(String ftpPassWord) {
		this.ftpPassWord = ftpPassWord;
	}

	
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

	
	/**
	* mainCameraTrigger.
	*
	* @return the mainCameraTrigger
	* @since JDK 1.8
	*/
	public String getMainCameraTrigger() {
		return mainCameraTrigger;
	}

	
	/**
	* mainCameraTrigger.
	*
	* @param mainCameraTrigger the mainCameraTrigger to set
	* @since JDK 1.8
	*/
	public void setMainCameraTrigger(String mainCameraTrigger) {
		this.mainCameraTrigger = mainCameraTrigger;
	}

	
	/**
	* viceCameraTrigger.
	*
	* @return the viceCameraTrigger
	* @since JDK 1.8
	*/
	public String getViceCameraTrigger() {
		return viceCameraTrigger;
	}

	
	/**
	* viceCameraTrigger.
	*
	* @param viceCameraTrigger the viceCameraTrigger to set
	* @since JDK 1.8
	*/
	public void setViceCameraTrigger(String viceCameraTrigger) {
		this.viceCameraTrigger = viceCameraTrigger;
	}

	
	/**
	* lastCameraTrigger.
	*
	* @return the lastCameraTrigger
	* @since JDK 1.8
	*/
	public String getLastCameraTrigger() {
		return lastCameraTrigger;
	}

	
	/**
	* lastCameraTrigger.
	*
	* @param lastCameraTrigger the lastCameraTrigger to set
	* @since JDK 1.8
	*/
	public void setLastCameraTrigger(String lastCameraTrigger) {
		this.lastCameraTrigger = lastCameraTrigger;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}
	
	

}
