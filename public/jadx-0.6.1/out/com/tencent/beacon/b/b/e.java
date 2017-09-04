package com.tencent.beacon.b.b;

import android.content.Context;
import android.util.Base64;
import android.util.SparseArray;
import com.tencent.beacon.b.c;
import com.tencent.beacon.e.d;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/* compiled from: ProGuard */
public class e {
    private static e a = null;
    private String b;
    private int c;
    private int d;
    private int e;
    private SparseArray<a> f;
    private Map<String, String> g;
    private byte h;
    private byte i;
    private String j;
    private String k;
    private String l;
    private String m;
    private final Object n;

    /* compiled from: ProGuard */
    class AnonymousClass1 implements Runnable {
        private /* synthetic */ String a;
        private /* synthetic */ String b;
        private /* synthetic */ Context c;

        AnonymousClass1(String str, String str2, Context context) {
            this.a = str;
            this.b = str2;
            this.c = context;
        }

        public final void run() {
            long time;
            Object[] objArr = new Object[2];
            objArr[0] = this.a;
            try {
                time = com.tencent.beacon.net.a.d(this.b).getTime() / 1000;
            } catch (Exception e) {
                time = 0;
            }
            if (time == 0) {
                time = (new Date().getTime() / 1000) + TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
            }
            objArr[1] = Long.valueOf(time);
            com.tencent.beacon.net.a.a(this.c, "sid", objArr);
        }
    }

    /* compiled from: ProGuard */
    public static class a {
        private final int a;
        private boolean b = false;
        private String c = "http://oth.eve.mdt.qq.com:8080/analytics/upload";
        private String d = "http://jrlt.beacon.qq.com/analytics/upload";
        private Map<String, String> e = null;
        private Set<String> f = null;
        private com.tencent.beacon.c.e.e g = null;
        private Set<String> h = null;

        public a(int i) {
            this.a = i;
        }

        public final boolean a() {
            return this.b;
        }

        public final void a(boolean z) {
            this.b = z;
        }

        public final String b() {
            if (com.tencent.beacon.e.a.b) {
                return this.d;
            }
            return this.c;
        }

        public final void a(String str) {
            this.c = str;
        }

        public final Map<String, String> c() {
            return this.e;
        }

        public final void a(Map<String, String> map) {
            this.e = map;
        }

        public final Set<String> d() {
            return this.f;
        }

        public final void a(Set<String> set) {
            this.f = set;
        }

        public final com.tencent.beacon.c.e.e e() {
            return this.g;
        }

        public final void a(com.tencent.beacon.c.e.e eVar) {
            this.g = eVar;
        }

        public final int f() {
            return this.a;
        }

        public final Set<String> g() {
            return this.h;
        }

        public final void b(Set<String> set) {
            this.h = set;
        }
    }

    private e() {
        this.b = "http://oth.str.mdt.qq.com:8080/analytics/upload";
        this.c = 360;
        this.d = 1;
        this.e = 100;
        this.f = null;
        this.g = null;
        this.h = (byte) 3;
        this.i = (byte) 2;
        this.j = "*^@K#K@!";
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = new Object();
        this.f = new SparseArray(3);
        this.f.put(1, new a(1));
        this.f.put(2, new a(2));
        this.f.put(3, new a(3));
    }

    public static e a() {
        if (a == null) {
            synchronized (e.class) {
                if (a == null) {
                    a = new e();
                }
            }
        }
        return a;
    }

    public final String b() {
        return this.b;
    }

    public final void a(String str) {
        this.b = str;
    }

    public final int c() {
        return this.c;
    }

    public final void a(int i) {
        this.c = i;
    }

    public final Map<String, String> d() {
        return this.g;
    }

    public final void a(Map<String, String> map) {
        this.g = map;
    }

    public final synchronized SparseArray<a> e() {
        SparseArray<a> a;
        if (this.f != null) {
            d dVar = new d();
            a = d.a(this.f);
        } else {
            a = null;
        }
        return a;
    }

    public final synchronized a b(int i) {
        a aVar;
        if (this.f != null) {
            aVar = (a) this.f.get(i);
        } else {
            aVar = null;
        }
        return aVar;
    }

    public final synchronized boolean f() {
        boolean z;
        z = false;
        if (this.f != null) {
            z = ((a) this.f.get(2)).b;
        }
        return z;
    }

    public final boolean g() {
        if (this.g != null) {
            String str = (String) this.g.get("updateQimei");
            if (str != null && "n".equalsIgnoreCase(str)) {
                return false;
            }
            if (str != null && "y".equalsIgnoreCase(str)) {
                return true;
            }
        }
        return true;
    }

    public final int h() {
        if (this.g != null) {
            String str = (String) this.g.get("maxQIMEIQueryOneDay");
            if (!(str == null || str.trim().equals(""))) {
                try {
                    return Integer.valueOf(str).intValue();
                } catch (Exception e) {
                    return this.d;
                }
            }
        }
        return this.d;
    }

    public final int i() {
        if (this.g != null) {
            String str = (String) this.g.get("maxStrategyQueryOneDay");
            if (!(str == null || str.trim().equals(""))) {
                try {
                    return Integer.valueOf(str).intValue();
                } catch (Exception e) {
                    return this.e;
                }
            }
        }
        return this.e;
    }

    public final synchronized boolean j() {
        boolean z;
        if (this.g != null) {
            String str = (String) this.g.get("zeroPeak");
            if (str != null && "y".equalsIgnoreCase(str) && Calendar.getInstance().get(11) == 0) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public final synchronized boolean k() {
        boolean z;
        if (this.g != null) {
            String str = (String) this.g.get("qimeiZeroPeak");
            if (str != null && "y".equalsIgnoreCase(str) && Calendar.getInstance().get(11) == 0) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public final synchronized byte l() {
        return this.h;
    }

    public final synchronized byte m() {
        return this.i;
    }

    public final synchronized String n() {
        return this.j;
    }

    public final synchronized void b(String str) {
        this.j = str;
    }

    public final synchronized String o() {
        return this.k;
    }

    public final String p() {
        String str;
        synchronized (this.n) {
            str = this.l;
        }
        return str;
    }

    public final String q() {
        String str;
        synchronized (this.n) {
            str = this.m;
        }
        return str;
    }

    public final void a(Context context, String str, String str2) {
        synchronized (this.n) {
            this.m = str;
            c.a().a(new AnonymousClass1(str, str2, context));
        }
    }

    final void a(Context context) {
        synchronized (this.n) {
            String str;
            Object[] a = com.tencent.beacon.net.a.a(context, "sid");
            if (a != null && a.length == 3) {
                long longValue;
                long time = new Date().getTime() / 1000;
                try {
                    longValue = ((Long) a[2]).longValue();
                } catch (Exception e) {
                    longValue = 0;
                }
                if (longValue > time) {
                    str = (String) a[1];
                    synchronized (this.n) {
                        this.m = str;
                    }
                }
            }
            str = com.tencent.beacon.net.a.g(context);
            synchronized (this.n) {
                this.l = str;
                this.k = Base64.encodeToString(com.tencent.beacon.net.a.a(str), 2);
            }
        }
    }
}
