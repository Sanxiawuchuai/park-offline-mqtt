package com.drzk.service.entity;

public class ParkErrorMessageBody extends SuperBody
{

	private String InOut;
	private String DisMessage;
	
	/**
	 * 错误信息
	 * @param DisMessage
	 */
	public void setDisMessage(String DisMessage)
	{
		this.DisMessage =DisMessage;
	}
	/**
	 * 错误信息
	 * @return
	 */
	public String getDisMessage()
	{
		return DisMessage;
	}
	/**
	 * 0,代表入场，1，代表出场
	 * @return
	 */
	public String getInOut()
	{
		return InOut;
	}
	/**
	 * 0,代表入场，1，代表出场
	 * @param InOut
	 */
	public void setInOut(String InOut)
	{
		this.InOut =InOut;
	}
}
