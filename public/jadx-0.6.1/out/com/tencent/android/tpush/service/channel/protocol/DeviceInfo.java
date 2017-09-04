package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class DeviceInfo extends JceStruct {
    public String apiLevel = "";
    public String appList = "";
    public String cpuInfo = "";
    public String imei = "";
    public long isRooted = 0;
    public String language = "";
    public String launcherName = "";
    public String manu = "";
    public String model = "";
    public String network = "";
    public String os = "";
    public String resolution = "";
    public String sdCard = "";
    public String sdDouble = "";
    public String sdkVersion = "";
    public String sdkVersionName = "";
    public String timezone = "";
    public String xgAppList = "";

    public DeviceInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, long j, String str12, String str13, String str14, String str15, String str16, String str17) {
        this.imei = str;
        this.model = str2;
        this.os = str3;
        this.network = str4;
        this.sdCard = str5;
        this.sdDouble = str6;
        this.resolution = str7;
        this.manu = str8;
        this.apiLevel = str9;
        this.sdkVersion = str10;
        this.sdkVersionName = str11;
        this.isRooted = j;
        this.appList = str12;
        this.cpuInfo = str13;
        this.language = str14;
        this.timezone = str15;
        this.launcherName = str16;
        this.xgAppList = str17;
    }

    public void writeTo(d dVar) {
        if (this.imei != null) {
            dVar.a(this.imei, 0);
        }
        if (this.model != null) {
            dVar.a(this.model, 1);
        }
        if (this.os != null) {
            dVar.a(this.os, 2);
        }
        if (this.network != null) {
            dVar.a(this.network, 3);
        }
        if (this.sdCard != null) {
            dVar.a(this.sdCard, 4);
        }
        if (this.sdDouble != null) {
            dVar.a(this.sdDouble, 5);
        }
        if (this.resolution != null) {
            dVar.a(this.resolution, 6);
        }
        if (this.manu != null) {
            dVar.a(this.manu, 7);
        }
        if (this.apiLevel != null) {
            dVar.a(this.apiLevel, 8);
        }
        if (this.sdkVersion != null) {
            dVar.a(this.sdkVersion, 9);
        }
        if (this.sdkVersionName != null) {
            dVar.a(this.sdkVersionName, 10);
        }
        dVar.a(this.isRooted, 11);
        if (this.appList != null) {
            dVar.a(this.appList, 12);
        }
        if (this.cpuInfo != null) {
            dVar.a(this.cpuInfo, 13);
        }
        if (this.language != null) {
            dVar.a(this.language, 14);
        }
        if (this.timezone != null) {
            dVar.a(this.timezone, 15);
        }
        if (this.launcherName != null) {
            dVar.a(this.launcherName, 16);
        }
        if (this.xgAppList != null) {
            dVar.a(this.xgAppList, 17);
        }
    }

    public void readFrom(c cVar) {
        this.imei = cVar.a(0, false);
        this.model = cVar.a(1, false);
        this.os = cVar.a(2, false);
        this.network = cVar.a(3, false);
        this.sdCard = cVar.a(4, false);
        this.sdDouble = cVar.a(5, false);
        this.resolution = cVar.a(6, false);
        this.manu = cVar.a(7, false);
        this.apiLevel = cVar.a(8, false);
        this.sdkVersion = cVar.a(9, false);
        this.sdkVersionName = cVar.a(10, false);
        this.isRooted = cVar.a(this.isRooted, 11, false);
        this.appList = cVar.a(12, false);
        this.cpuInfo = cVar.a(13, false);
        this.language = cVar.a(14, false);
        this.timezone = cVar.a(15, false);
        this.launcherName = cVar.a(16, false);
        this.xgAppList = cVar.a(17, false);
    }
}
