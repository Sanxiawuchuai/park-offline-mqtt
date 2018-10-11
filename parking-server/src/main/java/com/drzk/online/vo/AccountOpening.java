package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf 
 * 车主开户
 */
public class AccountOpening implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1686068516099866240L;
	private Integer cardOperationId; // 编号
	private String carNo;// 车牌号码
	private String cardNo; // 卡号
	private String cardId;// 卡ID
	private Integer cardTypeID;// 卡类型ID
	private String cardTypeName; // 卡类名称
	private String contactID;// 车主ID
	private String contactName; // 车主名称
	private String contactPhone;// 手机号
	private String address; // 地址
	private String frontDate;// 前结束日期（延期用）
	private String newStartDate; // 新起始日期（延期用
	private String newEndDate;// 新终止日期（延期用）
	private Double rechargeMoney;// 发生前余额（储值卡用）
	private Double balanceMoney;// 发生金额
	private Double deposit;// 押金
	private Integer payType; // 付款方式(0现金1银联闪付2微信3支付宝)
	private String transactionNumber;// 云交易记录编号
	private Integer userDate;// 操作时间
	private String userName; // 操作员
	private Integer operationType;// 操作类型（0卡发行1卡延期2挂失3解挂4补发5退款6销户）
	private String description;// 备注
	public Integer getCardOperationId() {
		return cardOperationId;
	}
	public void setCardOperationId(Integer cardOperationId) {
		this.cardOperationId = cardOperationId;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public Integer getCardTypeID() {
		return cardTypeID;
	}
	public void setCardTypeID(Integer cardTypeID) {
		this.cardTypeID = cardTypeID;
	}
	public String getCardTypeName() {
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
	public String getContactID() {
		return contactID;
	}
	public void setContactID(String contactID) {
		this.contactID = contactID;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFrontDate() {
		return frontDate;
	}
	public void setFrontDate(String frontDate) {
		this.frontDate = frontDate;
	}
	public String getNewStartDate() {
		return newStartDate;
	}
	public void setNewStartDate(String newStartDate) {
		this.newStartDate = newStartDate;
	}
	public String getNewEndDate() {
		return newEndDate;
	}
	public void setNewEndDate(String newEndDate) {
		this.newEndDate = newEndDate;
	}
	public Double getRechargeMoney() {
		return rechargeMoney;
	}
	public void setRechargeMoney(Double rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	public Double getBalanceMoney() {
		return balanceMoney;
	}
	public void setBalanceMoney(Double balanceMoney) {
		this.balanceMoney = balanceMoney;
	}
	public Double getDeposit() {
		return deposit;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public Integer getUserDate() {
		return userDate;
	}
	public void setUserDate(Integer userDate) {
		this.userDate = userDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
