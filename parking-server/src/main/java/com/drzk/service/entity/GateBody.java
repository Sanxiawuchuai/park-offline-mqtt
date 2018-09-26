package com.drzk.service.entity;
//道闸控制实体
public class GateBody  extends SuperBody{
//	/控制命令 1.开闸，2.关闸,3.停闸,4,启动高峰查模式,5,关闭高峰模式
	private String order;
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}
