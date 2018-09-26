package com.drzk.service.impl;
/**
 * Description:
 *
 * @author admin
 * 2017年2月10日下午18:04:07
 */

import com.drzk.app.Application;
import com.drzk.common.TopicsDefine;
import com.drzk.online.constant.ConstantUtil;
import com.drzk.online.constant.DownOffEvent;
import com.drzk.online.impl.DSReplayEventImpl;
import com.drzk.online.impl.YunScanCodeEvent;
import com.drzk.parklib.event.UploadParkingEvent;
import com.drzk.parklib.test.MainBoardSdkTest;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.LoggerUntils;
import com.drzk.utils.SpringUtil;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PushCallback implements MqttCallback {
    private static Logger logger = Logger.getLogger("userLog");

    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
        ClientMQTT.start();         //重新启动
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    /**
     * 消息到达处理
     */
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        try {
            String body = new String(message.getPayload(), "utf-8");
            String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
            String uploadParkEventTopic = String.format(TopicsDefine.DOWN_UPLOAD_PARK_EVENT, parkNo);
            String scanCodesInAndOut = String.format(ConstantUtil.ScanCodeInOut, parkNo);
            String DSReplayTopic = String.format(ConstantUtil.DSReplayStart, parkNo);
            String downEventTopic="X21412300001/publish/offData";//String.format(TopicsDefine.DOWN_OFFLINEDIR_EVENT,parkNo);// "X21412300001/publish/offData"

            if ("test".equals(topic)) {
                MainBoardSdkTest.test(body);
            } else if ("refresh".equals(topic)) {
                Application app = SpringUtil.getBean(Application.class);
                app.restart();
            } else if (uploadParkEventTopic.equals(topic)) { // 车场上传事件
                UploadParkingEvent.receiveJson(body);
            } else if (topic.startsWith(DSReplayTopic) && topic.endsWith(ConstantUtil.DSReplayEnd)) {
                DSReplayEventImpl.receiveJson(body);
            } else if (topic.equals(scanCodesInAndOut)) {
            	YunScanCodeEvent.receiveJson(body);
            }else if(downEventTopic.equals(topic)){		//车场下发事件
                DownOffEvent.receiveJson(body);
            }
        } catch (Exception e) {
            logger.error("消息到达处理:",e);
        }
    }
}
