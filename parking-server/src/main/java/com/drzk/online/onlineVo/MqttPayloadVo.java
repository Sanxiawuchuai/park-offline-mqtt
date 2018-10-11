package com.drzk.online.onlineVo;

import java.io.Serializable;

/**
 * 2018/6/23 cx
 */
public class MqttPayloadVo<T>{


	//头信息
    private MqttHeadVo head;

    //发送内容
    private T body;


    public MqttHeadVo getHead() {
        if (head == null) {
            this.head = new MqttHeadVo();
        }
        return head;
    }

    public void setHead(MqttHeadVo head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}
