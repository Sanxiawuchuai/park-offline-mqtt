package com.drzk.online.service;

import com.drzk.online.vo.MqttPayloadVo;

public interface OfMqttService<T> {
	MqttPayloadVo<T> sendMqtt(T body);
	MqttPayloadVo<T> sendMessage(String topic, String body, String replyTopic, int timeout);
	public void sendMessage(String topic, String content, int timeout);
}
