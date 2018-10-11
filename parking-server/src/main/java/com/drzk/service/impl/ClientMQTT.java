package com.drzk.service.impl;
/**
 *
 * Description:
 * @author admin
 * 2017年2月10日下午17:50:15
 */

import com.drzk.online.constant.ConstantUtil;
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
 
    public static void start() throws MqttException {
        if(onlineMqttClient !=null && onlineMqttClient.isConnected()) {
            return;
        }
        //String clientId = GlobalPark.properties.getProperty("MQTT_CLIENTID");
        String clientId=UUID.randomUUID().toString();
        onlineMqttClient = getConnect(clientId);
        subScipt();         //订阅相关的主题
    }

    /**
     * 主动订阅相关的配置
     */
    public static void subScipt(){
        try{
            if(onlineMqttClient == null) {
                return;
            }
            // 设置回调
            onlineMqttClient.setCallback(new PushCallback());
            //订阅消息
            int[] Qos  = {0,0,0,0};
            onlineMqttClient.subscribe(setTopics(), Qos);
        }catch (Exception e){
            log.error("线上MQTT订阅主题失败:",e);
        }
    }

    /**
     * 取消订阅线上主题
     */
    public static void unScribe(){
        try {
            if (onlineMqttClient == null) {
                return;
            }
            onlineMqttClient.unsubscribe(setTopics());
        }catch (Exception e){
            log.error("取消订阅线上主题异常:",e);
        }
    }


    public static final Integer MAX_INFLIGHT=100;
    public static MqttClient getConnect(String clientId) throws MqttException {
    	String host = GlobalPark.properties.getProperty("MQTT_ONLINE");

        onlineMqttClient = new MqttClient(host, clientId, new MemoryPersistence());
         // MQTT的连接设置
        MqttConnectOptions options  = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置超时时间 单位为秒
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(30);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);

        options.setMaxInflight(MAX_INFLIGHT);

        //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
        if (!onlineMqttClient.isConnected()) {
            onlineMqttClient.connect(options);
        }else {//这里的逻辑是如果连接成功就重新连接
            onlineMqttClient.disconnect();
            onlineMqttClient.connect(options);
        }
    	return onlineMqttClient;
    }

    /**
     * 设置线下相关订阅的主题
     * @return
     */
    private static String[] setTopics(){
        String parkNum = GlobalPark.properties.getProperty("PARK_NUM");
        String mac=GlobalPark.sysMap.get("mac");
        String topic =String.format(ConstantUtil.DS_TOPIC, parkNum, "+");       //线下上传后回调主题
        String topic1=String.format(ConstantUtil.ONLINE_LOWER,parkNum);                               //线上下发的主题
        String topic2=String.format(ConstantUtil.ScanCodeInOut,parkNum);        //线上支付模块的主题
        String topic3=String.format(ConstantUtil.OFFLINE_PARKNO,mac);        //线上审核后下发车场编号
        String[] topics ={topic,topic1,topic2,topic3};
        return topics;
    }

}