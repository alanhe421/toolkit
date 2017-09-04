package com.qrcomic.downloader;

import android.text.TextUtils;
import com.qrcomic.a.h;
import com.qrcomic.a.j;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.f;
import com.qrcomic.entity.i;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.manager.b;
import com.qrcomic.util.e;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: QRComicSectionState */
public abstract class s {
    public u a;
    protected int b = 1;
    protected String c = "selectState";

    public s(u uVar) {
        this.a = uVar;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public void a(boolean z, boolean z2) {
        if (z) {
            final f fVar = this.a.f;
            final int n = this.a.n();
            j.b().a(new Runnable(this) {
                final /* synthetic */ s c;

                public void run() {
                    h b = b.a().b();
                    String a = b.a();
                    if (!TextUtils.isEmpty(a)) {
                        String str = fVar.a;
                        String str2 = fVar.b;
                        int i = fVar.f;
                        QRComicManager qRComicManager = (QRComicManager) b.a(1);
                        i a2 = qRComicManager.a(str, str2, a);
                        if (a2 == null) {
                            a2 = new i();
                            a2.a = a;
                            a2.b = str;
                            a2.c = str2;
                            a2.g = 0;
                            a2.i = 0;
                            a2.l = i;
                        } else {
                            this.c.a.t = a2.i;
                            this.c.a.u = a2.g;
                            this.c.a.r.set(a2.g);
                        }
                        a2.d = n;
                        a2.k = fVar.d;
                        a2.f = 0;
                        a2.e = "";
                        a2.j = e.a();
                        if (!qRComicManager.a(a2)) {
                            qRComicManager.a(a2);
                        }
                        d.b().a(0, true, this.c.a);
                    }
                }
            });
        }
    }

    public void b(boolean z, final boolean z2) {
        if (z) {
            final f fVar = this.a.f;
            final int n = this.a.n();
            j.b().a(new Runnable(this) {
                final /* synthetic */ s d;

                public void run() {
                    h b = b.a().b();
                    Object a = b.a();
                    if (!TextUtils.isEmpty(a)) {
                        boolean a2 = ((QRComicManager) b.a(1)).a(a, fVar.a, fVar.b, fVar.f, n, 202, "user pause download");
                        if (z2) {
                            d.b().a(5, a2, this.d.a);
                        }
                    }
                }
            });
        }
    }

    public void a(List<ComicSectionPicInfo> list, String str, String str2) {
    }

    public void a() {
    }

    public void a(long j, int i) {
    }

    public void a(int i, String str) {
        ConcurrentHashMap g = d.b().g();
        if (g != null) {
            g.remove(this.a.i());
        }
        final f fVar = this.a.f;
        this.a.a(this.a.m, true);
        final int n = this.a.n();
        final int i2 = i;
        final String str2 = str;
        j.b().a(new Runnable(this) {
            final /* synthetic */ s e;

            public void run() {
                h b = b.a().b();
                Object a = b.a();
                if (!TextUtils.isEmpty(a)) {
                    ((QRComicManager) b.a(1)).a(a, fVar.a, fVar.b, fVar.f, n, i2, str2);
                    d.b().a(1, false, this.e.a);
                }
            }
        });
    }

    public void a(int i, int i2) {
    }
}
