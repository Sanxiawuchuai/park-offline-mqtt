package com.drzk.service.entity;

public class ParkCarPlaceBody extends SuperBody
{
	private String placeNumSum;	
	private String overPlusNum;
	private String monSumNum;
	private String temSumNum;
	private String bespeakPlusNum;
	private String shareCarNum;
	private String tempCarNum;
	private String monCarNum;
	private String overStoreNum;
	private String freeCarNum;
	
	
	
	/**免费车*/
	public void setFreeCarNum(String freeCarNum)
	{
		this.freeCarNum =freeCarNum;
	}
	public String getFreeCarNum()
	{
		return freeCarNum;
	}
	
	/**储值车 */
	public void  setOverStoreNum(String overStoreNum)
	{
		this.overStoreNum =overStoreNum;
	}
	public String getOverStoreNum()
	{
		return overStoreNum;
	}
	/**月租车*/
	public void setMonCarNum(String monCarNum)
	{
		this.monCarNum=monCarNum;
	}
	public String getMonCarNum()
	{
		return monCarNum;
	}
	/**临时车*/
	public void setTempCarNum(String tempCarNum)
	{
		this.tempCarNum =tempCarNum;
	}
	public String getTempCarNum()
	{
		return tempCarNum;
	}
	/**共享车*/
	public void setShareCarNum(String shareCarNum)
	{
		this.shareCarNum =shareCarNum;
	}
	public String getShareCarNum()
	{
		return shareCarNum;
	}
	
	/**预约车*/
	public void setBespeakPlusNum(String bespeakPlusNum)
	{
		this.bespeakPlusNum=bespeakPlusNum;
	}
	public String getBespeakPlusNum()
	{
		return bespeakPlusNum;
	}
	/**临时车剩余车位*/
	public String getTemSumNum()
	{
		return temSumNum;
	}
	public void setTemSumNum(String temSumNum)
	{
		this.temSumNum =temSumNum;
	}
	/** 固定车剩余车位*/
	public void setMonSumNum(String monSumNum)
	{
		this.monSumNum =monSumNum;
	}
	public String getMonSumNum()
	{
		return monSumNum;
	}
	
	/** 剩余车位*/
	public void setOverPlusNum(String overPlusNum)
	{
		this.overPlusNum =overPlusNum;
	}
	public String getOverPlusNum()
	{
		return overPlusNum;
	}
	/** 总车位*/
	public void setPlaceNumSum(String placeNumSum)
	{
		this.placeNumSum = placeNumSum;
	}
	public String getPlaceNumSum()
	{
		return placeNumSum;
	}
}
