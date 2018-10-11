package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 异常出场
 */
public class AbnormalAppearanceVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2090126975397716900L;
	private Integer id;//编号
    private String areaName;//小区名称
    private String carNo;//车牌号码
    private String contactName;//车主姓名
    private String cardTypeName;//卡类型名称
    private Double payCharge;//金额
    private String inTime;//入场时间
    private String entrance;//入口岗亭名称
    private String outTime;//出场时间
    private String appearances;//出口岗亭名称
    private String inUserName;//入场操业员
    private String outUserName;//出场操业员
    private String outWayName;//出场开闸方式
    private String inPichttp;//入场图片路径
    private String outPichttp;//出场图片路径
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getCardTypeName() {
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
	public Double getPayCharge() {
		return payCharge;
	}
	public void setPayCharge(Double payCharge) {
		this.payCharge = payCharge;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getAppearances() {
		return appearances;
	}
	public void setAppearances(String appearances) {
		this.appearances = appearances;
	}
	public String getInUserName() {
		return inUserName;
	}
	public void setInUserName(String inUserName) {
		this.inUserName = inUserName;
	}
	public String getOutUserName() {
		return outUserName;
	}
	public void setOutUserName(String outUserName) {
		this.outUserName = outUserName;
	}
	public String getOutWayName() {
		return outWayName;
	}
	public void setOutWayName(String outWayName) {
		this.outWayName = outWayName;
	}
	public String getInPichttp() {
		return inPichttp;
	}
	public void setInPichttp(String inPichttp) {
		this.inPichttp = inPichttp;
	}
	public String getOutPichttp() {
		return outPichttp;
	}
	public void setOutPichttp(String outPichttp) {
		this.outPichttp = outPichttp;
	}
}
