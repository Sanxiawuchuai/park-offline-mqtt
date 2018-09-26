
package com.drzk.online.vo;

import java.util.List;

/**
 * ClassName:ReplayVO <br>
 * Date: 2018年8月28日 下午7:18:16 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class ReplayVO<H,B> {
	private H head;
	
	private List<B> body;

	
	/**
	* head.
	*
	* @return the head
	* @since JDK 1.8
	*/
	public H getHead() {
		return head;
	}

	
	/**
	* head.
	*
	* @param head the head to set
	* @since JDK 1.8
	*/
	public void setHead(H head) {
		this.head = head;
	}

	
	/**
	* body.
	*
	* @return the body
	* @since JDK 1.8
	*/
	public List<B> getBody() {
		return body;
	}

	
	/**
	* body.
	*
	* @param body the body to set
	* @since JDK 1.8
	*/
	public void setBody(List<B> body) {
		this.body = body;
	}
	
	

}
