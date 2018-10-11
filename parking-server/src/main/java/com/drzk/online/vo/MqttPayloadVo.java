package com.drzk.online.vo;


public class MqttPayloadVo<T> {

	 //头信息
    private HeadVO head;

    //发送内容
    private T body;
    
    public HeadVO getHead() {
        if(head==null){
            this.head=new HeadVO();
        }
        return head;
    }

    public void setHead(HeadVO head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}
