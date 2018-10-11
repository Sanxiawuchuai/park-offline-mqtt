package com.drzk.online.vo;

import java.io.Serializable;

public class ReplayHeadVO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8486723188968515421L;
	private String method;// 方法名
	private String parkingNo;// 车场编号
	public String getMethod()
	{
		return method;
	}
	public void setMethod(String method)
	{
		this.method = method;
	}
	public String getParkingNo()
	{
		return parkingNo;
	}
	public void setParkingNo(String parkingNo)
	{
		this.parkingNo = parkingNo;
	}
	
}
