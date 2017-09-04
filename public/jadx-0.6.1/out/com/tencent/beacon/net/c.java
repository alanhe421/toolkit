package com.tencent.beacon.net;

import android.content.Context;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.beacon.b.d;
import com.tencent.beacon.b.f;
import com.tencent.beacon.b.j;
import com.tencent.beacon.e.a;
import com.tencent.beacon.event.k;
import com.tencent.beacon.net.a.b;
import com.tencent.midas.api.APMidasPayAPI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ProGuard */
public final class c implements Runnable {
    private Context a;
    private List<b> b;

    public c(Context context, List<b> list) {
        this.a = context;
        this.b = list;
    }

    public final void run() {
        if (this.a != null && this.b != null) {
            d m = d.m();
            if (m == null) {
                a.d(" common info null", new Object[0]);
                return;
            }
            long h;
            String g;
            String str;
            Object obj;
            Map hashMap;
            k kVar;
            StringBuilder stringBuilder;
            b a;
            long currentTimeMillis;
            k kVar2;
            String trim;
            String[] split;
            String str2;
            Context context;
            long time;
            synchronized (m) {
                h = m.h();
                g = m.g();
            }
            String p = f.p(this.a);
            if (p == null) {
                str = "null";
            } else {
                str = p;
            }
            p = "null";
            if (f.a(this.a) != null) {
                p = f.l(this.a);
                if (p == null) {
                    obj = "null";
                    hashMap = new HashMap();
                    for (b bVar : this.b) {
                        a.a(" check " + bVar.a(), new Object[0]);
                        a.b(" address:" + bVar.b(), new Object[0]);
                        kVar = null;
                        hashMap.clear();
                        hashMap.put("A28", g);
                        hashMap.put("A3", j.a(this.a).a());
                        hashMap.put("A33", obj);
                        stringBuilder = new StringBuilder();
                        f.a(this.a);
                        hashMap.put("A20", stringBuilder.append(f.f(this.a)).toString());
                        stringBuilder = new StringBuilder();
                        f.a(this.a);
                        hashMap.put("A74", stringBuilder.append(f.m(this.a)).toString());
                        if (!"PG".equals(bVar.a())) {
                            if (bVar.f()) {
                                hashMap.put(APMidasPayAPI.ENV_TEST, "Y");
                            }
                            if (str.toLowerCase().contains("wap")) {
                                a = a.a(bVar.b(), bVar.e());
                            } else {
                                a.a(" using wap request", new Object[0]);
                                a = a.a(bVar.b(), str);
                            }
                            if (a == null) {
                                a = new b();
                            }
                            if ((((a.a + a.b) + a.c) + a.d) + a.e <= 0) {
                                a.a(" elapse bean is not avilable!try apach}", new Object[0]);
                                currentTimeMillis = System.currentTimeMillis();
                                if (a(bVar.b(), " ".getBytes(), "post", str) != null) {
                                    a.e = System.currentTimeMillis() - currentTimeMillis;
                                    a.a(" get a total time}" + a.e, new Object[0]);
                                }
                            }
                            kVar2 = new k();
                            kVar2.b(bVar.b());
                            currentTimeMillis = new Date().getTime();
                            a.a(" loc time:}" + currentTimeMillis, new Object[0]);
                            currentTimeMillis += h;
                            a.a(" to stime:}" + currentTimeMillis, new Object[0]);
                            kVar2.b(currentTimeMillis);
                            kVar2.a("DN");
                            hashMap.put("A19", str);
                            hashMap.put("A40", a.b);
                            hashMap.put("A34", ConfigBaseParser.DEFAULT_VALUE);
                            hashMap.put("A39", a.f);
                            hashMap.put("A35", a.a);
                            hashMap.put("A36", a.c);
                            hashMap.put("A38", a.e);
                            hashMap.put("A37", a.d);
                            kVar2.a(hashMap);
                            kVar = kVar2;
                        } else if ("IP".equals(bVar.a())) {
                            trim = bVar.b().trim();
                            split = trim.split(":");
                            if (split != null || split.length != 2) {
                                a.d(" ip dest wrong }" + trim, new Object[0]);
                                break;
                            }
                            if (bVar.f()) {
                                hashMap.put(APMidasPayAPI.ENV_TEST, "Y");
                            }
                            currentTimeMillis = a.a(split[0], Integer.parseInt(split[1]));
                            kVar = new k();
                            kVar.b(bVar.b());
                            long time2 = new Date().getTime();
                            a.a(" loc time:}" + time2, new Object[0]);
                            time2 += h;
                            a.a(" to stime:}" + time2, new Object[0]);
                            kVar.b(time2);
                            kVar.a("IP");
                            hashMap.put("A19", str);
                            hashMap.put("A26", currentTimeMillis);
                            kVar.a(hashMap);
                        } else if ("HOST".equals(bVar.a())) {
                            str2 = "http://" + bVar.c() + bVar.b();
                            a.a(" host domain test:" + str2, new Object[0]);
                            if (str.toLowerCase().contains("wap")) {
                                a = a.a(bVar.d(), bVar.c(), bVar.b());
                            } else {
                                a.a(" using wap request", new Object[0]);
                                a = a.a(str2, str);
                            }
                            if (a == null) {
                                a = new b();
                            }
                            if ((((a.a + a.b) + a.c) + a.d) + a.e <= 0) {
                                a.a(" elapse bean is not avilable!try apach", new Object[0]);
                                currentTimeMillis = System.currentTimeMillis();
                                if (a(str2, " ".getBytes(), "post", str) != null) {
                                    a.e = System.currentTimeMillis() - currentTimeMillis;
                                    a.a(" get a total time}" + a.e, new Object[0]);
                                }
                            }
                            kVar2 = new k();
                            kVar2.b(bVar.b());
                            currentTimeMillis = new Date().getTime();
                            a.a("loc time:" + currentTimeMillis, new Object[0]);
                            currentTimeMillis += h;
                            a.a("to stime:" + currentTimeMillis, new Object[0]);
                            kVar2.b(currentTimeMillis);
                            kVar2.a("HO");
                            hashMap.put("A19", str);
                            hashMap.put("A40", a.b);
                            hashMap.put("A34", bVar.c());
                            hashMap.put("hostip", bVar.d());
                            hashMap.put("A39", a.f);
                            hashMap.put("A35", a.a);
                            hashMap.put("A36", a.c);
                            hashMap.put("A38", a.e);
                            hashMap.put("A37", a.d);
                            kVar2.a(hashMap);
                            kVar = kVar2;
                        }
                        if (kVar != null) {
                            a.a(" insert record type " + bVar.a(), new Object[0]);
                            if (!a.a(this.a, kVar)) {
                                a.a(" insert record fail!", new Object[0]);
                            }
                        }
                    }
                    context = this.a;
                    time = new Date().getTime();
                    a.a(" MonitorDAO.deleteSpeedMonitorSources() start}", new Object[0]);
                    if (context != null) {
                        a.a(" MonitorDAO.deleteSpeedMonitorSources() context is null arg}", new Object[0]);
                    }
                    com.tencent.beacon.b.a.a.a(context, new int[]{5}, time);
                    return;
                }
            }
            String str3 = p;
            hashMap = new HashMap();
            for (b bVar2 : this.b) {
                a.a(" check " + bVar2.a(), new Object[0]);
                a.b(" address:" + bVar2.b(), new Object[0]);
                kVar = null;
                hashMap.clear();
                hashMap.put("A28", g);
                hashMap.put("A3", j.a(this.a).a());
                hashMap.put("A33", obj);
                stringBuilder = new StringBuilder();
                f.a(this.a);
                hashMap.put("A20", stringBuilder.append(f.f(this.a)).toString());
                stringBuilder = new StringBuilder();
                f.a(this.a);
                hashMap.put("A74", stringBuilder.append(f.m(this.a)).toString());
                if (!"PG".equals(bVar2.a())) {
                    if ("IP".equals(bVar2.a())) {
                        trim = bVar2.b().trim();
                        split = trim.split(":");
                        if (split != null) {
                        }
                        a.d(" ip dest wrong }" + trim, new Object[0]);
                        break;
                    } else if ("HOST".equals(bVar2.a())) {
                        str2 = "http://" + bVar2.c() + bVar2.b();
                        a.a(" host domain test:" + str2, new Object[0]);
                        if (str.toLowerCase().contains("wap")) {
                            a = a.a(bVar2.d(), bVar2.c(), bVar2.b());
                        } else {
                            a.a(" using wap request", new Object[0]);
                            a = a.a(str2, str);
                        }
                        if (a == null) {
                            a = new b();
                        }
                        if ((((a.a + a.b) + a.c) + a.d) + a.e <= 0) {
                            a.a(" elapse bean is not avilable!try apach", new Object[0]);
                            currentTimeMillis = System.currentTimeMillis();
                            if (a(str2, " ".getBytes(), "post", str) != null) {
                                a.e = System.currentTimeMillis() - currentTimeMillis;
                                a.a(" get a total time}" + a.e, new Object[0]);
                            }
                        }
                        kVar2 = new k();
                        kVar2.b(bVar2.b());
                        currentTimeMillis = new Date().getTime();
                        a.a("loc time:" + currentTimeMillis, new Object[0]);
                        currentTimeMillis += h;
                        a.a("to stime:" + currentTimeMillis, new Object[0]);
                        kVar2.b(currentTimeMillis);
                        kVar2.a("HO");
                        hashMap.put("A19", str);
                        hashMap.put("A40", a.b);
                        hashMap.put("A34", bVar2.c());
                        hashMap.put("hostip", bVar2.d());
                        hashMap.put("A39", a.f);
                        hashMap.put("A35", a.a);
                        hashMap.put("A36", a.c);
                        hashMap.put("A38", a.e);
                        hashMap.put("A37", a.d);
                        kVar2.a(hashMap);
                        kVar = kVar2;
                    }
                } else {
                    if (bVar2.f()) {
                        hashMap.put(APMidasPayAPI.ENV_TEST, "Y");
                    }
                    if (str.toLowerCase().contains("wap")) {
                        a = a.a(bVar2.b(), bVar2.e());
                    } else {
                        a.a(" using wap request", new Object[0]);
                        a = a.a(bVar2.b(), str);
                    }
                    if (a == null) {
                        a = new b();
                    }
                    if ((((a.a + a.b) + a.c) + a.d) + a.e <= 0) {
                        a.a(" elapse bean is not avilable!try apach}", new Object[0]);
                        currentTimeMillis = System.currentTimeMillis();
                        if (a(bVar2.b(), " ".getBytes(), "post", str) != null) {
                            a.e = System.currentTimeMillis() - currentTimeMillis;
                            a.a(" get a total time}" + a.e, new Object[0]);
                        }
                    }
                    kVar2 = new k();
                    kVar2.b(bVar2.b());
                    currentTimeMillis = new Date().getTime();
                    a.a(" loc time:}" + currentTimeMillis, new Object[0]);
                    currentTimeMillis += h;
                    a.a(" to stime:}" + currentTimeMillis, new Object[0]);
                    kVar2.b(currentTimeMillis);
                    kVar2.a("DN");
                    hashMap.put("A19", str);
                    hashMap.put("A40", a.b);
                    hashMap.put("A34", ConfigBaseParser.DEFAULT_VALUE);
                    hashMap.put("A39", a.f);
                    hashMap.put("A35", a.a);
                    hashMap.put("A36", a.c);
                    hashMap.put("A38", a.e);
                    hashMap.put("A37", a.d);
                    kVar2.a(hashMap);
                    kVar = kVar2;
                }
                if (kVar != null) {
                    a.a(" insert record type " + bVar2.a(), new Object[0]);
                    if (!a.a(this.a, kVar)) {
                        a.a(" insert record fail!", new Object[0]);
                    }
                }
            }
            context = this.a;
            time = new Date().getTime();
            a.a(" MonitorDAO.deleteSpeedMonitorSources() start}", new Object[0]);
            if (context != null) {
                com.tencent.beacon.b.a.a.a(context, new int[]{5}, time);
                return;
            }
            a.a(" MonitorDAO.deleteSpeedMonitorSources() context is null arg}", new Object[0]);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] a(java.lang.String r7, byte[] r8, java.lang.String r9, java.lang.String r10) {
        /*
        r0 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r2 = 0;
        r6 = 0;
        r3 = new org.apache.http.params.BasicHttpParams;
        r3.<init>();
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r3, r0);
        org.apache.http.params.HttpConnectionParams.setSoTimeout(r3, r0);
        r4 = new org.apache.http.entity.ByteArrayEntity;
        r4.<init>(r8);
        r0 = r9.toLowerCase();
        r1 = "post";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0092;
    L_0x0022:
        r1 = new org.apache.http.client.methods.HttpPost;
        r1.<init>(r7);
        r0 = r1;
        r0 = (org.apache.http.client.methods.HttpPost) r0;
        r0.setEntity(r4);
    L_0x002d:
        r0 = new org.apache.http.impl.client.DefaultHttpClient;	 Catch:{ Throwable -> 0x0159 }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x0159 }
        if (r10 == 0) goto L_0x006e;
    L_0x0034:
        r3 = r10.toLowerCase();	 Catch:{ Throwable -> 0x0159 }
        r4 = "wap";
        r3 = r3.contains(r4);	 Catch:{ Throwable -> 0x0159 }
        if (r3 == 0) goto L_0x006e;
    L_0x0041:
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0159 }
        r4 = " use proxy }";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x0159 }
        r3 = r3.append(r10);	 Catch:{ Throwable -> 0x0159 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x0159 }
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x0159 }
        com.tencent.beacon.e.a.a(r3, r4);	 Catch:{ Throwable -> 0x0159 }
        r3 = android.net.Proxy.getDefaultHost();	 Catch:{ Throwable -> 0x0159 }
        r4 = android.net.Proxy.getDefaultPort();	 Catch:{ Throwable -> 0x0159 }
        r5 = new org.apache.http.HttpHost;	 Catch:{ Throwable -> 0x0159 }
        r5.<init>(r3, r4);	 Catch:{ Throwable -> 0x0159 }
        r3 = r0.getParams();	 Catch:{ Throwable -> 0x0159 }
        r4 = "http.route.default-proxy";
        r3.setParameter(r4, r5);	 Catch:{ Throwable -> 0x0159 }
    L_0x006e:
        r3 = " do request!}";
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x0159 }
        com.tencent.beacon.e.a.a(r3, r4);	 Catch:{ Throwable -> 0x0159 }
        r0 = r0.execute(r1);	 Catch:{ Throwable -> 0x0159 }
        r1 = r0.getStatusLine();	 Catch:{ Throwable -> 0x0159 }
        r1 = r1.getStatusCode();	 Catch:{ Throwable -> 0x0159 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 == r3) goto L_0x00c4;
    L_0x0087:
        r0 = " request failure!}";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0159 }
        com.tencent.beacon.e.a.d(r0, r1);	 Catch:{ Throwable -> 0x0159 }
        r0 = r2;
    L_0x0091:
        return r0;
    L_0x0092:
        if (r8 != 0) goto L_0x00ae;
    L_0x0094:
        r0 = "";
    L_0x0097:
        r1 = new org.apache.http.client.methods.HttpGet;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4 = r4.append(r7);
        r0 = r4.append(r0);
        r0 = r0.toString();
        r1.<init>(r0);
        goto L_0x002d;
    L_0x00ae:
        r0 = new java.lang.StringBuilder;
        r1 = "?";
        r0.<init>(r1);
        r1 = new java.lang.String;
        r1.<init>(r8);
        r0 = r0.append(r1);
        r0 = r0.toString();
        goto L_0x0097;
    L_0x00c4:
        r0 = r0.getEntity();	 Catch:{ Throwable -> 0x0159 }
        if (r0 != 0) goto L_0x00d5;
    L_0x00ca:
        r0 = " no response!}";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0159 }
        com.tencent.beacon.e.a.d(r0, r1);	 Catch:{ Throwable -> 0x0159 }
        r0 = r2;
        goto L_0x0091;
    L_0x00d5:
        r1 = " get response and read!}";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0159 }
        com.tencent.beacon.e.a.a(r1, r3);	 Catch:{ Throwable -> 0x0159 }
        r3 = new java.io.DataInputStream;	 Catch:{ Throwable -> 0x0159 }
        r0 = r0.getContent();	 Catch:{ Throwable -> 0x0159 }
        r3.<init>(r0);	 Catch:{ Throwable -> 0x0159 }
        r0 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x00f7, all -> 0x0156 }
        r0.<init>();	 Catch:{ Throwable -> 0x00f7, all -> 0x0156 }
    L_0x00ec:
        r1 = r3.read();	 Catch:{ Throwable -> 0x00f7, all -> 0x0156 }
        r4 = -1;
        if (r1 == r4) goto L_0x011d;
    L_0x00f3:
        r0.write(r1);	 Catch:{ Throwable -> 0x00f7, all -> 0x0156 }
        goto L_0x00ec;
    L_0x00f7:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        r2 = r3;
    L_0x00fb:
        com.tencent.beacon.e.a.a(r1);	 Catch:{ all -> 0x0141 }
        r1 = r1.getMessage();	 Catch:{ all -> 0x0141 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0141 }
        com.tencent.beacon.e.a.d(r1, r3);	 Catch:{ all -> 0x0141 }
        if (r2 == 0) goto L_0x0091;
    L_0x010a:
        r2.close();	 Catch:{ IOException -> 0x010e }
        goto L_0x0091;
    L_0x010e:
        r1 = move-exception;
        com.tencent.beacon.e.a.a(r1);
        r1 = r1.getMessage();
        r2 = new java.lang.Object[r6];
        com.tencent.beacon.e.a.d(r1, r2);
        goto L_0x0091;
    L_0x011d:
        r0.flush();	 Catch:{ Throwable -> 0x00f7, all -> 0x0156 }
        r0 = r0.toByteArray();	 Catch:{ Throwable -> 0x00f7, all -> 0x0156 }
        r1 = " read end!}";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x015d, all -> 0x0156 }
        com.tencent.beacon.e.a.a(r1, r2);	 Catch:{ Throwable -> 0x015d, all -> 0x0156 }
        r3.close();	 Catch:{ IOException -> 0x0132 }
        goto L_0x0091;
    L_0x0132:
        r1 = move-exception;
        com.tencent.beacon.e.a.a(r1);
        r1 = r1.getMessage();
        r2 = new java.lang.Object[r6];
        com.tencent.beacon.e.a.d(r1, r2);
        goto L_0x0091;
    L_0x0141:
        r0 = move-exception;
    L_0x0142:
        if (r2 == 0) goto L_0x0147;
    L_0x0144:
        r2.close();	 Catch:{ IOException -> 0x0148 }
    L_0x0147:
        throw r0;
    L_0x0148:
        r1 = move-exception;
        com.tencent.beacon.e.a.a(r1);
        r1 = r1.getMessage();
        r2 = new java.lang.Object[r6];
        com.tencent.beacon.e.a.d(r1, r2);
        goto L_0x0147;
    L_0x0156:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0142;
    L_0x0159:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        goto L_0x00fb;
    L_0x015d:
        r1 = move-exception;
        r2 = r3;
        goto L_0x00fb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.net.c.a(java.lang.String, byte[], java.lang.String, java.lang.String):byte[]");
    }
}
