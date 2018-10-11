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
import com.drzk.utils.SpringUtil;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PushCallback implements MqttCallback {
    private static Logger logger = Logger.getLogger("userLog");

    public void connectionLost(Throwable cause) {
        while (true){
            try {//如果没有发生异常说明连接成功，如果发生异常，则死循环
                Thread.sleep(5000);
                ClientMQTT.start();         //重新启动
                logger.debug("连接云端MQTT断线重连成功");
                break;
            }catch (Exception e){
                logger.error("连接云端MQTT断线重连异常");
                continue;
            }
        }

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
            String mac=GlobalPark.sysMap.get("mac");            //得到

            String uploadParkEventTopic = String.format(TopicsDefine.DOWN_UPLOAD_PARK_EVENT, parkNo);
            String scanCodesInAndOut = String.format(ConstantUtil.ScanCodeInOut, parkNo);
            String DSReplayTopic = String.format(ConstantUtil.DSReplayStart, parkNo);
            String downEventTopic="X51200000001/publish/offData";//String.format(TopicsDefine.DOWN_OFFLINEDIR_EVENT,parkNo);// "X21412300001/publish/offData"         
            //String downEventTopic=String.format(ConstantUtil.ONLINE_LOWER,parkNo);
            String downParkNoTopic=String.format(ConstantUtil.OFFLINE_PARKNO,mac);
            logger.debug("topic name="+topic);
            logger.debug("message:=="+topic);
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
            }else if(downParkNoTopic.equals(topic)){        //车场主题下发
                DownOffEvent.lowerNum(body);
            }
        } catch (Exception e) {
            logger.error("消息到达处理:",e);
        }
    }
}
