package com.drzk.service.impl;
/**
 *
 * Description:
 * @author admin
 * 2017年2月10日下午17:50:15
 */

import com.drzk.utils.GlobalPark;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ClientMQTT {
 
    public static MqttClient onlineMqttClient;
    private static Logger log=LoggerFactory.getLogger(ClientMQTT.class);
 
    public static void start() {
        try {
        	if(onlineMqttClient !=null && onlineMqttClient.isConnected()) {
        		return;
        	}
        	//String clientId = GlobalPark.properties.getProperty("MQTT_CLIENTID");
			String clientId=UUID.randomUUID().toString();
        	onlineMqttClient = getConnect(clientId);
        	if(onlineMqttClient == null) {
        		return;
        	}
            // 设置回调
            onlineMqttClient.setCallback(new PushCallback());
            //订阅消息
            int[] Qos  = {0,0,0};
            String parkNum = GlobalPark.properties.getProperty("PARK_NUM");
            String topic = parkNum + "/park/+/v1";
            String topic1=parkNum+"/publish/offData";
            String topic2="server/data/publish/phone/"+parkNum;
            String[] topics ={topic,topic1,topic2};
            onlineMqttClient.subscribe(topics, Qos);
        } catch (Exception e) {
            log.error("MQTT启动连接云端出现异常：",e);
        }
    }
    
    public static MqttClient getConnect(String clientId) {
    	String host = GlobalPark.properties.getProperty("MQTT_ONLINE");
    	MqttClient onlineMqttClient = null;
    	try {
    		onlineMqttClient = new MqttClient(host, clientId, new MemoryPersistence());
			 // MQTT的连接设置
	        MqttConnectOptions options  = new MqttConnectOptions();
	        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
	        options.setCleanSession(true);
	        // 设置超时时间 单位为秒
			options.setAutomaticReconnect(true);
			options.setConnectionTimeout(5);
	        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
	        options.setKeepAliveInterval(20);
	        // 设置回调
	        onlineMqttClient.connect(options);
		} catch (MqttException e) {
            log.error("MQTT连接异常：",e);
		}
       
    	return onlineMqttClient;
    }
 
}
