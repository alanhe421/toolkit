package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsRegisterReq extends JceStruct {
    static DeviceInfo cache_deviceInfo;
    static MutableInfo cache_mutableInfo;
    public long accessId = 0;
    public String accessKey = "";
    public String account = "";
    public String appCert = "";
    public String appVersion = "";
    public String deviceId = "";
    public DeviceInfo deviceInfo = null;
    public short deviceType = (short) 0;
    public byte keyEncrypted = (byte) 0;
    public MutableInfo mutableInfo = null;
    public String reserved = "";
    public String ticket = "";
    public short ticketType = (short) 0;
    public String token = "";
    public short updateAutoTag = (short) 0;
    public short version = (short) 0;

    public TpnsRegisterReq(long j, String str, String str2, String str3, String str4, String str5, short s, short s2, DeviceInfo deviceInfo, String str6, short s3, byte b, MutableInfo mutableInfo, short s4, String str7, String str8) {
        this.accessId = j;
        this.accessKey = str;
        this.deviceId = str2;
        this.appCert = str3;
        this.account = str4;
        this.ticket = str5;
        this.ticketType = s;
        this.deviceType = s2;
        this.deviceInfo = deviceInfo;
        this.token = str6;
        this.version = s3;
        this.keyEncrypted = b;
        this.mutableInfo = mutableInfo;
        this.updateAutoTag = s4;
        this.appVersion = str7;
        this.reserved = str8;
    }

    public void writeTo(d dVar) {
        dVar.a(this.accessId, 0);
        dVar.a(this.accessKey, 1);
        dVar.a(this.deviceId, 2);
        dVar.a(this.appCert, 3);
        if (this.account != null) {
            dVar.a(this.account, 4);
        }
        if (this.ticket != null) {
            dVar.a(this.ticket, 5);
        }
        dVar.a(this.ticketType, 6);
        dVar.a(this.deviceType, 7);
        if (this.deviceInfo != null) {
            dVar.a(this.deviceInfo, 8);
        }
        if (this.token != null) {
            dVar.a(this.token, 9);
        }
        dVar.a(this.version, 10);
        dVar.b(this.keyEncrypted, 11);
        if (this.mutableInfo != null) {
            dVar.a(this.mutableInfo, 12);
        }
        dVar.a(this.updateAutoTag, 13);
        if (this.appVersion != null) {
            dVar.a(this.appVersion, 14);
        }
        if (this.reserved != null) {
            dVar.a(this.reserved, 15);
        }
    }

    public void readFrom(c cVar) {
        this.accessId = cVar.a(this.accessId, 0, true);
        this.accessKey = cVar.a(1, true);
        this.deviceId = cVar.a(2, true);
        this.appCert = cVar.a(3, true);
        this.account = cVar.a(4, false);
        this.ticket = cVar.a(5, false);
        this.ticketType = cVar.a(this.ticketType, 6, false);
        this.deviceType = cVar.a(this.deviceType, 7, false);
        if (cache_deviceInfo == null) {
            cache_deviceInfo = new DeviceInfo();
        }
        this.deviceInfo = (DeviceInfo) cVar.a(cache_deviceInfo, 8, false);
        this.token = cVar.a(9, false);
        this.version = cVar.a(this.version, 10, false);
        this.keyEncrypted = cVar.a(this.keyEncrypted, 11, false);
        if (cache_mutableInfo == null) {
            cache_mutableInfo = new MutableInfo();
        }
        this.mutableInfo = (MutableInfo) cVar.a(cache_mutableInfo, 12, false);
        this.updateAutoTag = cVar.a(this.updateAutoTag, 13, false);
        this.appVersion = cVar.a(14, false);
        this.reserved = cVar.a(15, false);
    }
}
