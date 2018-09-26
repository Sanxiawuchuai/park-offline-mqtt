
package com.drzk.offline.vo;


/**
 * 与主板通信消息的封装 <br>
 * 包含head 与 body,body是
 * Date: 2018年6月27日 上午9:54:37 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class BoxMessageVO<H,B> {
	private H head;
	private B body;
	
	public BoxMessageVO() {
		
	}

	public BoxMessageVO(H head, B body) {
		this.head = head;
		this.body = body;
	}



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
	public B getBody() {
		return body;
	}
	
	/**
	* body.
	*
	* @param body the body to set
	* @since JDK 1.8
	*/
	public void setBody(B body) {
		this.body = body;
	}
	
	
}
