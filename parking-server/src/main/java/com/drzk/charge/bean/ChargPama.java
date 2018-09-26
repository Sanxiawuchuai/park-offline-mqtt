package com.drzk.charge.bean;

import java.util.Date;

public class ChargPama {

	//入场时间
	private Date inTime;
	//出场时间
	private Date outTime;
	//卡类型
	private int cardType;

	public int getCardType() {
		return cardType;
	}
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date date) {
		this.inTime = date;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

}
