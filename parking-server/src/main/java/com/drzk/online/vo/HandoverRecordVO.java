package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 交班记录
 */
public class HandoverRecordVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1690263051110443175L;
	private Integer sumUserId; // 编号
	private Integer boxId;// 岗亭编号
	private String loginDate;// 登入时间
	private String reliefDate;// 交班时间
	private String userName;// 交班操作员
	private String reUserName;// 接班操作员
	private Integer handGate; // 手动开闸
	private Integer computerGate; // 电脑开闸
	private Integer tempCardIn; // 临时卡入场张数
	private Integer tempCardOut;// 临时卡出场张数
	private Integer monthCardIn; // 月租车入场数
	private Integer monthCardOut;// 月租车出场数
	private Integer storedCardNum;// 储值卡数
	private Double storedCardMoney;// 储值卡收费金额
	private Integer tempFree;// 临免卡张数
	private Integer freeCarNo;// 免费卡数
	private Double freeCharge; // 免费金额
	private Integer discountNum;// 折扣张数
	private Double discountCharge;// 折扣金额
	private Double posMoney;// POS消费（电商抵扣）
	private Double totalCharge; // 现金金额
	private Integer tNominal;// 工本费
	private Double account;// 应收金额
	private String description;// 备注
	public Integer getSumUserId() {
		return sumUserId;
	}
	public void setSumUserId(Integer sumUserId) {
		this.sumUserId = sumUserId;
	}
	public Integer getBoxId() {
		return boxId;
	}
	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getReliefDate() {
		return reliefDate;
	}
	public void setReliefDate(String reliefDate) {
		this.reliefDate = reliefDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReUserName() {
		return reUserName;
	}
	public void setReUserName(String reUserName) {
		this.reUserName = reUserName;
	}
	public Integer getHandGate() {
		return handGate;
	}
	public void setHandGate(Integer handGate) {
		this.handGate = handGate;
	}
	public Integer getComputerGate() {
		return computerGate;
	}
	public void setComputerGate(Integer computerGate) {
		this.computerGate = computerGate;
	}
	public Integer getTempCardIn() {
		return tempCardIn;
	}
	public void setTempCardIn(Integer tempCardIn) {
		this.tempCardIn = tempCardIn;
	}
	public Integer getTempCardOut() {
		return tempCardOut;
	}
	public void setTempCardOut(Integer tempCardOut) {
		this.tempCardOut = tempCardOut;
	}
	public Integer getMonthCardIn() {
		return monthCardIn;
	}
	public void setMonthCardIn(Integer monthCardIn) {
		this.monthCardIn = monthCardIn;
	}
	public Integer getMonthCardOut() {
		return monthCardOut;
	}
	public void setMonthCardOut(Integer monthCardOut) {
		this.monthCardOut = monthCardOut;
	}
	public Integer getStoredCardNum() {
		return storedCardNum;
	}
	public void setStoredCardNum(Integer storedCardNum) {
		this.storedCardNum = storedCardNum;
	}
	public Double getStoredCardMoney() {
		return storedCardMoney;
	}
	public void setStoredCardMoney(Double storedCardMoney) {
		this.storedCardMoney = storedCardMoney;
	}
	public Integer getTempFree() {
		return tempFree;
	}
	public void setTempFree(Integer tempFree) {
		this.tempFree = tempFree;
	}
	public Integer getFreeCarNo() {
		return freeCarNo;
	}
	public void setFreeCarNo(Integer freeCarNo) {
		this.freeCarNo = freeCarNo;
	}
	public Double getFreeCharge() {
		return freeCharge;
	}
	public void setFreeCharge(Double freeCharge) {
		this.freeCharge = freeCharge;
	}
	public Integer getDiscountNum() {
		return discountNum;
	}
	public void setDiscountNum(Integer discountNum) {
		this.discountNum = discountNum;
	}
	public Double getDiscountCharge() {
		return discountCharge;
	}
	public void setDiscountCharge(Double discountCharge) {
		this.discountCharge = discountCharge;
	}
	public Double getPosMoney() {
		return posMoney;
	}
	public void setPosMoney(Double posMoney) {
		this.posMoney = posMoney;
	}
	public Double getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(Double totalCharge) {
		this.totalCharge = totalCharge;
	}
	public Integer gettNominal() {
		return tNominal;
	}
	public void settNominal(Integer tNominal) {
		this.tNominal = tNominal;
	}
	public Double getAccount() {
		return account;
	}
	public void setAccount(Double account) {
		this.account = account;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
