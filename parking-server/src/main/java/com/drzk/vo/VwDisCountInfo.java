package com.drzk.vo;

import java.util.Date;

public class VwDisCountInfo {
    private Integer id;

    private Byte outType;

    private String discountId;

    private String cardId;

    private Date outTime;

    private Date discountTime;

    private Float disAmount;

    private Byte disType;

    private String onlineId;

    private String puid;

    private Byte isLoad;

    private Byte delFrag;

    private String carNo;

    private Date inTime;

    private String discountName;

    private Byte discountType;

    private Float discountAmount;

    private Integer disInfoId;

    private String disInfoPuid;

    private Integer eqId;

    private String eqName;

    private String clientNo;

    private String euid;

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

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid == null ? null : puid.trim();
    }

    public Byte getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(Byte isLoad) {
        this.isLoad = isLoad;
    }

    public Byte getDelFrag() {
        return delFrag;
    }

    public void setDelFrag(Byte delFrag) {
        this.delFrag = delFrag;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName == null ? null : discountName.trim();
    }

    public Byte getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Byte discountType) {
        this.discountType = discountType;
    }

    public Float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getDisInfoId() {
        return disInfoId;
    }

    public void setDisInfoId(Integer disInfoId) {
        this.disInfoId = disInfoId;
    }

    public String getDisInfoPuid() {
        return disInfoPuid;
    }

    public void setDisInfoPuid(String disInfoPuid) {
        this.disInfoPuid = disInfoPuid == null ? null : disInfoPuid.trim();
    }

    public Integer getEqId() {
        return eqId;
    }

    public void setEqId(Integer eqId) {
        this.eqId = eqId;
    }

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName == null ? null : eqName.trim();
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo == null ? null : clientNo.trim();
    }

    public String getEuid() {
        return euid;
    }

    public void setEuid(String euid) {
        this.euid = euid == null ? null : euid.trim();
    }
}