/**
 * @author chenlong
 * 2018年8月31日
 */
package com.drzk.offline.vo;

import java.io.Serializable;

import com.drzk.vo.ParkSumUser;

/**
 * @author chenlong
 * 2018年8月31日
 */
public class ParkSumUserBody implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7453115430692015393L;
	private String uId;
	private ParkSumUser sunUser;

	/**
	 * @return the sunUser
	 */
	public ParkSumUser getSunUser() {
		return sunUser;
	}

	/**
	 * @param sunUser the sunUser to set
	 */
	public void setSunUser(ParkSumUser sunUser) {
		this.sunUser = sunUser;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}
	
	
	
}
