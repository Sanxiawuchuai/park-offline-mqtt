package com.drzk.offline.constant;

import com.drzk.common.TopicsDefine;
import com.drzk.offline.impl.OfPushCallback;
import com.drzk.utils.GlobalPark;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 线下MQTT连接配置工具类
 * @date 2018/10/8 14:51
 */
public class OfClientMQTT {

    public static MqttClient offMqttClient;
    private static Logger log= LoggerFactory.getLogger(OfClientMQTT.class);

    public static void start() throws MqttException {
        if(offMqttClient !=null && offMqttClient.isConnected()) {
            return;
        }
        String clientId=UUID.randomUUID().toString();
        offMqttClient = getConnect(clientId);
        subScipt();     //订阅相关的主题
    }

    /**
     * 主动订阅相关的配置
     */
    public static void subScipt(){
        try{
            if(offMqttClient == null) {
                return;
            }
            // 设置回调
            offMqttClient.setCallback(new OfPushCallback());
            //订阅消息
            int[] Qos  = {0};
            offMqttClient.subscribe(setTopics(), Qos);
        }catch (Exception e){
            log.error("线下MQTT订阅主题失败:",e);
        }
    }

    /**
     * 取消订阅的消息
     */
    public static void unSubScript(){
        try {
            if (offMqttClient == null) {
                return;
            }
            offMqttClient.unsubscribe(setTopics());     //取消订阅主题
        }catch (Exception e){
            log.error("线下取消订阅失败:",e);
        }
    }

    /**
     * 线下连接MQTT的方法
     * @param clientId
     * @return
     */
    public static MqttClient getConnect(String clientId) throws MqttException {
        String host = GlobalPark.properties.getProperty("MQTT_UPLINE");

        offMqttClient = new MqttClient(host, clientId, new MemoryPersistence());
        // MQTT的连接设置
        MqttConnectOptions options  = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(30);
        options.setMaxInflight(100);

        //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
        if (!offMqttClient.isConnected()) {
            offMqttClient.connect(options);
        }else {//这里的逻辑是如果连接成功就重新连接
            offMqttClient.disconnect();
            offMqttClient.connect(options);
        }

        return offMqttClient;
    }

    /**
     * 设置线下相关订阅的主题
     * @return
     */
    private static String[] setTopics(){
        String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
        String topic =String.format(TopicsDefine.DOWN_UPLOAD_PARK_EVENT, parkNo);       //线下通讯、主板上传的主题
        String[] topics ={topic};
        return topics;
    }
}
