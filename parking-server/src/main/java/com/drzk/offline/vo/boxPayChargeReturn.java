package com.drzk.offline.vo;

import com.drzk.fact.AbsRealTimeBase;
/** 收费改变实体 */
public class boxPayChargeReturn
{
	private String uId;
	private Integer type;
	private AbsRealTimeBase chargeData;
	
	public String getuId()
	{
		return uId;
	}
	public void setuId(String uId)
	{
		this.uId = uId;
	}
	/** 0为出口收费，1为中央收费 当为1时，控制器IP可以为空 */
	public Integer getType()
	{
		return type;
	}
	/** 0为出口收费，1为中央收费 当为1时，控制器IP可以为空 */
	public void setType(Integer type)
	{
		this.type = type;
	}
	public AbsRealTimeBase getChargeData()
	{
		return chargeData;
	}
	public void setChargeData(AbsRealTimeBase chargeData)
	{
		this.chargeData = chargeData;
	}
}
