package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf 
 * 出场纪录
 */
public class ParkCarOut implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 106473763656202159L;
	private Integer carOutId ; //出场表ID
	private String inMachNo; //控制器编号
	private String entrance ;//入场控制器名称
	private String outMachNo ; //出口控制器编号
	private String appearances ;//出场控制器名称
	private String cardId ;//卡ID（7Byte）
	private String cardNo ;//卡号
	private String carNo ;//车牌号码
	private String nextCarNo ;//从车牌
	private String correctCarNo ;//矫正车牌
	private String cFlag ;//卡介质(0为IC，1为ID，2IC做ID，3; 4车牌)
	private String contactName ;//车主名称      
	private String carNoType ;//车牌类型
	private String cardType ; //卡类名称
	private String cardTypeName ; //卡类名称
	private String parkingType ;//停车类型
	private String freeType ; //免费类型
	private String inTime ;//入场时间
	private String inUserName ; //入场操作员
	private String inWay ;//入场方式 0正常入场，1校正入场 ，2手工入场 3;扫码入场;4手持机入场
	private String inWayName ;//入场方式 inWay：0正常入场，1校正入场 ，2手工入场 3;扫码入场;4手持机入场
	private String inPic ;//入场图片路径
	private String centralTime ;//定点收费时间
	private String outTime ;//出场时间
	private String outPic ;//出场图片路径
	private String outUserName ; //收费操作员
	private String payType ;//支付类型 0，现金支付，1微信支付;2，支付宝支付;3;银联闪付，4，交通卡支付，5;自助缴费机;6，其它
	private String payCharge ; //收费金额
	private String balanceMoney ;//卡上金额
	private String discountNo ;//打折机号
	private String typeId ;//模式ID
	private String discountTime ;//折扣时间
	private String disAmount ;//折扣金额
	private String accountCharge ;//应收金额
	private String isOut ;//是否出场（中央收费时为0，出场后更新为1）
	private String unusualMemo ; //异常原因（1车闸故障2卡遗失等等
	private String outWay ;//出场方式 0;表示正常出场，1，手工放行，2，异常放行;4手持机放行
	private String outWayName ; //出场方式
	private String inControlIp ;//入场IP
	private String outCcontrolIp ;//出场IP
	private String description;//描述
	public Integer getCarOutId() {
		return carOutId;
	}
	public void setCarOutId(Integer carOutId) {
		this.carOutId = carOutId;
	}
	public String getInMachNo() {
		return inMachNo;
	}
	public void setInMachNo(String inMachNo) {
		this.inMachNo = inMachNo;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	public String getOutMachNo() {
		return outMachNo;
	}
	public void setOutMachNo(String outMachNo) {
		this.outMachNo = outMachNo;
	}
	public String getAppearances() {
		return appearances;
	}
	public void setAppearances(String appearances) {
		this.appearances = appearances;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getNextCarNo() {
		return nextCarNo;
	}
	public void setNextCarNo(String nextCarNo) {
		this.nextCarNo = nextCarNo;
	}
	public String getCorrectCarNo() {
		return correctCarNo;
	}
	public void setCorrectCarNo(String correctCarNo) {
		this.correctCarNo = correctCarNo;
	}
	public String getcFlag() {
		return cFlag;
	}
	public void setcFlag(String cFlag) {
		this.cFlag = cFlag;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getCarNoType() {
		return carNoType;
	}
	public void setCarNoType(String carNoType) {
		this.carNoType = carNoType;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardTypeName() {
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
	public String getParkingType() {
		return parkingType;
	}
	public void setParkingType(String parkingType) {
		this.parkingType = parkingType;
	}
	public String getFreeType() {
		return freeType;
	}
	public void setFreeType(String freeType) {
		this.freeType = freeType;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getInUserName() {
		return inUserName;
	}
	public void setInUserName(String inUserName) {
		this.inUserName = inUserName;
	}
	public String getInWay() {
		return inWay;
	}
	public void setInWay(String inWay) {
		this.inWay = inWay;
	}
	public String getInWayName() {
		return inWayName;
	}
	public void setInWayName(String inWayName) {
		this.inWayName = inWayName;
	}
	public String getInPic() {
		return inPic;
	}
	public void setInPic(String inPic) {
		this.inPic = inPic;
	}
	public String getCentralTime() {
		return centralTime;
	}
	public void setCentralTime(String centralTime) {
		this.centralTime = centralTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getOutPic() {
		return outPic;
	}
	public void setOutPic(String outPic) {
		this.outPic = outPic;
	}
	public String getOutUserName() {
		return outUserName;
	}
	public void setOutUserName(String outUserName) {
		this.outUserName = outUserName;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayCharge() {
		return payCharge;
	}
	public void setPayCharge(String payCharge) {
		this.payCharge = payCharge;
	}
	public String getBalanceMoney() {
		return balanceMoney;
	}
	public void setBalanceMoney(String balanceMoney) {
		this.balanceMoney = balanceMoney;
	}
	public String getDiscountNo() {
		return discountNo;
	}
	public void setDiscountNo(String discountNo) {
		this.discountNo = discountNo;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getDiscountTime() {
		return discountTime;
	}
	public void setDiscountTime(String discountTime) {
		this.discountTime = discountTime;
	}
	public String getDisAmount() {
		return disAmount;
	}
	public void setDisAmount(String disAmount) {
		this.disAmount = disAmount;
	}
	public String getAccountCharge() {
		return accountCharge;
	}
	public void setAccountCharge(String accountCharge) {
		this.accountCharge = accountCharge;
	}
	public String getIsOut() {
		return isOut;
	}
	public void setIsOut(String isOut) {
		this.isOut = isOut;
	}
	public String getUnusualMemo() {
		return unusualMemo;
	}
	public void setUnusualMemo(String unusualMemo) {
		this.unusualMemo = unusualMemo;
	}
	public String getOutWay() {
		return outWay;
	}
	public void setOutWay(String outWay) {
		this.outWay = outWay;
	}
	public String getOutWayName() {
		return outWayName;
	}
	public void setOutWayName(String outWayName) {
		this.outWayName = outWayName;
	}
	public String getInControlIp() {
		return inControlIp;
	}
	public void setInControlIp(String inControlIp) {
		this.inControlIp = inControlIp;
	}
	public String getOutCcontrolIp() {
		return outCcontrolIp;
	}
	public void setOutCcontrolIp(String outCcontrolIp) {
		this.outCcontrolIp = outCcontrolIp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
