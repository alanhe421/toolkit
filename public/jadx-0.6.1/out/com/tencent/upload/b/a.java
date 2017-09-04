package com.tencent.upload.b;

import android.text.TextUtils;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.open.SocialConstants;
import com.tencent.upload.common.Global;
import com.tencent.upload.common.e;
import com.tencent.upload.log.b;
import org.json.JSONObject;

public abstract class a {
    public String a = "qcloudimage";
    public String b = "";
    public String c = "";
    public String d = "";
    public int e;
    public String f = "";
    public int g = 0;
    public int h = 0;
    public String i = "";
    public long j = 0;
    public long k = 0;
    public int l = 0;
    public String m = "";
    public int n = 0;
    private int o = 2;
    private int p = 0;
    private String q = Global.clientIP;
    private String r = Global.SDK_VERSION;
    private String s = Global.getDeviceId();
    private long t = (System.currentTimeMillis() / 1000);
    private int u = 2;
    private String v = "";

    public a() {
        e.a();
        int c = e.c();
        if (1 == c) {
            this.p = 1;
        } else if (3 == c) {
            this.p = 2;
        } else if (2 == c) {
            this.p = 3;
        } else if (6 == c) {
            this.p = 4;
        } else {
            this.p = 0;
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String replaceAll;
        try {
            int indexOf = str.indexOf("?");
            if (indexOf > 0) {
                str = str.substring(0, indexOf);
            }
            replaceAll = str.replaceAll(",", "%2C");
        } catch (Exception e) {
            replaceAll = str;
        }
        return replaceAll;
    }

    public abstract int a();

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("terminal", this.o);
            jSONObject.put("network", this.p);
            jSONObject.put("deviceid", this.s);
            jSONObject.put("clientip", this.q);
            jSONObject.put("svrip", this.f);
            jSONObject.put("svrport", this.g);
            jSONObject.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, this.r);
            jSONObject.put("appid", this.a);
            jSONObject.put("biz", this.c);
            jSONObject.put("userid", this.b);
            jSONObject.put("interface", this.d);
            jSONObject.put("ret", this.h);
            jSONObject.put(SocialConstants.PARAM_SEND_MSG, this.i);
            jSONObject.put("time", this.t);
            jSONObject.put("size", this.j);
            jSONObject.put("cost", this.k);
            jSONObject.put("retry", this.l);
            jSONObject.put("refer", this.u);
            jSONObject.put(SocialConstants.PARAM_URL, a(this.m));
            jSONObject.put("flow", this.e);
            jSONObject.put("detect", this.n);
            jSONObject.put("extend", this.v);
        } catch (Throwable th) {
            b.c("ReportObj", "to json error!", th);
        }
        return jSONObject;
    }
}
