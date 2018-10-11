package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class VwParkCarcharge implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8273541625925422795L;

	private Integer boxId;

    private String cardId;

    private String cardNo;

    private String carNo;

    private Byte cardType;

    private Date inTime;

    private Date outTime;

    private String userName;

    private Float payCharge;

    private Float accountCharge;

    private Double disAmount;

    private Double freeAmount;

    private String cardTypeName;

    private String freeName;

    private String chargeType;

    private String inPic;

    private String outPic;

    private String payType;

    private String orderNum;

    private Float balanceMoney;

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Byte getCardType() {
        return cardType;
    }

    public void setCardType(Byte cardType) {
        this.cardType = cardType;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Float getPayCharge() {
        return payCharge;
    }

    public void setPayCharge(Float payCharge) {
        this.payCharge = payCharge;
    }

    public Float getAccountCharge() {
        return accountCharge;
    }

    public void setAccountCharge(Float accountCharge) {
        this.accountCharge = accountCharge;
    }

    public Double getDisAmount() {
        return disAmount;
    }

    public void setDisAmount(Double disAmount) {
        this.disAmount = disAmount;
    }

    public Double getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(Double freeAmount) {
        this.freeAmount = freeAmount;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
    }

    public String getFreeName() {
        return freeName;
    }

    public void setFreeName(String freeName) {
        this.freeName = freeName == null ? null : freeName.trim();
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType == null ? null : chargeType.trim();
    }

    public String getInPic() {
        return inPic;
    }

    public void setInPic(String inPic) {
        this.inPic = inPic == null ? null : inPic.trim();
    }

    public String getOutPic() {
        return outPic;
    }

    public void setOutPic(String outPic) {
        this.outPic = outPic == null ? null : outPic.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Float getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(Float balanceMoney) {
        this.balanceMoney = balanceMoney;
    }
}