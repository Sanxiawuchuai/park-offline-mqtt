package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf 商户管理
 */
public class MerchantManagementVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String businessName;//商户名称
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}


}
