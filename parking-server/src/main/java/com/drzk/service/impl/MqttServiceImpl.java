package com.drzk.service.impl;

import com.drzk.bean.MqttMessageVO;
import com.drzk.common.ParkMethod;
import com.drzk.offline.constant.OfClientMQTT;
import com.drzk.service.IMqttService;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service("mqttServiceImpl")
public class MqttServiceImpl implements IMqttService {
	private static Logger logger = Logger.getLogger(MqttServiceImpl.class);

	public static ConcurrentHashMap<String,IMqttClient> clientMap = new ConcurrentHashMap<>();          //测试调用多个MQTT方法

	/**
	 * 线下MQTT连接问题
	 * @param mac
	 * @return
	 */
	public static IMqttClient getConnection(String mac) {
		IMqttClient client=clientMap.get(mac);              //获取当前mac地址所对应的client
		if (client == null) {
			client=getCon(client,mac);
		}else{
			if(!client.isConnected()){
				client=getCon(client,mac);
			}
		}
		return client;
	}

	/**
	 * 连接client连接
	 * @param client
	 * @param mac
	 */
	public static IMqttClient getCon(IMqttClient client,String mac){
		try {
			String clientId=ParkMethod.getUUID()+mac;
			client = OfClientMQTT.getConnect(clientId);
			clientMap.put(mac,client);          //存放到公共的client集合中
		} catch (MqttException e) {
			logger.error("线下MQTT连接异常：",e);
		}
		return client;
	}

	/**
	 * mqtt 发送消息
	 * 
	 * @param topic      主题
	 * @param content    消息内容
	 * @param replyTopic 需要等待回复的主题,如果不需要等待回复，传null
	 * @param timeout    等待超时时间(单位:秒)
	 * @return 如果有消息回复,回复的信息保存在MqttMessageVO中的topic和message中,同时status值为1
	 * @notice 如果没有消息回复,MqttMessageVO并不为null,只是status,topic与message中没值.<br>
	 *         所以不能用MqttMessageVO==null来判断是否有回复.应该用status是否为0判断<br>
	 *         另外,如果等待时间超过timeout,MqttMessageVO也不为null.
	 */
	public static MqttMessageVO sendMessage(String mac,String topic, String content, String replyTopic, int timeout) {
		//如果mac为空，直接退出，不发布
		if(mac==null){
			return null;
		}

		CountDownLatch countDownLatch = new CountDownLatch(1);
		IMqttClient client = null;
		MqttMessageVO returnMessage = new MqttMessageVO();
		String uId = JsonUtil.getUidByJsonStr(content);

		// 如果replyTopic为空,表示不需要等待
		boolean isWait = !StringUtils.isNullOrEempty(replyTopic);
		try {
			client = getConnection(mac);
			logger.debug("线下发布订阅语句:"+content);
			if (isWait) {
				client.subscribe(replyTopic, 0);
			
				client.setCallback(new MqttCallback() {
					
					@Override
					public void messageArrived(String topic, MqttMessage message) throws Exception {
						String reciveBody = new String(message.getPayload());
						String reciveUId = JsonUtil.getUidByJsonStr(reciveBody);
						if (uId.equals(reciveUId)) {
							returnMessage.setStatus(1);
							returnMessage.setTopic(topic);
							returnMessage.setBody(reciveBody);
							countDownLatch.countDown();
						}
					}
					@Override
					public void deliveryComplete(IMqttDeliveryToken token) {
						 //System.out.println("传递完成");
					}
					@Override
					public void connectionLost(Throwable cause) {
						//System.out.println("连接丢失");
					}
				});
			}
			//System.out.println(content);
			synchronized (client) {
				MqttMessage sendMessage = new MqttMessage(content.getBytes("utf-8"));
				sendMessage.setQos(0);
				client.publish(topic, sendMessage);
				if (isWait) {
					countDownLatch.await(3, TimeUnit.SECONDS);
					client.unsubscribe(replyTopic);
				}
			}
		} catch (Exception e) {
			logger.error("线下发布订阅异常：",e);
		}

		return returnMessage;
	}
}
