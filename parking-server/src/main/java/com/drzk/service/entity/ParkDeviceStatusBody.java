package com.drzk.service.entity;

import java.util.List;

import com.drzk.vo.ParkDeviceStatus;

public class ParkDeviceStatusBody extends SuperBody
{

	private List<ParkDeviceStatus> deviceStatus;
	
	public void setDeviceStatus(List<ParkDeviceStatus> deviceStatus)
	{
		this.deviceStatus =deviceStatus; 
	}
	public List<ParkDeviceStatus> getDeviceStatus()
	{
		return deviceStatus;
	}
}
