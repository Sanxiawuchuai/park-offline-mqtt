
package com.drzk.common;

/**
 * mqtt主题定义  <br>
 * 前缀DOWN表示线下的在主题，为UP表示线上主题<br>
 * Date: 2018年6月28日 上午11:51:58 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public interface TopicsDefine {
	
	/**主板上传车场事件*/
    String DOWN_UPLOAD_PARK_EVENT = "%s/publish/data";
	
	/**向主板发送mqtt消息都为此主题,具体内容根据消息不同来构造消息体*/
    String DOWN_SEND = "%s/controller";
	
	/**线下主题回复*/
    String DOWN_RECIVE = "%s/aysnReply/data";
	
	/**发送岗亭错误信息*/
    String BOX_ERROR ="%s/publish/box/data";

}
