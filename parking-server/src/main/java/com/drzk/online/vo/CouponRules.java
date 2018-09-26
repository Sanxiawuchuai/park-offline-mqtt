package com.drzk.online.vo;

/**
 * @author tf
 * 优惠券规则
 */
public class CouponRules extends SuperBody {

	private String businessId; //商户编号
    private String businessName;//商户名称
    private String discountName ;//折扣名称
    private Integer disType; //折扣模式 1全免(0)  2 折扣率(<100) 3免时间(<65535) 4免金额(<65535)
    private Double disAmount;//折扣金额
	private String description;

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

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public Integer getDisType() {
		return disType;
	}

	public void setDisType(Integer disType) {
		this.disType = disType;
	}

	public Double getDisAmount() {
		return disAmount;
	}

	public void setDisAmount(Double disAmount) {
		this.disAmount = disAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
