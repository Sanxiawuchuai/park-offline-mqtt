package com.drzk.online.vo;

public class YunGetChargeReplay extends YunScanCodeOutModel
{

	private Integer overTime;

	/** 交费后允许免费出场时间 */
	public Integer getOverTime()
	{
		return overTime;
	}
	/** 交费后允许免费出场时间 */
	public void setOverTime(Integer overTime)
	{
		this.overTime = overTime;
	}
	
}
