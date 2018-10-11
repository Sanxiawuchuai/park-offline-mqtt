
package com.drzk.service.entity;

import java.io.Serializable;

/**
 * ClassName:ReplyHead <br>
 * Date: 2018年7月2日 上午10:38:35 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class ReplyHead implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5003621204026959857L;

	/** 状态*/
	private String status;
	
	/** 错误信息*/
	private String message;
	
	/** 方法(业务类型)*/
	private String method;

	
	/**
	* status.
	*
	* @return the status
	* @since JDK 1.8
	*/
	public String getStatus() {
		return status;
	}

	
	/**
	* status.
	*
	* @param status the status to set
	* @since JDK 1.8
	*/
	public void setStatus(String status) {
		this.status = status;
	}

	
	/**
	* message.
	*
	* @return the message
	* @since JDK 1.8
	*/
	public String getMessage() {
		return message;
	}

	
	/**
	* message.
	*
	* @param message the message to set
	* @since JDK 1.8
	*/
	public void setMessage(String message) {
		this.message = message;
	}

	
	/**
	* method.
	*
	* @return the method
	* @since JDK 1.8
	*/
	public String getMethod() {
		return method;
	}

	
	/**
	* method.
	*
	* @param method the method to set
	* @since JDK 1.8
	*/
	public void setMethod(String method) {
		this.method = method;
	}
	
	
	
}
