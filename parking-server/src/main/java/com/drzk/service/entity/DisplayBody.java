package com.drzk.service.entity;

import java.util.List;

//显示屏实体
public class DisplayBody extends SuperBody {
	// 事件号
	private String carEventNo;
	private List<DisplayChar> displayChar;
	
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

