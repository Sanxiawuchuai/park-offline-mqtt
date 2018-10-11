package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 中央缴费记录
 */
public class PaymentRecordVO implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 6333888502845933314L;
	private Integer centerPaymentId;//中央缴费编号
	 private Integer inMachNo;//入口机号
	 private String entrance;//入口名称
	 private String cardId;//卡ID（7Byte）
	 private String cardNo;//卡编号
	 private String carNo;//车牌号
	 private Integer  cFlag;//卡介质(0为IC，1为ID，2IC做ID，3)
	 private String  cardType;//卡类型
	 private String cardTypeName;//卡类型名称
	 private Double  freeType;//免费类型
	 private String inTime;//入场时间
	 private String inUserName;//入场操作员       
     private String  overTimeS;//逾时开始时间
     private String payChargeTime;//收费时间
     private Integer payType;//收费类型（0出口收费1定点收费）
     private String transactionId;//交易编号
     private Double payCharge;//收费金额
     private Integer discountNo;//打折机号
     private Integer typeId;//模式ID
     private String discountTime;//折扣时间
     private Double disAmount;//折扣金额
     private String accountCharge;//应收金额
     private Integer unusualMemo;//异常原因（1车闸故障2卡遗失等等）
     private String description;//备注
	public Integer getCenterPaymentId() {
		return centerPaymentId;
	}
	public void setCenterPaymentId(Integer centerPaymentId) {
		this.centerPaymentId = centerPaymentId;
	}
	public Integer getInMachNo() {
		return inMachNo;
	}
	public void setInMachNo(Integer inMachNo) {
		this.inMachNo = inMachNo;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
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
	public Integer getcFlag() {
		return cFlag;
	}
	public void setcFlag(Integer cFlag) {
		this.cFlag = cFlag;
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
	public Double getFreeType() {
		return freeType;
	}
	public void setFreeType(Double freeType) {
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
	public String getOverTimeS() {
		return overTimeS;
	}
	public void setOverTimeS(String overTimeS) {
		this.overTimeS = overTimeS;
	}
	public String getPayChargeTime() {
		return payChargeTime;
	}
	public void setPayChargeTime(String payChargeTime) {
		this.payChargeTime = payChargeTime;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Double getPayCharge() {
		return payCharge;
	}
	public void setPayCharge(Double payCharge) {
		this.payCharge = payCharge;
	}
	public Integer getDiscountNo() {
		return discountNo;
	}
	public void setDiscountNo(Integer discountNo) {
		this.discountNo = discountNo;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getDiscountTime() {
		return discountTime;
	}
	public void setDiscountTime(String discountTime) {
		this.discountTime = discountTime;
	}
	public Double getDisAmount() {
		return disAmount;
	}
	public void setDisAmount(Double disAmount) {
		this.disAmount = disAmount;
	}
	public String getAccountCharge() {
		return accountCharge;
	}
	public void setAccountCharge(String accountCharge) {
		this.accountCharge = accountCharge;
	}
	public Integer getUnusualMemo() {
		return unusualMemo;
	}
	public void setUnusualMemo(Integer unusualMemo) {
		this.unusualMemo = unusualMemo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
