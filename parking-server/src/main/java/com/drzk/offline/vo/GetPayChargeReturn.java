package com.drzk.offline.vo;

import java.io.Serializable;

/** 计费返回实体 */
public class GetPayChargeReturn implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4221060484282577978L;
	private String uId;
	private String accMoney;//应收金额
	private String payMoney;//实收金额
	private String disMoney;//优惠金额
	
	public String getAccMoney() {
		return accMoney;
	}
	public void setAccMoney(String accMoney) {
		this.accMoney = accMoney;
	}
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	public String getDisMoney() {
		return disMoney;
	}
	public void setDisMoney(String disMoney) {
		this.disMoney = disMoney;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
}
