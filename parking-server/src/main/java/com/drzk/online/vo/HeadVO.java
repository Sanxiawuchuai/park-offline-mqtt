package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 线下发布到线上head
 */
public class HeadVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7847659866531195093L;
	private String method;// 方法名
	private String replyTopic;// 回复主题
	private String parkingNo;// 车场编号
    private int status;
    
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getReplyTopic() {
		return replyTopic;
	}

	public void setReplyTopic(String replyTopic) {
		this.replyTopic = replyTopic;
	}

	public String getParkingNo() {
		return parkingNo;
	}

	public void setParkingNo(String parkingNo) {
		this.parkingNo = parkingNo;
	}

}
