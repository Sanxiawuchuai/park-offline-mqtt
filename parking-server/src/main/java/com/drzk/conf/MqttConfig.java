package com.drzk.conf;

import com.drzk.offline.constant.OfClientMQTT;
import com.drzk.online.service.ParkConfigService;
import com.drzk.service.impl.ClientMQTT;
import com.drzk.utils.CompUtils;
import com.drzk.utils.GlobalPark;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/9 11:51
 */
@Component
public class MqttConfig{

    private Logger log=Logger.getLogger(MqttConfig.class);
    @Autowired
    private ParkConfigService parkConfigService;

    //初始化MQTT的参数配置
    @PostConstruct
    public void mqttInit(){
        try {
            parkConfigService.reloadSysParams();
            OfClientMQTT.start();            //连接线下服务器

            //如果数据库设置需要往云端传数据,则启动云端mqtt
            if ("1".equals(GlobalPark.properties.getProperty("UPLOAD_DATA_CLOUD"))) {
                //加载服务器相关的参数
                CompUtils.getAllSn();;
                ClientMQTT.start();
            }
        }catch (MqttException e) {
            log.error("启动项目时，连接MQTT异常：",e);
        }
    }
}
