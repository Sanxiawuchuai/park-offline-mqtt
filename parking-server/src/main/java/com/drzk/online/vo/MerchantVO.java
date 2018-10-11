package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 优惠券商户信息
 */
public class MerchantVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5542498325725968810L;
	private Integer EqID ; //商户编号
    private String EqName ;//商户名称
    private String ClientNO ;//线上编号
    private String description; //描述
	public Integer getEqID() {
		return EqID;
	}
	public void setEqID(Integer eqID) {
		EqID = eqID;
	}
	public String getEqName() {
		return EqName;
	}
	public void setEqName(String eqName) {
		EqName = eqName;
	}
	public String getClientNO() {
		return ClientNO;
	}
	public void setClientNO(String clientNO) {
		ClientNO = clientNO;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
