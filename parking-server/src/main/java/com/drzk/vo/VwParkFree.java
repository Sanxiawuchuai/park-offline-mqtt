package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class VwParkFree implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7883283389486667254L;

	private Byte cardType;

    private String cardId;

    private String cardNo;

    private String cardTypeName;

    private String carNo;

    private Double freeAmount;

    private Date inTime;

    private Date payChargeTime;

    private String payUserName;

    private String freeName;

    private Integer freeType;

    private String credentialsPic;

    public Byte getCardType() {
        return cardType;
    }

    public void setCardType(Byte cardType) {
        this.cardType = cardType;
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

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Double getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(Double freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getPayChargeTime() {
        return payChargeTime;
    }

    public void setPayChargeTime(Date payChargeTime) {
        this.payChargeTime = payChargeTime;
    }

    public String getPayUserName() {
        return payUserName;
    }

    public void setPayUserName(String payUserName) {
        this.payUserName = payUserName == null ? null : payUserName.trim();
    }

    public String getFreeName() {
        return freeName;
    }

    public void setFreeName(String freeName) {
        this.freeName = freeName == null ? null : freeName.trim();
    }

    public Integer getFreeType() {
        return freeType;
    }

    public void setFreeType(Integer freeType) {
        this.freeType = freeType;
    }

    public String getCredentialsPic() {
        return credentialsPic;
    }

    public void setCredentialsPic(String credentialsPic) {
        this.credentialsPic = credentialsPic == null ? null : credentialsPic.trim();
    }
}