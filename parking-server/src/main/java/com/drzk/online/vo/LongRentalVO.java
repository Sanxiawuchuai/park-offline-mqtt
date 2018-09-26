package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 长租计费
 */
public class LongRentalVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public static final String TABLE="park_monthlyfeetype";
	
    private String id;
	private String packageType;//套餐类型
	private String packageName;//套餐名称
	private String packageCharge;//金额
	private String chargeModel;//计费模式  1连续性计费 | 2按当天起计
	private String packageDuration;//套餐周期
	private String remark;//备注
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageCharge() {
		return packageCharge;
	}
	public void setPackageCharge(String packageCharge) {
		this.packageCharge = packageCharge;
	}
	public String getChargeModel() {
		return chargeModel;
	}
	public void setChargeModel(String chargeModel) {
		this.chargeModel = chargeModel;
	}
	public String getPackageDuration() {
		return packageDuration;
	}
	public void setPackageDuration(String packageDuration) {
		this.packageDuration = packageDuration;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
