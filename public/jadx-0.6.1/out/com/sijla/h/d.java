package com.sijla.h;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.util.SparseArray;
import com.sijla.common.a;
import com.sijla.j.b;
import com.sijla.j.c;
import com.sijla.j.f;
import com.sijla.j.j;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class d implements a {
    private boolean a;
    private Context b;
    private String c = "";
    private String d = "";
    private long e = 0;
    private String f = b.e();
    private List<String> g = new ArrayList();
    private boolean h;
    private SparseArray<Long[]> i = new SparseArray();
    private HashMap<String, Integer> j = new HashMap();
    private HashMap<String, Long> k = null;
    private List<String> l = null;
    private boolean m;
    private com.sijla.g.a n;
    private c o = com.sijla.common.b.e();

    public d(Context context) {
        boolean z = true;
        this.b = context;
        this.h = com.sijla.d.c.a.optInt("deskmode", 1) >= 1;
        this.n = new com.sijla.g.a(context);
        this.g = com.sijla.j.a.a.a(context, 2);
        if (VERSION.SDK_INT < 20) {
            z = false;
        }
        this.a = z;
        if (this.a) {
            this.k = new HashMap();
            this.l = new ArrayList();
        }
        a(context);
    }

    private void a(Context context) {
        try {
            String c = b.c();
            if (!c.equals((String) j.b(context, "mdaunewday", ""))) {
                File file = new File(b.l());
                if (file != null) {
                    com.sijla.j.a.b.a(file);
                }
                j.a(context, "mdaunewday", c);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void run() {
        int optInt = com.sijla.d.c.a.optInt("sessionitl", 3500);
        f.a("sessionheartinterval = " + optInt);
        boolean f = com.sijla.j.a.a.f(this.b, "android.permission.GET_TASKS");
        while (!this.m) {
            try {
                Thread.sleep((long) optInt);
                if (!com.sijla.j.a.a.a(this.b)) {
                    break;
                } else if (this.a || !f) {
                    List<String> a = com.sijla.b.a.a(this.b);
                    if (!this.l.isEmpty()) {
                        for (String str : this.l) {
                            if (!a.contains(str) && this.k.containsKey(str)) {
                                a(b.e(), str, "", ((Long) this.k.get(str)).longValue());
                                this.k.remove(str);
                            }
                        }
                        for (String str2 : a) {
                            if (!this.l.contains(str2)) {
                                this.c = str2;
                                f.c(str2 + " opend:" + b.d());
                                this.n.a(str2);
                                this.k.put(str2, Long.valueOf(b.g()));
                            }
                        }
                    } else if (!a.isEmpty()) {
                        for (String str22 : a) {
                            this.c = str22;
                            this.k.put(str22, Long.valueOf(b.g()));
                        }
                    }
                    this.l = a;
                } else {
                    String[] h = com.sijla.j.a.a.h(this.b);
                    if ("".equals(this.c)) {
                        this.c = h[1];
                        this.d = h[0];
                        this.e = b.g();
                        this.f = b.e();
                    } else if (this.c.equals(h[1])) {
                        Object obj = (1 == com.sijla.d.c.a.optInt("page", 0) || com.sijla.d.c.c.contains(this.c)) ? 1 : null;
                        if (!(this.a || obj == null || this.d.equals(h[0]))) {
                            a(this.f, this.c, this.d, this.e);
                            this.c = h[1];
                            this.d = h[0];
                            this.e = b.g();
                        }
                    } else if (this.g.contains(h[1]) || 1 != com.sijla.d.c.a.optInt("dau", 1)) {
                        this.n.a(h[1]);
                        a(this.f, this.c, this.d, this.e);
                        this.f = b.e();
                        this.c = h[1];
                        this.d = h[0];
                        this.e = b.g();
                    } else {
                        this.n.a(h[1]);
                        a(this.f, this.c, this.d, this.e);
                        this.f = b.e();
                        this.c = h[1];
                        this.d = h[0];
                        this.e = b.g();
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        f.c("AppSessionFunner.Task Stop");
    }

    public void a(String str, String str2, String str3, long j) {
        long g = b.g() - j;
        if (g > 0 && 0 != j && !b.a(str2)) {
            if (!this.g.contains(str2) || this.h) {
                if (str2.contains(".")) {
                    long j2 = 0;
                    long j3 = 0;
                    try {
                        int i;
                        long j4;
                        if (!this.j.containsKey(str2)) {
                            b(this.b);
                        }
                        if (this.j.containsKey(str2)) {
                            int intValue = ((Integer) this.j.get(str2)).intValue();
                            long[] a = com.sijla.j.a.a.a(intValue);
                            j2 = a[0];
                            j3 = a[1];
                            i = intValue;
                        } else {
                            i = -1;
                        }
                        f.a("curt-total rx:" + j2 + " b tx:" + j3 + " b");
                        Long[] lArr = (Long[]) this.i.get(i);
                        this.i.remove(i);
                        this.i.put(i, new Long[]{Long.valueOf(j2), Long.valueOf(j3)});
                        if (lArr == null || j2 < 0 || j3 < 0) {
                            j2 = 0;
                            j4 = 0;
                        } else {
                            j2 -= lArr[0].longValue();
                            j3 -= lArr[1].longValue();
                            f.a("last—total rx:" + lArr[0] + " b tx:" + lArr[1] + " b");
                            f.a("this rx:" + j2 + " b tx:" + j3 + " b");
                            if (j2 < 0 || j3 < 0) {
                                j2 = 0;
                                j4 = 0;
                            } else {
                                j4 = j2;
                                j2 = j3;
                            }
                        }
                        List arrayList = new ArrayList();
                        arrayList.add(b.p(this.b));
                        String h = b.h(this.b);
                        arrayList.add(h);
                        arrayList.add(str2);
                        arrayList.add(com.sijla.j.a.a.d(this.b, str2));
                        arrayList.add(com.sijla.j.a.a.a(str2, this.b));
                        arrayList.add(j + "");
                        arrayList.add(com.sijla.j.a.a.e(this.b));
                        Object obj = (1 == com.sijla.d.c.a.optInt("page", 0) || com.sijla.d.c.c.contains(str2)) ? 1 : null;
                        arrayList.add(obj != null ? str3 : "");
                        arrayList.add(str);
                        arrayList.add(j4 + "");
                        arrayList.add(g + "");
                        arrayList.add(j2 + "");
                        arrayList.add(com.sijla.common.b.c());
                        arrayList.add(com.sijla.common.b.d() + "");
                        arrayList.add(this.a ? "1" : "0");
                        String str4 = (obj == null || this.a) ? "apion" : "apge";
                        f.a("lid:" + h + " sid:" + str + " name:" + com.sijla.j.a.a.b(this.b, str2) + " " + str2 + " btime:" + b.c(1000 * j) + " dur:" + g + " etime:" + b.d() + (obj != null ? " page:" + str3 : ""));
                        this.o.a(b.f(str4) + "", arrayList);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    private void b(Context context) {
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            this.i.clear();
            this.j.clear();
            for (PackageInfo packageInfo : installedPackages) {
                int i = packageInfo.applicationInfo.uid;
                this.j.put(packageInfo.packageName, Integer.valueOf(i));
                long[] a = com.sijla.j.a.a.a(i);
                long j = a[0];
                long j2 = a[1];
                if (j == -1 || j2 == -1) {
                    j = TrafficStats.getUidRxBytes(i);
                    j2 = TrafficStats.getUidTxBytes(i);
                }
                this.i.put(i, new Long[]{Long.valueOf(j), Long.valueOf(j2)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        try {
            this.m = false;
            this.c = "";
            this.e = b.g();
            if (this.l != null) {
                this.l.clear();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void b() {
        f.a("AppSessionFunner.onScreenOff");
        this.m = true;
        try {
            if (this.a) {
                if (this.k != null && this.k.containsKey(this.c)) {
                    a(b.e(), this.c, "", ((Long) this.k.get(this.c)).longValue());
                }
                this.l.clear();
                return;
            }
            a(this.f, this.c, this.d, this.e);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public void a(Intent intent) {
    }
}
