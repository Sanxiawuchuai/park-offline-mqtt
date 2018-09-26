package com.drzk.service.impl;

import com.drzk.app.Application;
import com.drzk.bean.MqttMessageVO;
import com.drzk.common.TopicsDefine;
import com.drzk.online.constant.ConstantUtil;
import com.drzk.online.impl.DSReplayEventImpl;
import com.drzk.online.impl.YunScanCodeEvent;
import com.drzk.online.service.YunEventSever;
import com.drzk.parklib.event.UploadParkingEvent;
import com.drzk.parklib.test.MainBoardSdkTest;
import com.drzk.service.IMqttService;
import com.drzk.utils.*;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.messaging.Message;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service("onlineMqttServiceImpl")
public class OnlineMqttServiceImpl implements IMqttService {
	private static Logger logger = Logger.getLogger("userLog");
	
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
	public static MqttMessageVO sendMessage(String topic, String content, String replyTopic, int timeout) {

		CountDownLatch countDownLatch = new CountDownLatch(1);
		IMqttClient client = null;
		MqttMessageVO returnMessage = new MqttMessageVO();
		String uId = JsonUtil.getUidByJsonStr(content);
		String clientId = UUID.randomUUID().toString();
		// 如果replyTopic为空,表示不需要等待
		boolean isWait = !StringUtils.isNullOrEempty(replyTopic);

		try {
			client = ClientMQTT.getConnect(clientId);
			if (isWait) {
				client.subscribe(replyTopic, 0);
				client.setCallback(new MqttCallback() {

					@Override
					public void messageArrived(String topic, MqttMessage message) throws Exception {
						// System.out.println(CommonUtils.bytesToHex(message.getPayload()));
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
						// System.out.println("传递完成");
					}

					@Override
					public void connectionLost(Throwable cause) {
						// System.out.println("连接丢失");
					}
				});
			}

			MqttMessage sendMessage = new MqttMessage(content.getBytes("utf-8"));
			sendMessage.setQos(0);
			client.publish(topic, sendMessage);
			System.out.println("publish sucess:"+topic+"=>" + sendMessage);
			if (isWait) {
				countDownLatch.await(timeout, TimeUnit.SECONDS);
				client.unsubscribe(replyTopic);
			}
			
			//client.disconnect();
			//client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMessage;
	}

	/**
	 * mqtt 接收事件
	 */
	@Override
	public void reciveMessage(Message<String> message) {
		try {
			String topic = (String) message.getHeaders().get("mqtt_topic");
			String body = message.getPayload();
			System.out.println("topic:" + topic);
			System.out.println("body:" + body);
			String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
			String uploadParkEventTopic = String.format(TopicsDefine.DOWN_UPLOAD_PARK_EVENT, parkNo);
			String scanCodesInAndOut = String.format(ConstantUtil.ScanCodeInOut, parkNo);			
			String DSReplayTopic = String.format(ConstantUtil.DSReplayStart, parkNo);
			if ("test".equals(topic)) {
				MainBoardSdkTest.test(body);
			} else if ("refresh".equals(topic)) {
				Application app = SpringUtil.getBean(Application.class);
				app.restart();
			} else if (uploadParkEventTopic.equals(topic)) { // 车场上传事件
				UploadParkingEvent.receiveJson(body);
			}
			else if(topic.startsWith(DSReplayTopic) && topic.endsWith(ConstantUtil.DSReplayEnd))
			{
				DSReplayEventImpl.receiveJson(body);
			}
			else if(topic.equals(scanCodesInAndOut))
			{
				YunScanCodeEvent.receiveJson(body);
			}
			
		} catch (Exception e) {
			logger.error("mqtt 接收事件:",e);
		}
	}
	
	public static void start() {
		
	}
}
