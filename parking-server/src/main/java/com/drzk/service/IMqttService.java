package com.drzk.service;

import org.springframework.messaging.Message;

import com.drzk.bean.MqttMessageVO;

public interface IMqttService {
	//public MqttMessageVO sendMessage(String topic, String content,String replyTopic,int timeout);
	
	void reciveMessage(Message<String> message);
}
