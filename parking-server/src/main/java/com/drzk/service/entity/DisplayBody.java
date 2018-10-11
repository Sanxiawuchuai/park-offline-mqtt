package com.drzk.service.entity;

import java.io.Serializable;
import java.util.List;

//显示屏实体
public class DisplayBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4650607918811616607L;
	// 事件号
	private String carEventNo;
	private List<DisplayChar> displayChar;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	/**
	* carEventNo.
	*
	* @return the carEventNo
	* @since JDK 1.8
	*/
	public String getCarEventNo() {
		return carEventNo;
	}
	/**
	* carEventNo.
	*
	* @param carEventNo the carEventNo to set
	* @since JDK 1.8
	*/
	public void setCarEventNo(String carEventNo) {
		this.carEventNo = carEventNo;
	}



	
	/**
	* displayChar.
	*
	* @return the displayChar
	* @since JDK 1.8
	*/
	public List<DisplayChar> getDisplayChar() {
		return displayChar;
	}



	
	/**
	* displayChar.
	*
	* @param displayChar the displayChar to set
	* @since JDK 1.8
	*/
	public void setDisplayChar(List<DisplayChar> displayChar) {
		this.displayChar = displayChar;
	}

}

