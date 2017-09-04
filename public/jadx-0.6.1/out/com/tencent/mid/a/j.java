package com.tencent.mid.a;

import android.content.Context;
import com.tencent.mid.api.MidCallback;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.b.a;
import com.tencent.mid.b.g;
import com.tencent.mid.util.Util;
import com.tencent.mid.util.f;
import com.tencent.mid.util.i;
import java.util.ArrayList;
import java.util.Arrays;

public class j implements Runnable {
    private Context a = null;
    private MidCallback b = null;
    private int c = 0;
    private f d = null;

    public j(Context context, int i, MidCallback midCallback) {
        this.a = context;
        this.c = i;
        this.b = midCallback;
        this.d = Util.getLogger();
    }

    private void a() {
        MidEntity a = g.a(this.a).a(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(2)})));
        MidEntity a2 = g.a(this.a).a(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(4)})));
        if (Util.equal(a2, a)) {
            this.d.d("local mid check passed.");
            return;
        }
        a = Util.getNewerMidEntity(a2, a);
        this.d.d("local mid check failed, redress with mid:" + a.toString());
        if (i.a(this.a).b("ten.mid.allowCheckAndRewriteLocal.bool", 0) == 1) {
            g.a(this.a).f(a);
        }
    }

    private void b() {
        a k = g.a(this.a).k();
        if (k == null) {
            this.d.d("CheckEntity is null");
            return;
        }
        int b = k.b() + 1;
        long currentTimeMillis = System.currentTimeMillis() - k.a();
        if (currentTimeMillis < 0) {
            currentTimeMillis = -currentTimeMillis;
        }
        this.d.b("check entity: " + k.toString() + ",duration:" + currentTimeMillis);
        a();
        c();
        k.b(b);
        k.a(System.currentTimeMillis());
        g.a(this.a).a(k);
        MidEntity a = g.a(this.a).a();
        this.d.b("midNewEntity:" + a);
        if (!Util.isMidValid(a)) {
            this.d.b((Object) "request mid_new ");
            c.a(this.a).a(3, new f(this.a), new k(this));
        }
    }

    private void c() {
        this.d.b((Object) "checkServer");
        c.a(this.a).a(2, new f(this.a), new l(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r5 = this;
        r1 = com.tencent.mid.a.j.class;
        monitor-enter(r1);
        r0 = "MID";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0071 }
        r2.<init>();	 Catch:{ all -> 0x0071 }
        r3 = "ServiceRunnable begin, type:";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0071 }
        r3 = r5.c;	 Catch:{ all -> 0x0071 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0071 }
        r3 = ",ver:";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0071 }
        r3 = 1080956027; // 0x406e147b float:3.72 double:5.340632376E-315;
        r2 = r2.append(r3);	 Catch:{ all -> 0x0071 }
        r2 = r2.toString();	 Catch:{ all -> 0x0071 }
        android.util.Log.i(r0, r2);	 Catch:{ all -> 0x0071 }
        r0 = r5.c;	 Catch:{ Throwable -> 0x006a }
        switch(r0) {
            case 1: goto L_0x0058;
            case 2: goto L_0x009b;
            default: goto L_0x0032;
        };	 Catch:{ Throwable -> 0x006a }
    L_0x0032:
        r0 = r5.d;	 Catch:{ Throwable -> 0x006a }
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x006a }
        r2.<init>();	 Catch:{ Throwable -> 0x006a }
        r3 = "wrong type:";
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x006a }
        r3 = r5.c;	 Catch:{ Throwable -> 0x006a }
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x006a }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x006a }
        r0.d(r2);	 Catch:{ Throwable -> 0x006a }
    L_0x004d:
        r0 = "MID";
        r2 = "ServiceRunnable end";
        android.util.Log.i(r0, r2);	 Catch:{ all -> 0x0071 }
        monitor-exit(r1);	 Catch:{ all -> 0x0071 }
        return;
    L_0x0058:
        r0 = r5.a;	 Catch:{ Throwable -> 0x006a }
        r0 = com.tencent.mid.a.g.a(r0);	 Catch:{ Throwable -> 0x006a }
        r2 = com.tencent.mid.util.Util.isMidValid(r0);	 Catch:{ Throwable -> 0x006a }
        if (r2 == 0) goto L_0x0074;
    L_0x0064:
        r2 = r5.b;	 Catch:{ Throwable -> 0x006a }
        r2.onSuccess(r0);	 Catch:{ Throwable -> 0x006a }
        goto L_0x004d;
    L_0x006a:
        r0 = move-exception;
        r2 = r5.d;	 Catch:{ all -> 0x0071 }
        r2.f(r0);	 Catch:{ all -> 0x0071 }
        goto L_0x004d;
    L_0x0071:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0071 }
        throw r0;
    L_0x0074:
        r0 = r5.a;	 Catch:{ Throwable -> 0x006a }
        r0 = com.tencent.mid.util.Util.isNetworkAvailable(r0);	 Catch:{ Throwable -> 0x006a }
        if (r0 == 0) goto L_0x0090;
    L_0x007c:
        r0 = r5.a;	 Catch:{ Throwable -> 0x006a }
        r0 = com.tencent.mid.a.c.a(r0);	 Catch:{ Throwable -> 0x006a }
        r2 = 1;
        r3 = new com.tencent.mid.a.f;	 Catch:{ Throwable -> 0x006a }
        r4 = r5.a;	 Catch:{ Throwable -> 0x006a }
        r3.<init>(r4);	 Catch:{ Throwable -> 0x006a }
        r4 = r5.b;	 Catch:{ Throwable -> 0x006a }
        r0.a(r2, r3, r4);	 Catch:{ Throwable -> 0x006a }
        goto L_0x004d;
    L_0x0090:
        r0 = r5.b;	 Catch:{ Throwable -> 0x006a }
        r2 = -10010; // 0xffffffffffffd8e6 float:NaN double:NaN;
        r3 = "network not available.";
        r0.onFail(r2, r3);	 Catch:{ Throwable -> 0x006a }
        goto L_0x004d;
    L_0x009b:
        r5.b();	 Catch:{ Throwable -> 0x006a }
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mid.a.j.run():void");
    }
}
