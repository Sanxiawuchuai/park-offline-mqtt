package com.drzk.offline.vo;

/** 计费返回实体 */
public class GetPayChargeReturn extends SuperBody
{
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
	
}
