package com.drzk.offline.vo;

import java.io.Serializable;
import java.util.List;

import com.drzk.vo.VwParkCarIn;

public class BoxGetInRecordReturn implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1843085084405696498L;
	private String uId;
	private List<VwParkCarIn> parkInRecord;
	
	public void setParkInRecord(List<VwParkCarIn> parkInRecord)
	{
		this.parkInRecord =parkInRecord;
	}
	public List<VwParkCarIn> getParkInRecord()
	{
		return parkInRecord;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}
