package com.dk.wx.po;

public class SubscribeMessage {
    //开发者微信号
    private String ToUserName;

    //发送方帐号（一个OpenID）
    private String FromUserName;

    //消息创建时间 （整型）
    private Long CreateTime;

    //消息类型，event
    private String MsgType;

    //事件类型，subscribe(订阅)、unsubscribe(取消订阅)
    private String Event;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
