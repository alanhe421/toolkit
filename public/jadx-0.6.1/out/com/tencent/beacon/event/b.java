package com.tencent.beacon.event;

import android.content.Context;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.beacon.b.c;
import com.tencent.beacon.b.f;
import com.tencent.beacon.b.j;
import com.tencent.beacon.c.d.d;
import com.tencent.beacon.e.a;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.midas.api.APMidasPayAPI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ProGuard */
public final class b implements j {
    private Context a;
    private boolean b = false;
    private Object c = new Object();
    private Object d = new Object();
    private Object e = new Object();
    private List<k> f;
    private long g = BuglyBroadcastRecevier.UPLOADLIMITED;
    private Runnable h = new Runnable(this) {
        private /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.a();
        }
    };
    private Runnable i = new Runnable(this) {
        private /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.c();
        }
    };
    private Runnable j = new Runnable() {
        public final void run() {
            a.f(" db events to up", new Object[0]);
            try {
                o.d(false);
            } catch (Throwable th) {
                a.a(th);
            }
        }
    };

    public static com.tencent.beacon.c.d.b b(k kVar) {
        if (kVar == null || !"IP".equals(kVar.b())) {
            return null;
        }
        Map e = kVar.e();
        if (e == null) {
            return null;
        }
        try {
            com.tencent.beacon.c.d.b bVar = new com.tencent.beacon.c.d.b();
            bVar.a = (String) e.get("A19");
            String str = (String) e.get("A26");
            if (str == null) {
                str = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
            }
            bVar.e = Long.parseLong(str);
            String[] split = kVar.d().split(":");
            bVar.c = split[0];
            bVar.d = Integer.parseInt(split[1]);
            bVar.b = (String) e.get("A28");
            bVar.f = kVar.c();
            Map hashMap = new HashMap();
            hashMap.put("A33", e.get("A33"));
            hashMap.put("A3", e.get("A3"));
            hashMap.put("A20", e.get("A20"));
            hashMap.put("A74", e.get("A74"));
            if (e.get(APMidasPayAPI.ENV_TEST) != null) {
                hashMap.put(APMidasPayAPI.ENV_TEST, "Y");
            }
            bVar.g = com.tencent.beacon.net.a.a(hashMap);
            return bVar;
        } catch (Throwable th) {
            a.a(th);
            a.d(th.getMessage(), new Object[0]);
            return null;
        }
    }

    public b(Context context) {
        this.a = context;
        this.f = new ArrayList(25);
    }

    public static com.tencent.beacon.c.d.a c(k kVar) {
        if (kVar == null || !"DN".equals(kVar.b())) {
            return null;
        }
        Map e = kVar.e();
        if (e == null) {
            return null;
        }
        try {
            com.tencent.beacon.c.d.a aVar = new com.tencent.beacon.c.d.a();
            aVar.a = (String) e.get("A19");
            aVar.c = kVar.d();
            aVar.j = (String) e.get("A34");
            aVar.d = Long.parseLong((String) e.get("A35"));
            aVar.f = Long.parseLong((String) e.get("A36"));
            aVar.g = Long.parseLong((String) e.get("A37"));
            aVar.h = Long.parseLong((String) e.get("A38"));
            aVar.b = (String) e.get("A28");
            aVar.i = (String) e.get("A39");
            aVar.e = Long.parseLong((String) e.get("A40"));
            aVar.k = kVar.c();
            Map hashMap = new HashMap();
            hashMap.put("A33", e.get("A33"));
            hashMap.put("A3", e.get("A3"));
            hashMap.put("A20", e.get("A20"));
            hashMap.put("A74", e.get("A74"));
            if (e.get(APMidasPayAPI.ENV_TEST) != null) {
                hashMap.put(APMidasPayAPI.ENV_TEST, "Y");
            }
            aVar.l = com.tencent.beacon.net.a.a(hashMap);
            return aVar;
        } catch (Throwable th) {
            a.a(th);
            a.d(th.getMessage(), new Object[0]);
            return null;
        }
    }

    private synchronized List<k> b() {
        List<k> list;
        if (this.f == null || this.f.size() <= 0 || !d()) {
            list = null;
        } else {
            list = new ArrayList();
            list.addAll(this.f);
            this.f.clear();
            a.b(" get MN:" + list.size(), new Object[0]);
        }
        return list;
    }

    public final boolean a(k kVar) {
        synchronized (this.c) {
            String str = " BF eN:%s   isRT:%b  isCR:%b";
            Object[] objArr = new Object[3];
            objArr[0] = kVar == null ? "null" : kVar.d();
            objArr[1] = Boolean.valueOf(false);
            objArr[2] = Boolean.valueOf(kVar == null ? false : kVar.f());
            a.f(str, objArr);
            if (this.a == null || kVar == null || !this.b) {
                a.d(" err BF 1R", new Object[0]);
                return false;
            } else if (d()) {
                g j = o.d().j();
                int c = j.c();
                this.g = (long) (j.d() * 1000);
                int size = this.f.size();
                if (size >= c) {
                    a.f(" BF mN!", new Object[0]);
                    c.a().a(this.h);
                    c.a().a(102, this.h, this.g, this.g);
                }
                this.f.add(kVar);
                if (this.f.size() >= c) {
                    a.c(" err BF 3R! list size:" + size, new Object[0]);
                }
                o.d().d.a();
                a.a("CommonprocessUA:true!", new Object[0]);
                return true;
            } else {
                a.d(" CommonProcess processUA return false, isEnable is false !", new Object[0]);
                return false;
            }
        }
    }

    public static d d(k kVar) {
        if (kVar == null || !"HO".equals(kVar.b())) {
            return null;
        }
        Map e = kVar.e();
        if (e == null) {
            return null;
        }
        try {
            d dVar = new d();
            dVar.a = (String) e.get("A19");
            String[] split = ((String) e.get("hostip")).split(":");
            dVar.m = Integer.parseInt(split[1]);
            dVar.b = (String) e.get("A28");
            dVar.c = (String) e.get("A34");
            dVar.k = split[0];
            dVar.l = kVar.d();
            dVar.d = Long.parseLong((String) e.get("A35"));
            dVar.e = Long.parseLong((String) e.get("A40"));
            dVar.f = Long.parseLong((String) e.get("A36"));
            dVar.g = Long.parseLong((String) e.get("A37"));
            dVar.h = Long.parseLong((String) e.get("A38"));
            dVar.j = kVar.c();
            dVar.i = (String) e.get("A39");
            Map hashMap = new HashMap();
            hashMap.put("A33", e.get("A33"));
            hashMap.put("A3", e.get("A3"));
            hashMap.put("A20", e.get("A20"));
            hashMap.put("A74", e.get("A74"));
            dVar.n = com.tencent.beacon.net.a.a(hashMap);
            return dVar;
        } catch (Throwable th) {
            a.a(th);
            a.d(th.getMessage(), new Object[0]);
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void a() {
        /*
        r8 = this;
        r0 = 0;
        r1 = r8.d;
        monitor-enter(r1);
        r2 = r8.d();	 Catch:{ all -> 0x00b3 }
        if (r2 != 0) goto L_0x0015;
    L_0x000a:
        r0 = " err su 1R";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00b3 }
        com.tencent.beacon.e.a.c(r0, r2);	 Catch:{ all -> 0x00b3 }
        monitor-exit(r1);	 Catch:{ all -> 0x00b3 }
    L_0x0014:
        return;
    L_0x0015:
        r2 = r8.b();	 Catch:{ all -> 0x00b3 }
        if (r2 == 0) goto L_0x00c7;
    L_0x001b:
        r3 = r2.size();	 Catch:{ all -> 0x00b3 }
        if (r3 <= 0) goto L_0x00c7;
    L_0x0021:
        r3 = r8.a;	 Catch:{ all -> 0x00b3 }
        r2 = com.tencent.beacon.net.a.a(r3, r2);	 Catch:{ all -> 0x00b3 }
        if (r2 == 0) goto L_0x0033;
    L_0x0029:
        r3 = com.tencent.beacon.event.o.d();	 Catch:{ all -> 0x00b3 }
        r3 = r3.d;	 Catch:{ all -> 0x00b3 }
        r4 = r2.length;	 Catch:{ all -> 0x00b3 }
        r3.a(r4);	 Catch:{ all -> 0x00b3 }
    L_0x0033:
        r3 = r8.a;	 Catch:{ all -> 0x00b3 }
        r4 = com.tencent.beacon.upload.i.a(r3);	 Catch:{ all -> 0x00b3 }
        if (r2 == 0) goto L_0x00c4;
    L_0x003b:
        r2 = com.tencent.beacon.event.o.d();	 Catch:{ all -> 0x00b3 }
        r2 = r2.j();	 Catch:{ all -> 0x00b3 }
        r2 = r2.e();	 Catch:{ all -> 0x00b3 }
        r2 = (long) r2;	 Catch:{ all -> 0x00b3 }
        r5 = r8.a;	 Catch:{ all -> 0x00b3 }
        r5 = com.tencent.beacon.b.f.n(r5);	 Catch:{ all -> 0x00b3 }
        if (r5 == 0) goto L_0x0069;
    L_0x0050:
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b3 }
        r6 = " onwifi, so half mSZ ";
        r5.<init>(r6);	 Catch:{ all -> 0x00b3 }
        r5 = r5.append(r2);	 Catch:{ all -> 0x00b3 }
        r5 = r5.toString();	 Catch:{ all -> 0x00b3 }
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x00b3 }
        com.tencent.beacon.e.a.e(r5, r6);	 Catch:{ all -> 0x00b3 }
        r6 = 2;
        r2 = r2 / r6;
    L_0x0069:
        r5 = r8.a;	 Catch:{ all -> 0x00b3 }
        r5 = com.tencent.beacon.net.a.e(r5);	 Catch:{ all -> 0x00b3 }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b3 }
        r7 = "countCommomRecordNum: ";
        r6.<init>(r7);	 Catch:{ all -> 0x00b3 }
        r6 = r6.append(r5);	 Catch:{ all -> 0x00b3 }
        r6 = r6.toString();	 Catch:{ all -> 0x00b3 }
        r7 = 0;
        r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x00b3 }
        com.tencent.beacon.e.a.b(r6, r7);	 Catch:{ all -> 0x00b3 }
        r6 = (long) r5;	 Catch:{ all -> 0x00b3 }
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 < 0) goto L_0x008b;
    L_0x008a:
        r0 = 1;
    L_0x008b:
        if (r0 == 0) goto L_0x00c4;
    L_0x008d:
        r0 = r4.a();	 Catch:{ all -> 0x00b3 }
        if (r0 == 0) goto L_0x00c4;
    L_0x0093:
        r0 = r4.b();	 Catch:{ all -> 0x00b3 }
        if (r0 == 0) goto L_0x00c4;
    L_0x0099:
        r0 = com.tencent.beacon.event.o.d();	 Catch:{ all -> 0x00b3 }
        r0 = r0.j();	 Catch:{ all -> 0x00b3 }
        r0 = r0.o();	 Catch:{ all -> 0x00b3 }
        if (r0 != 0) goto L_0x00b6;
    L_0x00a7:
        r0 = " common max, not up by zeroPeak!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00b3 }
        com.tencent.beacon.e.a.e(r0, r2);	 Catch:{ all -> 0x00b3 }
        monitor-exit(r1);	 Catch:{ all -> 0x00b3 }
        goto L_0x0014;
    L_0x00b3:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x00b6:
        r0 = r8.j;	 Catch:{ all -> 0x00b3 }
        r0.run();	 Catch:{ all -> 0x00b3 }
        r0 = " common max up ";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00b3 }
        com.tencent.beacon.e.a.e(r0, r2);	 Catch:{ all -> 0x00b3 }
    L_0x00c4:
        monitor-exit(r1);	 Catch:{ all -> 0x00b3 }
        goto L_0x0014;
    L_0x00c7:
        r2 = com.tencent.beacon.event.o.d();	 Catch:{ all -> 0x00b3 }
        r2 = r2.j();	 Catch:{ all -> 0x00b3 }
        if (r2 == 0) goto L_0x00d5;
    L_0x00d1:
        r0 = r2.y();	 Catch:{ all -> 0x00b3 }
    L_0x00d5:
        if (r0 == 0) goto L_0x00c4;
    L_0x00d7:
        r0 = r8.j;	 Catch:{ all -> 0x00b3 }
        r0.run();	 Catch:{ all -> 0x00b3 }
        r0 = " common polling up";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00b3 }
        com.tencent.beacon.e.a.e(r0, r2);	 Catch:{ all -> 0x00b3 }
        goto L_0x00c4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.event.b.a():void");
    }

    public static com.tencent.beacon.c.b.a e(k kVar) {
        if (kVar == null || !"UA".equals(kVar.b())) {
            return null;
        }
        Map e = kVar.e();
        if (e == null) {
            return null;
        }
        try {
            com.tencent.beacon.c.b.a aVar = new com.tencent.beacon.c.b.a();
            aVar.a = (String) e.get("A19");
            aVar.c = kVar.d();
            aVar.h = kVar.c();
            aVar.b = (String) e.get("A28");
            aVar.f = Long.parseLong((String) e.get("A26"));
            aVar.d = Boolean.parseBoolean((String) e.get("A25"));
            aVar.e = Long.parseLong((String) e.get("A27"));
            if (kVar.h()) {
                e.put("C9", kVar.i());
            }
            kVar.f();
            aVar.g = com.tencent.beacon.net.a.a(e);
            kVar.f();
            aVar.i = 0;
            return aVar;
        } catch (Throwable th) {
            a.a(th);
            a.d(th.getMessage(), new Object[0]);
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c() {
        /*
        r3 = this;
        r1 = r3.e;
        monitor-enter(r1);
        r0 = r3.d();	 Catch:{ all -> 0x0034 }
        if (r0 != 0) goto L_0x0014;
    L_0x0009:
        r0 = " err su 1R";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0034 }
        com.tencent.beacon.e.a.c(r0, r2);	 Catch:{ all -> 0x0034 }
        monitor-exit(r1);	 Catch:{ all -> 0x0034 }
    L_0x0013:
        return;
    L_0x0014:
        r0 = r3.b();	 Catch:{ all -> 0x0034 }
        if (r0 == 0) goto L_0x0032;
    L_0x001a:
        r2 = r0.size();	 Catch:{ all -> 0x0034 }
        if (r2 <= 0) goto L_0x0032;
    L_0x0020:
        r2 = r3.a;	 Catch:{ all -> 0x0034 }
        r0 = com.tencent.beacon.net.a.a(r2, r0);	 Catch:{ all -> 0x0034 }
        if (r0 == 0) goto L_0x0032;
    L_0x0028:
        r2 = com.tencent.beacon.event.o.d();	 Catch:{ all -> 0x0034 }
        r2 = r2.d;	 Catch:{ all -> 0x0034 }
        r0 = r0.length;	 Catch:{ all -> 0x0034 }
        r2.a(r0);	 Catch:{ all -> 0x0034 }
    L_0x0032:
        monitor-exit(r1);	 Catch:{ all -> 0x0034 }
        goto L_0x0013;
    L_0x0034:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.event.b.c():void");
    }

    public static k a(Context context, String str, Map<String, String> map) {
        return a(context, str, true, 0, 0, map, true, false);
    }

    public static k a(Context context, String str, boolean z, long j, long j2, Map<String, String> map, boolean z2, boolean z3) {
        if (str == null) {
            return null;
        }
        com.tencent.beacon.b.d m = com.tencent.beacon.b.d.m();
        if (m == null) {
            a.d("  CommonInfo or DeviceInfo have not been Created return null!", new Object[0]);
            return null;
        }
        String userID = UserAction.getUserID();
        long h = m.h() + new Date().getTime();
        String g = m.g();
        String p = f.p(context);
        if (p == null) {
            p = "null";
        }
        Map hashMap = new HashMap();
        if (UserAction.a != null) {
            hashMap.putAll(UserAction.a);
        }
        if (map != null) {
            hashMap.putAll(map);
        }
        hashMap.put("A1", userID);
        hashMap.put(Constants.SOURCE_QQ, UserAction.getQQ());
        hashMap.put("A19", p);
        hashMap.put("A28", g);
        hashMap.put("A25", z);
        hashMap.put("A26", j);
        hashMap.put("A27", j2);
        hashMap.put("A2", m.i());
        d a = d.a(context);
        hashMap.put("A4", a.d());
        hashMap.put("A6", a.c());
        hashMap.put("A7", a.e());
        hashMap.put("A3", j.a(context).a());
        hashMap.put("A23", m.k());
        hashMap.put("A31", a.a());
        hashMap.put("A67", com.tencent.beacon.b.b.l(context));
        hashMap.put("A76", com.tencent.beacon.b.b.a());
        hashMap.put("A99", z2 ? "Y" : "N");
        if (o.d().a.A()) {
            hashMap.put("A100", f.a(context).a(str));
        }
        k kVar = new k();
        kVar.b(str);
        kVar.b(h);
        kVar.a("UA");
        kVar.a(hashMap);
        kVar.g();
        kVar.b(z3);
        int i = 0;
        if (j >= 1200000) {
            i = 1;
        }
        if (j2 >= 50000000) {
            i++;
        }
        if (i > 0) {
            kVar.c((long) i);
            kVar.a(true);
            return kVar;
        }
        kVar.c(0);
        kVar.a(false);
        return kVar;
    }

    private synchronized boolean d() {
        return this.b;
    }

    public final synchronized void a(boolean z) {
        if (this.b != z) {
            if (z) {
                this.b = z;
                this.g = (long) (o.d().j().d() * 1000);
                c.a().a(102, this.h, this.g, this.g);
            } else {
                c.a().a(102);
                c.a().a(112);
                b(true);
                this.b = z;
            }
        }
    }

    public final synchronized void b(boolean z) {
        a.e("common process flush memory objects to db.", new Object[0]);
        if (z) {
            c();
        } else {
            c.a().a(this.i);
        }
    }
}
