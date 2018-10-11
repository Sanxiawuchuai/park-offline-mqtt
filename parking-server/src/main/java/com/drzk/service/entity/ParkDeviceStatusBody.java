package com.drzk.service.entity;

import com.drzk.vo.ParkDeviceStatus;

import java.io.Serializable;
import java.util.List;

public class ParkDeviceStatusBody implements Serializable
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7906027627817139189L;
	private String uId;
	private List<ParkDeviceStatus> deviceStatus;
	
	public void setDeviceStatus(List<ParkDeviceStatus> deviceStatus)
	{
		this.deviceStatus =deviceStatus; 
	}
	public List<ParkDeviceStatus> getDeviceStatus()
	{
		return deviceStatus;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}
