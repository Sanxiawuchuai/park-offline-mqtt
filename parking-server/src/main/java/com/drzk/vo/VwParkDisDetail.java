package com.drzk.vo;

import java.util.Date;

public class VwParkDisDetail {
    private Integer id;

    private Byte outType;

    private String discountId;

    private String cardId;

    private Date outTime;

    private Date discountTime;

    private Float disAmount;

    private Byte disType;

    private String onlineId;

    private Date inTime;

    private String cardNo;

    private String inUserName;

    private String outUserName;

    private Integer eqid;

    private String discountName;

    private String memo;

    private String disTypeName;

    private Float accountCharge;

    private Float payCharge;

    private String eqName;

    private String carNo;

    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getOutType() {
        return outType;
    }

    public void setOutType(Byte outType) {
        this.outType = outType;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getDiscountTime() {
        return discountTime;
    }

    public void setDiscountTime(Date discountTime) {
        this.discountTime = discountTime;
    }

    public Float getDisAmount() {
        return disAmount;
    }

    public void setDisAmount(Float disAmount) {
        this.disAmount = disAmount;
    }

    public Byte getDisType() {
        return disType;
    }

    public void setDisType(Byte disType) {
        this.disType = disType;
    }

    public String getOnlineId() {
        return onlineId;
    }

    public void setOnlineId(String onlineId) {
        this.onlineId = onlineId == null ? null : onlineId.trim();
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getInUserName() {
        return inUserName;
    }

    public void setInUserName(String inUserName) {
        this.inUserName = inUserName == null ? null : inUserName.trim();
    }

    public String getOutUserName() {
        return outUserName;
    }

    public void setOutUserName(String outUserName) {
        this.outUserName = outUserName == null ? null : outUserName.trim();
    }

    public Integer getEqid() {
        return eqid;
    }

    public void setEqid(Integer eqid) {
        this.eqid = eqid;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName == null ? null : discountName.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getDisTypeName() {
        return disTypeName;
    }

    public void setDisTypeName(String disTypeName) {
        this.disTypeName = disTypeName == null ? null : disTypeName.trim();
    }

    public Float getAccountCharge() {
        return accountCharge;
    }

    public void setAccountCharge(Float accountCharge) {
        this.accountCharge = accountCharge;
    }

    public Float getPayCharge() {
        return payCharge;
    }

    public void setPayCharge(Float payCharge) {
        this.payCharge = payCharge;
    }

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName == null ? null : eqName.trim();
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}