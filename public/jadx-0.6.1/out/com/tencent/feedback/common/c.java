package com.tencent.feedback.common;

import android.content.Context;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.feedback.proguard.a;
import com.tencent.feedback.proguard.o;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: RQDSRC */
public final class c {
    private static c E = null;
    private boolean A = true;
    private String B = null;
    private List<o> C;
    private Boolean D = null;
    private final long F;
    private int G = -1;
    private int H = -1;
    private Map<String, String> I = new HashMap();
    private Map<String, String> J = new HashMap();
    private final Context a;
    private final byte b;
    private String c;
    private String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private String j = "10000";
    private String k = ConfigBaseParser.DEFAULT_VALUE;
    private long l = 0;
    private String m = "";
    private String n = null;
    private String o = null;
    private String p = null;
    private String q = null;
    private String r = null;
    private String s = null;
    private String t = null;
    private long u = -1;
    private long v = -1;
    private long w = -1;
    private String x = null;
    private String y = null;
    private Map<String, PlugInInfo> z = null;

    private c(Context context) {
        Context context2;
        e.b("rqdp{  init cominfo}", new Object[0]);
        if (context == null) {
            context2 = context;
        } else {
            context2 = context.getApplicationContext();
            if (context2 == null) {
                context2 = context;
            }
        }
        this.a = context2;
        d.a(this.a);
        this.b = (byte) 1;
        this.c = a.b(context);
        this.d = a.c(context);
        this.e = "1.9.8.4";
        this.f = d.l();
        this.g = d.a();
        this.h = "Android " + d.b() + ",level " + d.c();
        this.g + VoiceWakeuperAidl.PARAMS_SEPARATE + this.h;
        this.F = new Date().getTime();
        this.i = a.i(this.a);
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (E == null) {
                E = new c(context);
            }
            cVar = E;
        }
        return cVar;
    }

    public final byte a() {
        return (byte) 1;
    }

    public final synchronized String b() {
        return this.c;
    }

    public final String c() {
        return this.e;
    }

    public final String d() {
        return this.f;
    }

    public final String e() {
        return this.g;
    }

    public final String f() {
        return this.h;
    }

    public final synchronized String g() {
        return this.j;
    }

    public final synchronized void a(String str) {
        if (str != null) {
            if (!(str.trim().length() == 0 || str.equals("10000"))) {
                this.j = str;
            }
        }
    }

    public final synchronized String h() {
        return this.m;
    }

    public final synchronized void b(String str) {
        this.m = str;
        if (str != null) {
            b(false);
        }
    }

    public final synchronized String i() {
        return this.k;
    }

    public final synchronized void c(String str) {
        this.k = str;
    }

    public final synchronized long j() {
        return this.l;
    }

    public final synchronized void a(long j) {
        this.l = j;
    }

    public final synchronized String k() {
        if (this.n == null) {
            this.n = a.d(this.a);
        }
        return this.n;
    }

    public final synchronized String l() {
        if (this.p == null) {
            this.p = a.h(this.a);
        }
        return this.p;
    }

    public final synchronized boolean m() {
        return this.p != null;
    }

    public final synchronized void d(String str) {
        this.p = str;
    }

    public final synchronized String n() {
        if (this.q == null) {
            this.q = a.a(this.a);
            if (this.q == null || this.q.equals("")) {
                this.q = this.c;
            }
        }
        return this.q;
    }

    public final synchronized void e(String str) {
        this.q = str;
    }

    public final synchronized String o() {
        String str;
        if (N()) {
            if (this.o == null) {
                d.a(this.a);
                this.o = d.b(this.a);
            }
            str = this.o;
        } else {
            str = "";
        }
        return str;
    }

    public final synchronized String p() {
        String str;
        if (N()) {
            if (this.r == null) {
                d.a(this.a);
                this.r = d.e(this.a);
            }
            str = this.r;
        } else {
            str = "";
        }
        return str;
    }

    public final synchronized String q() {
        String str;
        if (N()) {
            if (this.s == null) {
                d.a(this.a);
                this.s = d.c(this.a);
            }
            str = this.s;
        } else {
            str = "";
        }
        return str;
    }

    public final synchronized String r() {
        String str;
        if (N()) {
            if (this.t == null) {
                d.a(this.a);
                this.t = d.d(this.a);
            }
            str = this.t;
        } else {
            str = "";
        }
        return str;
    }

    public final synchronized long s() {
        if (this.u <= 0) {
            d.a(this.a);
            this.u = d.e();
        }
        return this.u;
    }

    public final synchronized long t() {
        if (this.v <= 0) {
            d.a(this.a);
            this.v = d.g();
        }
        return this.v;
    }

    public final synchronized long u() {
        if (this.w <= 0) {
            this.w = d.a(this.a).i();
        }
        return this.w;
    }

    public final synchronized String v() {
        if (this.x == null) {
            d.a(this.a);
            this.x = d.d();
        }
        return this.x;
    }

    public final synchronized String w() {
        if (this.y == null) {
            d.a(this.a);
            this.y = a.c("ro.board.platform");
        }
        return this.y;
    }

    public final synchronized Map<String, PlugInInfo> x() {
        return this.z;
    }

    private synchronized boolean N() {
        return this.A;
    }

    private synchronized void b(boolean z) {
        this.A = false;
    }

    public final synchronized Map<String, PlugInInfo> y() {
        Map<String, PlugInInfo> map;
        if (this.z == null || this.z.size() <= 0) {
            map = null;
        } else {
            map = new HashMap(this.z.size());
            map.putAll(this.z);
        }
        return map;
    }

    public final synchronized boolean a(String str, String str2, String str3) {
        boolean z = true;
        synchronized (this) {
            if (str == null || str2 == null || str3 == null) {
                z = false;
            } else {
                if (this.z == null) {
                    this.z = new HashMap();
                }
                this.z.put(str, new PlugInInfo(str, str2, str3));
                e.a("add %s %s %s", str, str2, str3);
            }
        }
        return z;
    }

    public final synchronized void f(String str) {
        if (str != null) {
            if (this.z != null) {
                this.z.remove(str);
            }
        }
    }

    public final String z() {
        if (this.B == null) {
            d.a(this.a);
            this.B = d.k();
        }
        return this.B;
    }

    public final void g(String str) {
        this.B = str;
    }

    public final synchronized List<o> A() {
        return this.C;
    }

    public final synchronized void a(List<o> list) {
        this.C = list;
    }

    public final synchronized void h(String str) {
        this.d = str;
    }

    public final synchronized String B() {
        return this.d;
    }

    public final synchronized void a(boolean z) {
        this.D = Boolean.valueOf(z);
    }

    public final synchronized boolean C() {
        if (this.D == null) {
            this.D = Boolean.valueOf(h.a().b());
        }
        return this.D.booleanValue();
    }

    public final long D() {
        return this.F;
    }

    public final String E() {
        return this.i;
    }

    public final synchronized void i(String str) {
        this.c = str;
    }

    public final synchronized void a(int i) {
        if (this.G != i) {
            this.G = i;
            e.a("user scene tag %d changed to tag %d", Integer.valueOf(r0), Integer.valueOf(this.G));
        }
    }

    public final synchronized int F() {
        return this.G;
    }

    public final synchronized int G() {
        return this.H;
    }

    public final synchronized Map<String, String> H() {
        Map<String, String> map;
        if (this.I.size() <= 0) {
            map = null;
        } else {
            map = new HashMap(this.I);
        }
        return map;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String j(java.lang.String r5) {
        /*
        r4 = this;
        r1 = 1;
        r0 = 0;
        monitor-enter(r4);
        if (r5 == 0) goto L_0x002d;
    L_0x0005:
        r2 = r5.trim();	 Catch:{ all -> 0x0038 }
        r2 = r2.length();	 Catch:{ all -> 0x0038 }
        if (r2 <= 0) goto L_0x002d;
    L_0x000f:
        if (r0 == 0) goto L_0x002f;
    L_0x0011:
        r0 = "key should not be empty %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0038 }
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0038 }
        r3.<init>();	 Catch:{ all -> 0x0038 }
        r3 = r3.append(r5);	 Catch:{ all -> 0x0038 }
        r3 = r3.toString();	 Catch:{ all -> 0x0038 }
        r1[r2] = r3;	 Catch:{ all -> 0x0038 }
        com.tencent.feedback.common.e.c(r0, r1);	 Catch:{ all -> 0x0038 }
        r0 = 0;
    L_0x002b:
        monitor-exit(r4);
        return r0;
    L_0x002d:
        r0 = r1;
        goto L_0x000f;
    L_0x002f:
        r0 = r4.I;	 Catch:{ all -> 0x0038 }
        r0 = r0.remove(r5);	 Catch:{ all -> 0x0038 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0038 }
        goto L_0x002b;
    L_0x0038:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.feedback.common.c.j(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String k(java.lang.String r5) {
        /*
        r4 = this;
        r1 = 1;
        r0 = 0;
        monitor-enter(r4);
        if (r5 == 0) goto L_0x002d;
    L_0x0005:
        r2 = r5.trim();	 Catch:{ all -> 0x0038 }
        r2 = r2.length();	 Catch:{ all -> 0x0038 }
        if (r2 <= 0) goto L_0x002d;
    L_0x000f:
        if (r0 == 0) goto L_0x002f;
    L_0x0011:
        r0 = "key should not be empty %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0038 }
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0038 }
        r3.<init>();	 Catch:{ all -> 0x0038 }
        r3 = r3.append(r5);	 Catch:{ all -> 0x0038 }
        r3 = r3.toString();	 Catch:{ all -> 0x0038 }
        r1[r2] = r3;	 Catch:{ all -> 0x0038 }
        com.tencent.feedback.common.e.c(r0, r1);	 Catch:{ all -> 0x0038 }
        r0 = 0;
    L_0x002b:
        monitor-exit(r4);
        return r0;
    L_0x002d:
        r0 = r1;
        goto L_0x000f;
    L_0x002f:
        r0 = r4.I;	 Catch:{ all -> 0x0038 }
        r0 = r0.get(r5);	 Catch:{ all -> 0x0038 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0038 }
        goto L_0x002b;
    L_0x0038:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.feedback.common.c.k(java.lang.String):java.lang.String");
    }

    public final synchronized void a(String str, String str2) {
        Object obj = null;
        synchronized (this) {
            Object obj2;
            if (str != null) {
                if (str.trim().length() > 0) {
                    obj2 = null;
                    if (obj2 == null) {
                        if (str2 == null || str2.trim().length() <= 0) {
                            int i = 1;
                        }
                        if (obj == null) {
                            this.I.put(str, str2);
                        }
                    }
                    e.c("key&value should not be empty %s %s", str, str2);
                }
            }
            int i2 = 1;
            if (obj2 == null) {
                int i3 = 1;
                if (obj == null) {
                    this.I.put(str, str2);
                }
            }
            e.c("key&value should not be empty %s %s", str, str2);
        }
    }

    public final synchronized int I() {
        return this.I.size();
    }

    public final synchronized Set<String> J() {
        return this.I.keySet();
    }

    public final synchronized Map<String, String> K() {
        Map<String, String> map;
        if (this.J.size() <= 0) {
            map = null;
        } else {
            map = new HashMap(this.J);
        }
        return map;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String l(java.lang.String r5) {
        /*
        r4 = this;
        r1 = 1;
        r0 = 0;
        monitor-enter(r4);
        if (r5 == 0) goto L_0x002d;
    L_0x0005:
        r2 = r5.trim();	 Catch:{ all -> 0x0038 }
        r2 = r2.length();	 Catch:{ all -> 0x0038 }
        if (r2 <= 0) goto L_0x002d;
    L_0x000f:
        if (r0 == 0) goto L_0x002f;
    L_0x0011:
        r0 = "key should not be empty %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0038 }
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0038 }
        r3.<init>();	 Catch:{ all -> 0x0038 }
        r3 = r3.append(r5);	 Catch:{ all -> 0x0038 }
        r3 = r3.toString();	 Catch:{ all -> 0x0038 }
        r1[r2] = r3;	 Catch:{ all -> 0x0038 }
        com.tencent.feedback.common.e.c(r0, r1);	 Catch:{ all -> 0x0038 }
        r0 = 0;
    L_0x002b:
        monitor-exit(r4);
        return r0;
    L_0x002d:
        r0 = r1;
        goto L_0x000f;
    L_0x002f:
        r0 = r4.J;	 Catch:{ all -> 0x0038 }
        r0 = r0.get(r5);	 Catch:{ all -> 0x0038 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0038 }
        goto L_0x002b;
    L_0x0038:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.feedback.common.c.l(java.lang.String):java.lang.String");
    }

    public final synchronized int L() {
        return this.J.size();
    }

    public final synchronized Set<String> M() {
        return this.J.keySet();
    }
}
