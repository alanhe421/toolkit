package com.qq.reader.liveshow.utils.a;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.b;
import com.qq.reader.liveshow.c.g;
import com.qq.reader.liveshow.c.k;
import com.qq.reader.liveshow.utils.SxbLog;
import com.tencent.TIMManager;
import java.util.Arrays;

/* compiled from: LiveShowQuitManager */
public class a {
    private static final String a = a.class.getSimpleName();
    private static a b = new a();
    private b c;
    private k d;
    private g e;
    private Handler f = new Handler();
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private int[] k = new int[2];
    private int l = 0;
    private int m = 1;
    private b n = new b(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(boolean z, Object obj, String str) {
            SxbLog.b(a.a, "mStopPushOnNext success = " + z + "  Object = " + obj + " msg=" + str);
            this.a.a("mStopPushOnNext success = " + z + "  Object = " + obj + " msg=" + str);
            this.a.k[this.a.m] = 0;
            this.a.l();
        }
    };
    private b o = new b(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(boolean z, Object obj, String str) {
            SxbLog.b(a.a, "mStopRecordOnNext success = " + z + "  Object = " + obj + " msg=" + str);
            this.a.a("mStopRecordOnNext success = " + z + "  Object = " + obj + " msg=" + str);
            this.a.k[this.a.l] = 0;
            this.a.l();
        }
    };
    private b p = new b(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(boolean z, Object obj, String str) {
            if (!this.a.h && this.a.h()) {
                SxbLog.b(a.a, "mQuitOnNext success = " + z + "  Object = " + obj + " msg=" + str);
                this.a.a("mQuitOnNext success = " + z + "  Object = " + obj + " msg=" + str);
                if (this.a.d != null) {
                    this.a.m();
                    this.a.d = null;
                }
            }
        }
    };
    private b q = new b(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(boolean z, Object obj, String str) {
            if (!this.a.h && this.a.h()) {
                SxbLog.b(a.a, "mPrepareQuitOnNext success = " + z + "  Object = " + obj + " msg=" + str);
                this.a.a("mPrepareQuitOnNext success = " + z + "  Object = " + obj + " msg=" + str);
                if (obj instanceof g) {
                    ((g) obj).q();
                }
                if (this.a.c != null) {
                    this.a.c.b();
                    this.a.c = null;
                }
            }
        }
    };
    private b r = new b(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(boolean z, Object obj, String str) {
            if (!this.a.h && this.a.h()) {
                SxbLog.b(a.a, "mOnNextIMLogout success = " + z + "  Object = " + obj + " msg=" + str);
                this.a.a("mOnNextIMLogout success = " + z + "  Object = " + obj + " msg=" + str);
                this.a.n();
            }
        }
    };

    private a() {
    }

    public static a a() {
        return b;
    }

    public void a(b bVar, k kVar, g gVar) {
        SxbLog.b(a, " bind this ");
        this.e = gVar;
        this.c = bVar;
        this.d = kVar;
        e(false);
        this.j = true;
        a("\r\n ----------------------------------");
        a("bind manager");
    }

    public void a(boolean z, boolean z2) {
        if (!this.h && !h()) {
            a().c(false);
            this.k[this.l] = 0;
            this.k[this.m] = 0;
            e(true);
            this.j = false;
            if (this.e != null) {
                if (z) {
                    this.e.v();
                    this.k[this.m] = -1;
                }
                if (z2) {
                    this.k[this.l] = -1;
                    this.e.u();
                }
            }
            l();
        }
    }

    private void l() {
        SxbLog.b(a, "close on check checkStopSuccessAndQuit " + Arrays.toString(this.k));
        if (this.k[this.l] == 0 && this.k[this.m] == 0) {
            d(false);
        }
    }

    public void a(boolean z) {
        if (!this.h && !h()) {
            a().c(false);
            e(true);
            this.j = false;
            d(z);
        }
    }

    private void d(boolean z) {
        a("startQuit");
        a(60000);
        if (this.e != null) {
            this.e.a(z);
            this.e = null;
        }
    }

    private void m() {
        SxbLog.b(a, "login user is " + TIMManager.getInstance().getLoginUser());
        a("try logout " + TIMManager.getInstance().getLoginUser());
        if (TextUtils.isEmpty(TIMManager.getInstance().getLoginUser())) {
            n();
        } else {
            this.d.b();
        }
    }

    private void n() {
        a("successQuit  ");
        this.f.removeCallbacksAndMessages(null);
        a(5000);
    }

    private void a(int i) {
        if (h()) {
            this.f.postDelayed(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.f.removeCallbacksAndMessages(null);
                    this.a.a("postDelayed  logout  ");
                    this.a.c = null;
                    this.a.d = null;
                    this.a.e = null;
                    this.a.e(false);
                    if (!this.a.i()) {
                        Intent intent = new Intent();
                        intent.setAction("com.reader.live.im_logout");
                        Context f = n.a().f();
                        if (f != null) {
                            SxbLog.e("START", "LOCAL_IM_EXIT_SUCCESS_ACTION");
                            f.sendBroadcast(intent);
                        }
                    }
                }
            }, (long) i);
        }
    }

    public b b() {
        return this.p;
    }

    public b c() {
        return this.r;
    }

    public b d() {
        return this.n;
    }

    public b e() {
        return this.o;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public b f() {
        return this.q;
    }

    public boolean g() {
        return h() || !this.j;
    }

    public boolean h() {
        return this.i;
    }

    public boolean i() {
        return this.h;
    }

    public void c(boolean z) {
        SxbLog.e(a, "setIsInRoom = " + z);
        this.g = z;
    }

    public boolean j() {
        return this.g;
    }

    private void a(String str) {
    }

    private void e(boolean z) {
        SxbLog.e(a, " someone set quiting " + z);
        this.i = z;
    }
}
