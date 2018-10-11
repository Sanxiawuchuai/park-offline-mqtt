package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 车主用户
 */
public class OwnerUserVO implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 8033796599805144753L;
	private Integer perId ; //人员表编号
     private String carNo ;//车牌号码
     private String tel ; //手机号
     private Integer cardTypeId ;//卡类型
     private String perName ;//姓名(唯一)
     private String cardType; //卡类名称
     private String cardStartDate  ;//有效起始日期
     private String  cardEndDate ; //有效终止日期
     private Integer cardStatus ;//状态
	public Integer getPerId() {
		return perId;
	}
	public void setPerId(Integer perId) {
		this.perId = perId;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getCardTypeId() {
		return cardTypeId;
	}
	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}
	public String getPerName() {
		return perName;
	}
	public void setPerName(String perName) {
		this.perName = perName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardStartDate() {
		return cardStartDate;
	}
	public void setCardStartDate(String cardStartDate) {
		this.cardStartDate = cardStartDate;
	}
	public String getCardEndDate() {
		return cardEndDate;
	}
	public void setCardEndDate(String cardEndDate) {
		this.cardEndDate = cardEndDate;
	}
	public Integer getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}
}
