package com.drzk.online.vo;

import java.io.Serializable;

public class YunScanCodeInOutReplayHead implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3514305892631824761L;
	public Integer status;
	public String message;
	
	/** 返回状态，1：成功；0：失败 */
	public Integer getStatus()
	{
		return status;
	}
	/** 返回状态，1：成功；0：失败 */
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	/** 失败时返回失败原因 */
	public String getMessage()
	{
		return message;
	}
	/** 失败时返回失败原因 */
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	
	
}
