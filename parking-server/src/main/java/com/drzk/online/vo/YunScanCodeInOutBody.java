package com.drzk.online.vo;

import java.io.Serializable;

public class YunScanCodeInOutBody implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5391153501226523545L;
	private String carNo;
	private String dsn;
	
	
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	public String getDsn()
	{
		return dsn;
	}
	public void setDsn(String dsn)
	{
		this.dsn = dsn;
	}
	 
	
	
}
