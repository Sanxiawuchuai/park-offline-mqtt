package com.drzk.service.entity;

public class HeadReturn {
	 /// <summary>
    /// 0 成功 1失败
    /// </summary>
    private int status ;
    /// <summary>
    /// 返回错误信息
    /// </summary>
    private String message;
    
    
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
