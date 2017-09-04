package com.tencent.beacon.net;

import android.content.Context;
import com.tencent.beacon.b.b.b;
import com.tencent.beacon.b.b.c;
import com.tencent.beacon.b.b.e;
import com.tencent.beacon.b.f;
import com.tencent.beacon.e.a;
import com.tencent.beacon.event.UserAction;
import com.tencent.beacon.event.o;
import com.tencent.beacon.upload.g;
import com.tencent.beacon.upload.h;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ProGuard */
public class SpeedMonitorModule implements b {
    private static SpeedMonitorModule b = null;
    protected final Context a;
    private h c;
    private g d = new d();

    public static synchronized SpeedMonitorModule getInstance(Context context, Object obj) {
        SpeedMonitorModule speedMonitorModule;
        synchronized (SpeedMonitorModule.class) {
            if (b == null) {
                a.e(" SpeedMonitorModule create instance", new Object[0]);
                if (obj instanceof h) {
                    b = new SpeedMonitorModule(context, (h) obj);
                }
            }
            speedMonitorModule = b;
        }
        return speedMonitorModule;
    }

    public static synchronized SpeedMonitorModule getInstance() {
        SpeedMonitorModule speedMonitorModule;
        synchronized (SpeedMonitorModule.class) {
            speedMonitorModule = b;
        }
        return speedMonitorModule;
    }

    private SpeedMonitorModule(Context context, h hVar) {
        this.a = context;
        this.c = hVar;
        if (this.c != null) {
            this.c.a(105, this.d);
        }
        c a = c.a(this.a);
        a.a(this);
        a.a(2, this.c);
    }

    public boolean testSpeedIp(List<String> list) {
        a.a(" testSpeedIp start", new Object[0]);
        o.d();
        if (!o.g()) {
            return false;
        }
        if (list == null || list.size() <= 0) {
            a.c(" ipList == null || ipList.size() <= 0", new Object[0]);
            return true;
        }
        final String[] strArr = (String[]) list.toArray(new String[0]);
        com.tencent.beacon.b.c.a().a(new Runnable(this) {
            private /* synthetic */ SpeedMonitorModule b;

            public final void run() {
                if (strArr != null) {
                    a.b(" start to ip test:", new Object[0]);
                    String[] strArr = strArr;
                    int length = strArr.length;
                    int i = 0;
                    while (i < length) {
                        String str = strArr[i];
                        a.b(" ip:" + str, new Object[0]);
                        long j = -1;
                        try {
                            String[] split = str.split(":");
                            Map hashMap;
                            boolean z;
                            if (split == null || split.length != 2) {
                                a.c(" ip wrong format ,not ip:port " + str, new Object[0]);
                                a.b(" elapse:" + j, new Object[0]);
                                hashMap = new HashMap(1);
                                hashMap.put("A29", str);
                                f.a(this.b.a);
                                hashMap.put("A33", f.l(this.b.a));
                                str = "rqd_ipSpeed";
                                if (j <= 0) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                o.a(str, z, j, hashMap);
                                i++;
                            } else {
                                j = a.a(split[0], Integer.parseInt(split[1]));
                                a.b(" elapse:" + j, new Object[0]);
                                hashMap = new HashMap(1);
                                hashMap.put("A29", str);
                                f.a(this.b.a);
                                hashMap.put("A33", f.l(this.b.a));
                                str = "rqd_ipSpeed";
                                if (j <= 0) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                o.a(str, z, j, hashMap);
                                i++;
                            }
                        } catch (Throwable th) {
                            a.a(th);
                        }
                    }
                }
            }
        });
        return true;
    }

    public boolean testSpeedDomain(List<String> list) {
        o.d();
        if (!o.g()) {
            return false;
        }
        if (list == null || list.size() <= 0) {
            a.c(" dnsList == null || dnsList.size() <= 0", new Object[0]);
            return true;
        }
        final String[] strArr = (String[]) list.toArray(new String[0]);
        Runnable anonymousClass2 = new Runnable(this) {
            private /* synthetic */ SpeedMonitorModule b;

            public final void run() {
                if (strArr != null) {
                    a.b(" start domain test:", new Object[0]);
                    String[] strArr = strArr;
                    int length = strArr.length;
                    int i = 0;
                    while (i < length) {
                        String str = strArr[i];
                        a.b(" dns:" + str, new Object[0]);
                        a.b a = a.a(str, false);
                        if (a != null) {
                            boolean z;
                            long j = (((a.a + a.b) + a.c) + a.d) + a.e;
                            a.b(" elapse:" + j, new Object[0]);
                            Map hashMap = new HashMap(1);
                            f.a(this.b.a);
                            hashMap.put("A33", f.l(this.b.a));
                            hashMap.put("A34", str);
                            hashMap.put("A35", String.valueOf(a.a));
                            hashMap.put("A36", String.valueOf(a.c));
                            hashMap.put("A37", String.valueOf(a.d));
                            hashMap.put("A38", String.valueOf(a.e));
                            hashMap.put("A40", String.valueOf(a.b));
                            hashMap.put("A39", String.valueOf(a.f));
                            str = "rqd_domainSpeed";
                            if (j > 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            UserAction.onUserAction(str, z, j, 0, hashMap, true);
                            i++;
                        } else {
                            return;
                        }
                    }
                }
            }
        };
        a.a(" post the test task", new Object[0]);
        com.tencent.beacon.b.c.a().a(anonymousClass2);
        return true;
    }

    public static boolean d() {
        e a = e.a();
        if (a != null) {
            return a.f();
        }
        return false;
    }

    public final boolean a(b[] bVarArr) {
        if (bVarArr != null && bVarArr.length > 0) {
            com.tencent.beacon.b.c.a().a(new c(this.a, Arrays.asList(bVarArr)));
        }
        return true;
    }

    public final void a() {
    }

    public final void b() {
        e e = c.a(this.a).e();
        if (e != null) {
            e.a b = e.b(2);
            if (b.a() && b != null && b.e() != null) {
                try {
                    g gVar = this.d;
                    List a = d.a(b.e());
                    if (a != null) {
                        a((b[]) a.toArray(new b[0]));
                    }
                } catch (Throwable e2) {
                    a.a(e2);
                }
            }
        }
    }

    public final void c() {
    }
}
