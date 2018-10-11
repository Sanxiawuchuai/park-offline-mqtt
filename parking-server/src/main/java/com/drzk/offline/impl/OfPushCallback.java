package com.drzk.offline.impl;

import com.drzk.app.Application;
import com.drzk.common.TopicsDefine;
import com.drzk.offline.constant.OfClientMQTT;
import com.drzk.parklib.event.UploadParkingEvent;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.SpringUtil;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/8 14:58
 */
public class OfPushCallback implements MqttCallback {
    private Logger log=Logger.getLogger("userLog");

    /**
     * 断线重连机制
     * @param throwable
     */
    @Override
    public void connectionLost(Throwable throwable) {
        while (true){
            try {//如果没有发生异常说明连接成功，如果发生异常，则死循环
                Thread.sleep(5000);
                OfClientMQTT.start();         //重新启动
                log.debug("线下服务断线重连成功");
                break;
            }catch (Exception e){
                log.debug("线下服务断线重连异常",e);
                continue;
            }
        }
    }

    /**
     * 接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用
     * @param iMqttDeliveryToken
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    /**
     * 线下订阅主题回调信息
     * @param topic 主题
     * @param message
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        try {
            String body = new String(message.getPayload(), "utf-8");
            String parkNo = GlobalPark.properties.getProperty("PARK_NUM");

            String uploadParkEventTopic = String.format(TopicsDefine.DOWN_UPLOAD_PARK_EVENT, parkNo);       //车场主板设置

            if ("refresh".equals(topic)) {
                Application app = SpringUtil.getBean(Application.class);
                app.restart();
            } else if (uploadParkEventTopic.equals(topic)) { // 车场上传事件
                UploadParkingEvent.receiveJson(body);
            }
        } catch (Exception e) {
            log.error("线下订阅异常",e);
        }
    }
}
