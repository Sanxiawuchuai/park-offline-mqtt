package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 异常入场信息
 */
public class AbnormalAdmissionVO implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -78579750069823643L;
	private Integer carInExceptionId; //编号
     private String cardId;//卡ID（7Byte）
     private String cardNo;//卡编号
     private String empName;//车主名称
     private Integer cardType;//卡类型
     private String cardTypeName; //卡类型名称   
     private String carNo; //车牌号码
     private String bInTime; //原有入场时间   
     private String inTime;//入场时间
     private String inControlName; //控制器名称            
     private String inUserName; //入场操作员
     private Integer inWay;//入场方式 0;表示正常出场，1，校正入场 ，2，手工入场 
     private String  inWayName;//入场方式 0;表示正常出场，1，校正入场 ，2，手工入场 
     private String  inPic;//入场图片地址
	public Integer getCarInExceptionId() {
		return carInExceptionId;
	}
	public void setCarInExceptionId(Integer carInExceptionId) {
		this.carInExceptionId = carInExceptionId;
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getCardTypeName() {
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getbInTime() {
		return bInTime;
	}
	public void setbInTime(String bInTime) {
		this.bInTime = bInTime;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getInControlName() {
		return inControlName;
	}
	public void setInControlName(String inControlName) {
		this.inControlName = inControlName;
	}
	public String getInUserName() {
		return inUserName;
	}
	public void setInUserName(String inUserName) {
		this.inUserName = inUserName;
	}
	public Integer getInWay() {
		return inWay;
	}
	public void setInWay(Integer inWay) {
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
}
