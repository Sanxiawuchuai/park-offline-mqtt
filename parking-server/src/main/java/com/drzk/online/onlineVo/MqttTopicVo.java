package com.drzk.online.onlineVo;

import java.io.Serializable;

/**
 * 2018/6/27 cx
 */
public class MqttTopicVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9007422164208774641L;
	//主题
    private String topic;
    //qos
    private Integer qos=1;

    public MqttTopicVo() { }

    public MqttTopicVo(String topic) {
        this.topic = topic;
        this.qos=1;
    }

    public MqttTopicVo(String topic, Integer qos) {
        this.topic = topic;
        this.qos = qos;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    @Override
    public String toString() {
        return "TopicVo{" +
                "topic='" + topic + '\'' +
                ", qos=" + qos +
                '}';
    }
}
