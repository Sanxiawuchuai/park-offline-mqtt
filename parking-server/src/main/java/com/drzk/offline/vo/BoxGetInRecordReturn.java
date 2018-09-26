package com.drzk.offline.vo;

import java.util.List;

import com.drzk.vo.VwParkCarIn;

public class BoxGetInRecordReturn extends SuperBody
{

	private List<VwParkCarIn> parkInRecord;
	
	public void setParkInRecord(List<VwParkCarIn> parkInRecord)
	{
		this.parkInRecord =parkInRecord;
	}
	public List<VwParkCarIn> getParkInRecord()
	{
		return parkInRecord;
	}
}
