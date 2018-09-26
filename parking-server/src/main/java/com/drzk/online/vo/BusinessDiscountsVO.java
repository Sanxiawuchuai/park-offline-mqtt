package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 折扣
 */
public class BusinessDiscountsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TABLE="park_discount";
	
	private String businessName;//商家名称
	private String businessId;//商家Id
	private String disconutType;//1免金额，2免时间，折扣率，金额
	private String disconutName;//打折名称
	private String disconutNo;//折扣编号
	private String disconutValue;//折扣值
	private String disconutRmark;//折扣备注
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getDisconutType() {
		return disconutType;
	}
	public void setDisconutType(String disconutType) {
		this.disconutType = disconutType;
	}
	public String getDisconutName() {
		return disconutName;
	}
	public void setDisconutName(String disconutName) {
		this.disconutName = disconutName;
	}
	public String getDisconutNo() {
		return disconutNo;
	}
	public void setDisconutNo(String disconutNo) {
		this.disconutNo = disconutNo;
	}
	public String getDisconutValue() {
		return disconutValue;
	}
	public void setDisconutValue(String disconutValue) {
		this.disconutValue = disconutValue;
	}
	public String getDisconutRmark() {
		return disconutRmark;
	}
	public void setDisconutRmark(String disconutRmark) {
		this.disconutRmark = disconutRmark;
	}
	public static String getTable() {
		return TABLE;
	}
	
}
