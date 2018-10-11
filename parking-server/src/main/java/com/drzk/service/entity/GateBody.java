package com.drzk.service.entity;

import java.io.Serializable;

//道闸控制实体
public class GateBody  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3634147244862584130L;
	//	/控制命令 1.开闸，2.关闸,3.停闸,4,启动高峰查模式,5,关闭高峰模式
	private String order;
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}
