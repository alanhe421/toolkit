package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class MutableInfo extends JceStruct {
    public String bssid = "";
    public String mac = "";
    public String ssid = "";
    public String wflist = "";

    public MutableInfo(String str, String str2, String str3, String str4) {
        this.ssid = str;
        this.bssid = str2;
        this.mac = str3;
        this.wflist = str4;
    }

    public void writeTo(d dVar) {
        if (this.ssid != null) {
            dVar.a(this.ssid, 0);
        }
        if (this.bssid != null) {
            dVar.a(this.bssid, 1);
        }
        if (this.mac != null) {
            dVar.a(this.mac, 2);
        }
        if (this.wflist != null) {
            dVar.a(this.wflist, 3);
        }
    }

    public void readFrom(c cVar) {
        this.ssid = cVar.a(0, false);
        this.bssid = cVar.a(1, false);
        this.mac = cVar.a(2, false);
        this.wflist = cVar.a(3, false);
    }
}
