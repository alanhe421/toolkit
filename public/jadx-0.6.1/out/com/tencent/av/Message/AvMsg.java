package com.tencent.av.Message;

import com.tencent.TIMCallBack;
import com.tencent.TIMUser;
import java.util.ArrayList;
import java.util.List;

public abstract class AvMsg {
    int authType;
    int auth_id;
    int bussType;
    private Type msgType;
    private List<TIMUser> receivers;
    private TIMUser sender;

    public enum Type {
        MutiAvInvitation,
        MutiAvAccept,
        MutiAvReject,
        MutiAvCancel
    }

    public void addReceivers(TIMUser tIMUser) {
        if (this.receivers == null) {
            this.receivers = new ArrayList();
        }
        this.receivers.add(tIMUser);
    }

    public Type getMsgType() {
        return this.msgType;
    }

    public List<TIMUser> getReceivers() {
        return this.receivers;
    }

    public TIMUser getSender() {
        return this.sender;
    }

    public abstract void receive(byte[] bArr);

    public abstract void response(TIMCallBack tIMCallBack);

    public abstract void send(TIMCallBack tIMCallBack);

    public void setMsgType(Type type) {
        this.msgType = type;
    }

    public void setReceivers(List<TIMUser> list) {
        this.receivers = list;
    }

    public void setSender(TIMUser tIMUser) {
        this.sender = tIMUser;
    }
}
