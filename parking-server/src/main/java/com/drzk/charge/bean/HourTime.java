package com.drzk.charge.bean;



public class HourTime {
	public int getHuorName() {
		return huorName;
	}
	public void setHuorName(int huorName) {
		this.huorName = huorName;
	} 
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	//几小时
	private int huorName;
	//金额
	private double charge;
}
