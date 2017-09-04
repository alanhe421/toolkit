package com.tencent.android.tpush.service.a;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.d.f;
import org.json.JSONObject;
import qalsdk.n;

/* compiled from: ProGuard */
public class a {
    private static a D = null;
    public int A = 60000;
    public int B = 1;
    public boolean C = true;
    private Context E = null;
    public long a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public String u;
    public int v;
    public int w;
    public String x = null;
    public int y = 1;
    public int z = 1;

    private a(Context context) {
        this.E = context.getApplicationContext();
        a();
    }

    public static a a(Context context) {
        if (D == null) {
            synchronized (a.class) {
                if (D == null) {
                    D = new a(context);
                }
            }
        }
        return D;
    }

    public String toString() {
        return "ConfigurationManager [context=" + this.E + ", configurationVersion=" + this.a + ", receiveTimeout=" + this.b + ", heartbeatInterval=" + this.c + ", httpHeartbeatInterval=" + this.d + ", speedTestInterval=" + this.e + ", channelMessageExpires=" + this.f + ", freqencySuccess=" + this.g + ", freqencyFailed=" + this.h + ", reportInterval=" + this.i + ", reportMaxCount=" + this.j + ", httpRetryCount=" + this.k + ", ackMaxCount=" + this.l + ", ackDuration=" + this.m + ", loadIpInerval=" + this.n + ", redirectConnectTimeOut=" + this.o + ", redirectSoTimeOut=" + this.p + ", strategyExpiredTime=" + this.q + ", logLevel=" + this.r + ", logFileSizeLimit=" + this.s + ", errCount=" + this.t + ", logUploadDomain=" + this.u + ", rptLive=" + this.v + ", rptLiveIntvl=" + this.w + ", disableXG=" + this.x + ", enableNewWd=" + this.y + ", enableMonitor=" + this.z + ", monitorFreg=" + this.A + ", enableReport=" + this.B + ", isXGServiceEnable=" + this.C + "]";
    }

    public void a() {
        if (this.a == 0) {
            this.a = b();
            this.b = a("recTo", (int) n.f);
            this.c = a("hbIntvl", 299980);
            this.d = a("httpHbIntvl", 600000);
            this.e = a("stIntvl", 54000000);
            this.f = a("cnMsgExp", 60000);
            this.g = a("fqcSuc", 10);
            this.h = a("fqcFal", 100);
            this.i = a("rptIntvl", 1200);
            this.j = a("rptMaxCnt", 5);
            this.k = a("httpRtCnt", 3);
            this.l = a("ackMaxCnt", 3);
            this.m = a("ackDuration", 180000);
            this.n = a("loadIpIntvl", 72000000);
            this.o = a("redirectConnectTime", (int) n.f);
            this.p = a("redirectSoTime", 20000);
            this.q = a("strategyExpiredTime", 1440);
            this.v = a("rptLive", 0);
            this.w = a("rptLiveIntvl", 3600);
            this.s = a("logFileSizeLimit", 262144);
            this.t = a("errCount", 5);
            this.u = a("logUploadDomain", "183.61.46.193");
            this.x = a("stopXG", "");
            this.y = a("enableNewWd", 1);
            this.B = a("report", 1);
            this.z = a("enable.monitor", 1);
            this.A = a("m.freq", 60000);
        }
    }

    private a() {
    }

    public void a(String str) {
        int i = n.f;
        int i2 = 3600;
        int i3 = 5;
        int i4 = 3;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.a = (long) a("confVer", jSONObject);
            this.a = this.a == 0 ? 1 : this.a;
            this.b = a("recTo", jSONObject) * 1000;
            this.b = this.b == 0 ? n.f : this.b;
            this.c = (a("hbIntvl", jSONObject) * 60) * 1000;
            this.c = this.c == 0 ? 299980 : this.c;
            this.d = (a("httpHbIntvl", jSONObject) * 60) * 1000;
            this.d = this.d == 0 ? 600000 : this.d;
            this.e = (a("stIntvl", jSONObject) * 60) * 1000;
            this.e = this.e == 0 ? 54000000 : this.e;
            this.f = a("cnMsgExp", jSONObject) * 1000;
            this.f = this.f == 0 ? 60000 : this.f;
            this.g = a("fqcSuc", jSONObject);
            this.g = this.g == 0 ? 10 : this.g;
            this.h = a("fqcFal", jSONObject);
            this.h = this.h == 0 ? 100 : this.h;
            this.i = a("rptIntvl", jSONObject);
            this.i = this.i == 0 ? 1200 : this.i;
            this.j = a("rptMaxCnt", jSONObject);
            this.j = this.j == 0 ? 5 : this.j;
            this.k = a("httpRtCnt", jSONObject);
            this.k = this.k == 0 ? 3 : this.k;
            this.l = a("ackMaxCnt", jSONObject);
            if (this.l != 0) {
                i4 = this.l;
            }
            this.l = i4;
            this.m = a("ackDuration", jSONObject) * 1000;
            this.m = this.m == 0 ? 180000 : this.m;
            this.n = ((a("loadIpIntvl", jSONObject) * 60) * 60) * 1000;
            this.n = this.n == 0 ? 72000000 : this.n;
            this.o = a("redirectConnectTime", jSONObject);
            if (this.o != 0) {
                i = this.o;
            }
            this.o = i;
            this.p = a("redirectSoTime", jSONObject);
            this.p = this.p == 0 ? 20000 : this.p;
            this.q = a("strategyExpiredTime", jSONObject);
            this.q = this.q == 0 ? 1440 : this.q;
            this.v = a("rptLive", jSONObject);
            this.v = this.v == 0 ? 0 : this.v;
            this.w = a("rptLiveIntvl", jSONObject);
            if (this.w != 3600) {
                i2 = this.w;
            }
            this.w = i2;
            this.r = a("logLevel", jSONObject);
            this.s = a("logFileSizeLimit", jSONObject) * 1024;
            this.s = this.s == 0 ? 262144 : this.s;
            this.t = a("errCount", jSONObject);
            if (this.t != 0) {
                i3 = this.t;
            }
            this.t = i3;
            this.u = b("logUploadDomain", jSONObject);
            this.u = TextUtils.isEmpty(this.u) ? "183.61.46.193" : this.u;
            this.y = jSONObject.optInt("enableNewWd", 1);
            this.B = jSONObject.optInt("report", 1);
            this.x = jSONObject.optString("stopXG", null);
            this.z = jSONObject.optInt("enable.monitor", 1);
            this.A = jSONObject.optInt("m.freq", 60000);
            Object optString = jSONObject.optString("st.kv", "");
            Object optString2 = jSONObject.optString("sp.kv", "");
            m.b(c(), b("confVer"), this.a);
            m.b(c(), b("recTo"), this.b);
            m.b(c(), b("hbIntvl"), this.c);
            m.b(c(), b("httpHbIntvl"), this.d);
            m.b(c(), b("stIntvl"), this.e);
            m.b(c(), b("cnMsgExp"), this.f);
            m.b(c(), b("fqcSuc"), this.g);
            m.b(c(), b("fqcFal"), this.h);
            m.b(c(), b("rptIntvl"), this.i);
            m.b(c(), b("rptMaxCnt"), this.j);
            m.b(c(), b("httpRtCnt"), this.k);
            m.b(c(), b("ackMaxCnt"), this.l);
            m.b(c(), b("ackDuration"), this.m);
            m.b(c(), b("loadIpIntvl"), this.n);
            m.b(c(), b("redirectConnectTime"), this.o);
            m.b(c(), b("redirectSoTime"), this.p);
            m.b(c(), b("strategyExpiredTime"), this.q);
            m.b(c(), b("rptLive"), this.v);
            m.b(c(), b("rptLiveIntvl"), this.w);
            m.b(c(), b("logLevel"), this.r);
            m.b(c(), b("logFileSizeLimit"), this.s);
            m.b(c(), b("errCount"), this.t);
            if (!f.a(this.x)) {
                m.b(c(), b("stopXG"), Rijndael.encrypt(this.x));
            }
            m.b(c(), b("enableNewWd"), this.y);
            m.b(c(), b("report"), this.B);
            m.b(c(), b("enable.monitor"), this.z);
            m.b(c(), b("m.freq"), this.A);
            if (!TextUtils.isEmpty(optString)) {
                b.b(c(), optString);
            }
            if (!TextUtils.isEmpty(optString2)) {
                b.a(c(), optString2);
            }
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "parseValue failed.", e);
        }
    }

    public long b() {
        if (this.E != null) {
            return m.a(this.E, b("confVer"), 1);
        }
        return 1;
    }

    public void a(long j) {
        if (this.E != null && b() != j) {
            m.b(this.E, b("confVer"), j);
        }
    }

    private Context c() {
        if (this.E != null) {
            return this.E;
        }
        if (c() != null) {
            this.E = com.tencent.android.tpush.service.m.e();
            return this.E;
        }
        if (XGPushManager.getContext() != null) {
            this.E = XGPushManager.getContext();
        }
        return this.E;
    }

    public int a(String str, int i) {
        return m.a(c(), b(str), i);
    }

    public String a(String str, String str2) {
        CharSequence a = m.a(c(), b(str), str2);
        return TextUtils.isEmpty(a) ? str2 : a;
    }

    public int a(String str, JSONObject jSONObject) {
        if (!(jSONObject == null || f.a(str))) {
            try {
                return jSONObject.getInt(str);
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "getJsonInt", e);
            }
        }
        return 0;
    }

    public String b(String str, JSONObject jSONObject) {
        if (!(jSONObject == null || f.a(str))) {
            try {
                return jSONObject.getString(str);
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "getJsonStr", e);
            }
        }
        return "";
    }

    public String b(String str) {
        return "com.tencent.tpus." + str;
    }
}
