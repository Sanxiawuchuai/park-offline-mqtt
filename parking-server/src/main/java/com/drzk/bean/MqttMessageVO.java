
package com.drzk.bean;

/**
 * ClassName:MqttMessageVO <br>
 * Date: 2018年6月21日 下午6:17:27 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class MqttMessageVO {
	
	private int status;
	private String topic;
	private String body;
	
	/**
	* status.
	*
	* @return the status
	* @since JDK 1.8
	*/
	public int getStatus() {
		return status;
	}

	
	/**
	* status.
	*
	* @param status the status to set
	* @since JDK 1.8
	*/
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	* topic.
	*
	* @return the topic
	* @since JDK 1.8
	*/
	public String getTopic() {
		return topic;
	}
	
	/**
	* topic.
	*
	* @param topic the topic to set
	* @since JDK 1.8
	*/
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	/**
	* body.
	*
	* @return the body
	* @since JDK 1.8
	*/
	public String getBody() {
		return body;
	}
	
	/**
	* body.
	*
	* @param body the body to set
	* @since JDK 1.8
	*/
	public void setBody(String body) {
		this.body = body;
	}
	
	

}
