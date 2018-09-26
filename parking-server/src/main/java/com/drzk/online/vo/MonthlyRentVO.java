package com.drzk.online.vo;

/**
 * @author tf
 * 月租费率
 */
public class MonthlyRentVO {
	private String typename;// 卡名称
    private Integer stype;//续费类型     0-1月  1-3月   2-半年  3-一年
    private Integer charge;//续费金额
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public Integer getStype() {
		return stype;
	}
	public void setStype(Integer stype) {
		this.stype = stype;
	}
	public Integer getCharge() {
		return charge;
	}
	public void setCharge(Integer charge) {
		this.charge = charge;
	}
}
