package com.tencent.beacon.upload;

import android.content.Context;
import com.tencent.beacon.b.b.c;
import com.tencent.beacon.b.b.e;
import com.tencent.beacon.b.j;
import com.tencent.beacon.c.a.b;
import com.tencent.beacon.e.a;

/* compiled from: ProGuard */
public final class d extends a {
    private Context f = null;
    private b g = null;

    public d(Context context) {
        super(context, 0, 102);
        this.f = context;
    }

    public final void b(boolean z) {
    }

    public final b a() {
        a.b("QIMEIUploadDatas.getUploadRequestPackage() start", new Object[0]);
        if (this.g != null) {
            return this.g;
        }
        try {
            j a = j.a(this.f);
            if (a == null) {
                a.c("QIMEIInfo instance is null, return", new Object[0]);
                return null;
            }
            com.tencent.beacon.c.c.a aVar;
            if (a == null) {
                aVar = null;
            } else {
                com.tencent.beacon.c.c.a aVar2 = new com.tencent.beacon.c.c.a();
                String c = a.c();
                if (c == null) {
                    c = "";
                }
                aVar2.b = c;
                c = a.e();
                if (c == null) {
                    c = "";
                }
                aVar2.d = c;
                c = a.d();
                if (c == null) {
                    c = "";
                }
                aVar2.c = c;
                c = a.f();
                if (c == null) {
                    c = "";
                }
                aVar2.e = c;
                c = a.a();
                if (c == null) {
                    c = "";
                }
                aVar2.a = c;
                c = a.m();
                if (c == null) {
                    c = "";
                }
                aVar2.f = c;
                c = a.q();
                if (c == null) {
                    c = "";
                }
                aVar2.g = c;
                c = a.r();
                if (c == null) {
                    c = "";
                }
                aVar2.h = c;
                c = a.g();
                if (c == null) {
                    c = "";
                }
                aVar2.i = c;
                aVar2.j = a.h();
                aVar2.k = true;
                c = a.p();
                if (c == null) {
                    c = "";
                }
                aVar2.l = c;
                c = a.i();
                if (c == null) {
                    c = "";
                }
                aVar2.m = c;
                c = a.j();
                if (c == null) {
                    c = "";
                }
                aVar2.n = c;
                c = a.m();
                if (c == null) {
                    c = "";
                }
                aVar2.o = c;
                c = a.k();
                if (c == null) {
                    c = "";
                }
                aVar2.p = c;
                c = a.n();
                if (c == null) {
                    c = "";
                }
                aVar2.q = c;
                c = a.l();
                if (c == null) {
                    c = "";
                }
                aVar2.r = c;
                c = a.o();
                if (c == null) {
                    c = "";
                }
                aVar2.s = c;
                aVar = aVar2;
            }
            e e = c.a(this.c).e();
            int i = 3;
            int i2 = 2;
            if (e != null) {
                i = e.l();
                i2 = e.m();
            }
            byte[] a2 = aVar.a();
            this.g = com.tencent.beacon.net.a.a(this.a, com.tencent.beacon.b.d.m(), a2, i2, i);
            return this.g;
        } catch (Throwable th) {
            a.a(th);
        }
    }
}
