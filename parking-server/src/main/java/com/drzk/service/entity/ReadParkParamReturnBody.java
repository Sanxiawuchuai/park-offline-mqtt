package com.drzk.service.entity;
//2.10. 读取车场系统参数返回实体
public class ReadParkParamReturnBody  extends SuperBody{
	private String temporaryCarWorkingModel;//临时车工作模式 00-自动，01-确认
	private String storedValueCarWorkingModel;//储值车工作模式 00-自动，01-确认
	private String vipCarWorkingModel;//免费车工作模式 00-自动， 01-在线
	private String entranceType;//出入口类型 0-标准入口，1-标准出口，2-嵌套入口，3-嵌套出口，4-半嵌套入口，5-半嵌套出口，6-双向通行口
	private String isCarReadCard;//有车读卡 0 禁止,1,允许出入场
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
	public String getqRCode() {
		return qRCode;
	}
	public void setqRCode(String qRCode) {
		this.qRCode = qRCode;
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
	private String noCarNoPass;//无牌车通行 00,禁止 1，允许出入场,02 取卡入场，3 票入场，4-扫码入场
	private String qRCode;//月租车工作模式 00-自动，01-在线，02-受控
	private String supplyGateControl;//补闸控制 0-禁止，1-补开闸，2-补关闸，3-补开关闸
	private String roadGateInterface;//道闸接口 0-电平，1-485接口，2-CAN接口
	private String geteSense;//道闸地感 0-禁止 1使能
	private String mainCameraType;//主摄像机类型 0-无，1-大华，2-臻识，3-海康，4-华夏智信，5-信路威
	private String viceCameraType;//副摄像机类型 0-无，1-大华，2-臻识，3-海康，4-华夏智信，5-信路威
	private String lastCameraType;//尾摄像机类型 0-无，1-大华，2-臻识，3-海康，4-华夏智信，5-信路威
	private String outNoCharge;//禁止收费 0可能收费，1 不可收费
	private String channelType;//通道类型 0-综合通道，1-固定车道 2-临时通道
	private String fixedCardWantonly;//固定卡多进多出 0 禁止，1允许
	private String monthToTempType;//月租车转临时车 0-禁止，1-4对应临时卡A-D，9-转成对应卡类
	private String monthOverDays;//仅当月租车转临时车为禁止时有效，即配置为转临则按临时车处理
}
