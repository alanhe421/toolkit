package com.tencent.feedback.eup;

import android.content.Context;
import com.tencent.feedback.common.i;
import com.tencent.feedback.proguard.a;
import com.tencent.feedback.proguard.l;
import com.tencent.feedback.proguard.t;
import com.tencent.feedback.upload.AbstractUploadDatas;
import com.tencent.feedback.upload.UploadHandleListener;
import com.tencent.feedback.upload.e;

/* compiled from: RQDSRC */
public final class f extends i {
    private static f b;
    private CrashStrategyBean c;
    private CrashStrategyBean d;
    private c e;
    private CrashHandleListener f;
    private final boolean g;

    public static synchronized f a(Context context, String str, boolean z, e eVar, UploadHandleListener uploadHandleListener, CrashHandleListener crashHandleListener, CrashStrategyBean crashStrategyBean) {
        f fVar;
        synchronized (f.class) {
            if (b == null) {
                com.tencent.feedback.common.e.a("rqdp{  eup create instance}", new Object[0]);
                fVar = new f(context, str, false, eVar, uploadHandleListener, crashHandleListener, crashStrategyBean);
                b = fVar;
                fVar.a(true);
            }
            fVar = b;
        }
        return fVar;
    }

    public static synchronized f l() {
        f fVar;
        synchronized (f.class) {
            fVar = b;
        }
        return fVar;
    }

    public static synchronized e a(Context context, boolean z) {
        e a;
        synchronized (f.class) {
            a = com.tencent.feedback.upload.f.a(context, z);
        }
        return a;
    }

    public static synchronized AbstractUploadDatas m() {
        AbstractUploadDatas abstractUploadDatas = null;
        synchronized (f.class) {
            if (o()) {
                if (b == null) {
                    com.tencent.feedback.common.e.c("rqdp{  instance == null}", new Object[0]);
                } else if (b.a()) {
                    abstractUploadDatas = g.a(b.a);
                }
            }
        }
        return abstractUploadDatas;
    }

    public static boolean n() {
        if (!o()) {
            return false;
        }
        com.tencent.feedback.common.e.a("rqdp{  doUploadExceptionDatas}", new Object[0]);
        f l = l();
        if (l != null) {
            return l.h();
        }
        com.tencent.feedback.common.e.c("rqdp{  instance == null}", new Object[0]);
        return false;
    }

    public static boolean a(Thread thread, Throwable th, String str, byte[] bArr) {
        com.tencent.feedback.common.e.a("rqdp{  handleCatchException}", new Object[0]);
        if (!o()) {
            return false;
        }
        f l = l();
        if (l == null) {
            com.tencent.feedback.common.e.c("rqdp{  instance == null}", new Object[0]);
            return false;
        }
        if (l.a()) {
            try {
                c u = l.u();
                if (u == null) {
                    com.tencent.feedback.common.e.c("rqdp{  imposiable chandler null!}", new Object[0]);
                    return false;
                }
                return u.a(thread == null ? null : thread.getName(), th, str, bArr, false);
            } catch (Throwable th2) {
                if (!com.tencent.feedback.common.e.a(th2)) {
                    th2.printStackTrace();
                }
                com.tencent.feedback.common.e.d("rqdp{  handleCatchException error} %s", th2.toString());
            }
        }
        return false;
    }

    public static boolean o() {
        f l = l();
        if (l == null) {
            com.tencent.feedback.common.e.d("rqdp{  not init eup}", new Object[0]);
            return false;
        }
        boolean a = l.a();
        if (a && l.t()) {
            return l.b();
        }
        return a;
    }

    private f(Context context, String str, boolean z, e eVar, UploadHandleListener uploadHandleListener, CrashHandleListener crashHandleListener, CrashStrategyBean crashStrategyBean) {
        Context context2;
        if (context == null) {
            context2 = context;
        } else {
            context2 = context.getApplicationContext();
            if (context2 == null) {
                context2 = context;
            }
        }
        super(context, str, 3, 530, 510, eVar, new d(context2), uploadHandleListener);
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        if (crashStrategyBean != null) {
            com.tencent.feedback.common.e.b("rqdp{  cus eupstrategy} %s", crashStrategyBean);
            this.c = crashStrategyBean;
        } else {
            com.tencent.feedback.common.e.b("rqdp{  default eupstrategy}", new Object[0]);
            this.c = new CrashStrategyBean();
        }
        this.e = c.a(this.a);
        this.f = crashHandleListener;
        this.g = z;
    }

    private synchronized boolean t() {
        return this.g;
    }

    public final synchronized CrashStrategyBean p() {
        return this.c;
    }

    public final synchronized CrashStrategyBean q() {
        return this.d;
    }

    public final synchronized void a(CrashStrategyBean crashStrategyBean) {
        this.d = crashStrategyBean;
    }

    private synchronized c u() {
        return this.e;
    }

    public final synchronized CrashHandleListener r() {
        return this.f;
    }

    public final void f() {
        int i = -1;
        super.f();
        Context context = this.a;
        com.tencent.feedback.common.e.b("rqdp{  EUPDAO.deleteEup() start}", new Object[0]);
        if (context == null) {
            com.tencent.feedback.common.e.c("rqdp{  deleteEup() context is null arg}", new Object[0]);
        } else {
            i = l.a(context, new int[]{1, 2}, -1, Long.MAX_VALUE, -1, -1);
        }
        com.tencent.feedback.common.e.b("rqdp{  eup clear} %d ", Integer.valueOf(i));
        com.tencent.feedback.common.e.b("rqdp{  eup strategy clear} %d ", Integer.valueOf(a.b(this.a, 510)));
    }

    public final boolean i() {
        return q() != null;
    }

    public final boolean h() {
        if (super.h()) {
            AbstractUploadDatas a = g.a(this.a);
            e c = c();
            if (a == null || c == null) {
                com.tencent.feedback.common.e.c("rqdp{  upDatas or uphandler null!}", new Object[0]);
                return false;
            }
            try {
                c.a(a);
                return true;
            } catch (Throwable th) {
                if (!com.tencent.feedback.common.e.a(th)) {
                    th.printStackTrace();
                }
                com.tencent.feedback.common.e.d("rqdp{  upload eupdata error} %s", th.toString());
            }
        }
        return false;
    }

    public final int g() {
        CrashStrategyBean s = s();
        if (s == null || super.g() < 0) {
            return -1;
        }
        if (s.isMerged()) {
            com.tencent.feedback.common.e.b("rqdp{  in merge}", new Object[0]);
            if (b.a(this.a)) {
                return 1;
            }
            return 0;
        }
        com.tencent.feedback.common.e.b("rqdp{  in no merge}", new Object[0]);
        return b.b(this.a);
    }

    public final CrashStrategyBean s() {
        try {
            CrashStrategyBean q;
            if (t.a(this.a).b().f()) {
                q = q();
            } else {
                q = null;
            }
            if (q == null) {
                return p();
            }
            return q;
        } catch (Throwable th) {
            if (!com.tencent.feedback.common.e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public final synchronized void b(boolean z) {
        super.b(z);
        if (a()) {
            this.e.a();
        } else {
            this.e.b();
        }
    }

    public final void e() {
        int i = -1;
        super.e();
        Context context = this.a;
        com.tencent.feedback.common.e.b("rqdp{  EUPDAO.deleteEup() start}", new Object[0]);
        if (context == null) {
            com.tencent.feedback.common.e.c("rqdp{  deleteEup() context is null arg}", new Object[0]);
        } else {
            i = l.a(context, new int[]{1, 2}, -1, Long.MAX_VALUE, 3, -1);
        }
        com.tencent.feedback.common.e.b("remove fail updata num :%d", Integer.valueOf(i));
        if (k() == 1) {
            BuglyBroadcastRecevier.brocastProcessLaunch(this.a);
        }
    }
}
