package com.tencent.android.tpush.data;

import java.io.Serializable;

/* compiled from: ProGuard */
public class MessageId implements Serializable {
    public static final short FLAG_ACK = (short) 1;
    public static final short FLAG_UNACK = (short) 0;
    private static final long serialVersionUID = 8708157897391765794L;
    public long accessId;
    public byte apn;
    public long busiMsgId = 0;
    public String date = "";
    public long host;
    public long id;
    public short isAck;
    public byte isp;
    public long msgType = -1;
    public long multiPkg = 0;
    public byte pact;
    public String pkgName;
    public int port;
    public long pushTime;
    public long receivedTime;
    public long serverTime;
    public String serviceHost;
    public long timestamp = 0;
    public long ttl;

    public boolean a() {
        return this.isAck == (short) 1;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MessageId [id=").append(this.id).append(", isAck=").append(this.isAck).append(", isp=").append(this.isp).append(", apn=").append(this.apn).append(", accessId=").append(this.accessId).append(", pushTime=").append(this.pushTime).append(", receivedTime=").append(this.receivedTime).append(", pact=").append(this.pact).append(", host=").append(this.host).append(", port=").append(this.port).append(", serviceHost=").append(this.serviceHost).append(", pkgName=").append(this.pkgName).append(", busiMsgId=").append(this.busiMsgId).append(", timestamp=").append(this.timestamp).append(", msgType=").append(this.msgType).append(", multiPkg=").append(this.multiPkg).append(", date=").append(this.date).append(", serverTime=").append(this.serverTime).append(", ttl=").append(this.ttl).append("]");
        return stringBuilder.toString();
    }
}
