package com.drzk.online.vo;

public class ParkMonthlyFeeType extends SuperBody
{

	private String packageType;
	private String packageName;
	private String packageCharge;
	private String chargeModel;
	private String remark;
//	private String description;
	private String packageDuration;
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/** 月租类型 */
	public String getPackageType()
	{
		return packageType;
	}
	/** 月租类型 */
	public void setPackageType(String packageType)
	{
		this.packageType = packageType;
	}
	/** 月租类型名称 */
	public String getPackageName()
	{
		return packageName;
	}
	/** 月租类型名称 */
	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}
	/** 续费金额 */
	public String getPackageCharge()
	{
		return packageCharge;
	}
	/** 续费金额 */
	public void setPackageCharge(String packageCharge)
	{
		this.packageCharge = packageCharge;
	}
	/** 计费模式， 1连续性计费 , 2按当天起计 */
	public String getChargeModel()
	{
		return chargeModel;
	}
	/** 计费模式， 1连续性计费 , 2按当天起计 */
	public void setChargeModel(String chargeModel)
	{
		this.chargeModel = chargeModel;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
//	public String getDescription()
//	{
//		return description;
//	}
//	public void setDescription(String description)
//	{
//		this.description = description;
//	}
	/** 1月卡，2季卡，3半年卡，4年卡 */
	public String getPackageDuration()
	{
		return packageDuration;
	}
	/** 1月卡，2季卡，3半年卡，4年卡 */
	public void setPackageDuration(String packageDuration)
	{
		this.packageDuration = packageDuration;
	}
	
}
