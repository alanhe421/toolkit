package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class UnregInfo extends JceStruct {
    static AppInfo cache_appInfo;
    public AppInfo appInfo = null;
    public byte isUninstall = (byte) 0;
    public long timestamp = 0;

    public UnregInfo(AppInfo appInfo, byte b, long j) {
        this.appInfo = appInfo;
        this.isUninstall = b;
        this.timestamp = j;
    }

    public void writeTo(d dVar) {
        dVar.a(this.appInfo, 0);
        dVar.b(this.isUninstall, 1);
        dVar.a(this.timestamp, 2);
    }

    public void readFrom(c cVar) {
        if (cache_appInfo == null) {
            cache_appInfo = new AppInfo();
        }
        this.appInfo = (AppInfo) cVar.a(cache_appInfo, 0, true);
        this.isUninstall = cVar.a(this.isUninstall, 1, true);
        this.timestamp = cVar.a(this.timestamp, 2, false);
    }
}
