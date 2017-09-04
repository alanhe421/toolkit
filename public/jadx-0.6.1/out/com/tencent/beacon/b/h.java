package com.tencent.beacon.b;

import android.content.Context;
import android.os.Process;
import com.tencent.beacon.b.a.f;
import com.tencent.beacon.net.a;
import com.tencent.beacon.upload.UploadHandleListener;
import com.tencent.beacon.upload.i;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* compiled from: ProGuard */
public final class h implements UploadHandleListener {
    private static h e = null;
    private static long f = 1073741824;
    private static long g = 209715200;
    private f a;
    private f b;
    private f c;
    private Context d = null;
    private Object h = new Object();
    private boolean i = true;

    private h(Context context) {
        this.d = context;
        i.a(this.d).a((UploadHandleListener) this);
        d();
        if (e() > 0) {
            f();
        }
    }

    public static synchronized h a(Context context) {
        h hVar;
        synchronized (h.class) {
            if (e == null) {
                e = new h(context);
            }
            hVar = e;
        }
        return hVar;
    }

    public static f b(Context context) {
        return a(context).b();
    }

    public static f c(Context context) {
        return a(context).a();
    }

    public static void a(Context context, f fVar) {
        fVar.g = 0;
        fVar.f = 0;
        fVar.d = 0;
        fVar.e = 0;
        a(context).c(fVar);
    }

    private f a() {
        try {
            long longValue;
            long longValue2;
            long j;
            int myUid = Process.myUid();
            Class cls = Class.forName("android.net.TrafficStats");
            if (cls != null) {
                longValue = ((Long) cls.getMethod("getUidRxBytes", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(myUid)})).longValue();
                longValue2 = ((Long) cls.getMethod("getUidTxBytes", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(myUid)})).longValue();
                j = longValue;
            } else {
                longValue2 = 0;
                j = 0;
            }
            List<f> b = a.b(this.d);
            if (b != null) {
                for (f fVar : b) {
                    this.c = fVar;
                }
            }
            if (this.c == null) {
                this.c = new f(2, System.currentTimeMillis(), 0, 0, 0, 0, 0);
                this.c.i = j;
                this.c.h = longValue2;
                c(this.c);
            } else if (!(j == this.c.i && longValue2 == this.c.h)) {
                boolean n = f.n(this.d);
                this.c.b = System.currentTimeMillis();
                long j2 = 0;
                long j3 = 0;
                longValue = 0;
                long j4 = 0;
                if (j - this.c.i > 0) {
                    j2 = j - this.c.i;
                }
                if (longValue2 - this.c.h > 0) {
                    j3 = longValue2 - this.c.h;
                }
                if (n) {
                    longValue = j2 + j3;
                } else {
                    j4 = j2 + j3;
                }
                f fVar2 = this.c;
                fVar2.g = j2 + fVar2.g;
                f fVar3 = this.c;
                fVar3.f = j3 + fVar3.f;
                f fVar4 = this.c;
                fVar4.d = longValue + fVar4.d;
                f fVar5 = this.c;
                fVar5.e = j4 + fVar5.e;
                this.c.i = j;
                this.c.h = longValue2;
                if (this.c.g >= f || this.c.f >= g || this.c.d >= f || this.c.e >= f) {
                    this.c.g = 0;
                    this.c.f = 0;
                    this.c.d = 0;
                    this.c.e = 0;
                    c(this.c);
                } else {
                    c(this.c);
                }
                return this.c;
            }
            return this.c;
        } catch (Throwable e) {
            com.tencent.beacon.e.a.a(e);
            return null;
        }
    }

    public static void d(Context context) {
        h a = a(context);
        synchronized (a) {
            long time = new Date().getTime();
            f b = a.b();
            a.a(new f(0, time, 0, 0, 0, 0, 0));
        }
        synchronized (a.h) {
            if (b != null) {
                if (b.a() >= 0) {
                    a.b(a.d, new f[]{b});
                }
            }
        }
    }

    public static void e(Context context) {
        h a = a(context);
        synchronized (a) {
            long time = new Date().getTime();
            f fVar = a.c;
            a.a(new f(2, time, 0, 0, 0, 0, 0));
        }
        synchronized (a.h) {
            if (fVar != null) {
                if (fVar.a() >= 0) {
                    a.b(a.d, new f[]{fVar});
                }
            }
        }
    }

    public static f f(Context context) {
        return a(context).c();
    }

    public final void onUploadEnd(int i, int i2, long j, long j2, boolean z, String str) {
        com.tencent.beacon.e.a.h(" req:%d  res:%d  send:%d  recv:%d  result: %b  msg:%s", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j), Long.valueOf(j2), Boolean.valueOf(z), str);
        if (i != 5) {
            e();
            a(j, j2, f.n(this.d));
            f();
            com.tencent.beacon.e.a.f(" [total:%s] \n[today:%s]", b(), c());
        }
    }

    private synchronized f b() {
        return this.a;
    }

    private synchronized void a(f fVar) {
        this.a = fVar;
    }

    private synchronized f c() {
        e();
        return this.b;
    }

    private synchronized void b(f fVar) {
        this.b = fVar;
    }

    private synchronized void d() {
        List<f> a = a.a(this.d);
        if (a != null) {
            for (f fVar : a) {
                if (fVar.a == 0) {
                    a(fVar);
                } else if (fVar.a == 1) {
                    b(fVar);
                }
            }
        }
    }

    private synchronized int e() {
        int i;
        long c = a.c();
        long time = new Date().getTime();
        int i2 = 0;
        if (this.b == null || this.b.b < c) {
            this.b = new f(1, time, 0, 0, 0, 0, 0);
            i2 = 1;
        }
        if (this.a == null) {
            this.a = new f(0, time, 0, 0, 0, 0, 0);
            i = i2 + 1;
        } else {
            i = i2;
        }
        return i;
    }

    private synchronized void a(long j, long j2, boolean z) {
        long time = new Date().getTime();
        long j3 = j + j2;
        long j4 = z ? j3 : 0;
        if (z) {
            j3 = 0;
        }
        if (this.b == null) {
            this.b = new f(1, time, 1, j4, j3, j, j2);
        } else {
            long a = this.b.a();
            this.b = new f(1, this.b.b, 1 + this.b.c, this.b.d + j4, this.b.e + j3, this.b.f + j, this.b.g + j2);
            this.b.a(a);
        }
        if (this.a == null) {
            this.a = new f(0, time, 1, j4, j3, j, j2);
        } else {
            long a2 = this.a.a();
            this.a = new f(0, this.a.b, this.a.c + 1, j4 + this.a.d, j3 + this.a.e, this.a.f + j, this.a.g + j2);
            this.a.a(a2);
        }
    }

    private void f() {
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            f b = b();
            if (b != null) {
                arrayList.add(b);
            }
            b = c();
            if (b != null) {
                arrayList.add(b);
            }
        }
        synchronized (this.h) {
            if (arrayList.size() > 0) {
                a.a(this.d, (f[]) arrayList.toArray(new f[arrayList.size()]));
                if (this.i) {
                    d();
                    this.i = false;
                }
            }
        }
    }

    private void c(f fVar) {
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            if (fVar != null) {
                arrayList.add(fVar);
            }
        }
        synchronized (this.h) {
            if (arrayList.size() > 0) {
                a.c(this.d, (f[]) arrayList.toArray(new f[arrayList.size()]));
            }
        }
    }
}
