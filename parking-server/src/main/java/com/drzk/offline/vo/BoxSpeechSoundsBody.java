package com.drzk.offline.vo;

import java.io.Serializable;
import java.util.Date;

public class BoxSpeechSoundsBody implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6487680911577577962L;
	private String uId;
	private String controlIP;//控制器IP
	private String equipmentID;//硬件设备编号
	private String carNo;//车牌
	private Date inDate;//入场时间
	private Date outDate;//出场时间
	private Integer payMoney;// 费用
		
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	/**
	 * 控制器IP
	 * @param controlIP
	 */
	public void setControlIP(String controlIP)
	{
		this.controlIP = controlIP;
	}
	/**
	 * 控制器IP
	 * @return
	 */
	public String getControlIP()
	{
		return controlIP;
	}
	
	/**
	 * 硬件设备编号
	 * @param equipmentID
	 */
	public void setEquipmentID(String equipmentID)
	{
		this.equipmentID=equipmentID;
	}
	/**
	 * 硬件设备编号
	 * @return
	 */
	public String getEquipmentID()
	{
		return equipmentID;
	}
	public Integer getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Integer payMoney) {
		this.payMoney = payMoney;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
}
