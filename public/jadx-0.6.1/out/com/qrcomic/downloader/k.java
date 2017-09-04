package com.qrcomic.downloader;

import android.text.TextUtils;
import com.qrcomic.a.h;
import com.qrcomic.a.j;
import com.qrcomic.entity.f;
import com.qrcomic.entity.i;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.manager.b;
import com.qrcomic.util.e;
import com.qrcomic.util.g;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: QRComicSectionDownloadState */
public class k extends s {
    public k(u uVar) {
        super(uVar);
        this.b = 4;
        this.c = "DownloadState";
    }

    public void a(boolean z, boolean z2) {
    }

    public void b(boolean z, boolean z2) {
        d b = d.b();
        if (!(b == null || this.a.e.isEmpty())) {
            Iterator it = this.a.e.iterator();
            while (it.hasNext()) {
                b.a.a(((a) it.next()).i());
            }
            it = this.a.e.iterator();
            while (it.hasNext()) {
                b.a.b(((a) it.next()).i());
            }
        }
        this.a.a(this.a.l, z2);
        super.b(z, z2);
    }

    public void a(long j, int i) {
        this.a.a(true);
        final f fVar = this.a.f;
        final int n = this.a.n();
        final int i2 = this.a.q.get();
        this.a.r.set(j);
        final long j2 = j;
        final int i3 = i;
        j.b().a(new Runnable(this) {
            final /* synthetic */ k f;

            public void run() {
                h b = b.a().b();
                if (b != null) {
                    String a = b.a();
                    String str = fVar.a;
                    String str2 = fVar.b;
                    if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                        QRComicManager qRComicManager = (QRComicManager) b.a(1);
                        i a2 = qRComicManager.a(str, str2, a);
                        if (a2 == null) {
                            a2 = new i();
                        }
                        a2.d = n;
                        a2.g = j2;
                        a2.i = i3;
                        a2.h = i2;
                        a2.j = e.a();
                        a2.m = fVar.p;
                        if (fVar.f >= 0) {
                            a2.l = fVar.f;
                        }
                        if (TextUtils.isEmpty(a2.a)) {
                            a2.a = a;
                        }
                        if (TextUtils.isEmpty(a2.b)) {
                            a2.b = str;
                        }
                        if (TextUtils.isEmpty(a2.c)) {
                            a2.c = str2;
                        }
                        qRComicManager.b(a2);
                        d.b().a(3, true, this.f.a);
                    }
                }
            }
        });
    }

    public void a(int i, final int i2) {
        final d b = d.b();
        ConcurrentHashMap g = b.g();
        if (g != null) {
            g.remove(this.a.i());
        }
        final f fVar = this.a.f;
        this.a.r.set(fVar.d);
        this.a.a(this.a.n, true);
        j.b().a(new Runnable(this) {
            final /* synthetic */ k d;

            public void run() {
                h b = b.a().b();
                if (b != null) {
                    String a = b.a();
                    String str = fVar.a;
                    String str2 = fVar.b;
                    if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                        QRComicManager qRComicManager = (QRComicManager) b.a(1);
                        i a2 = qRComicManager.a(str, str2, a);
                        if (a2 == null) {
                            a2 = new i();
                        }
                        a2.d = 104;
                        a2.g = fVar.d;
                        a2.i = 100;
                        a2.h = i2;
                        a2.j = e.a();
                        a2.m = fVar.p;
                        if (fVar.f >= 0) {
                            a2.l = fVar.f;
                        }
                        if (TextUtils.isEmpty(a2.a)) {
                            a2.a = a;
                        }
                        if (TextUtils.isEmpty(a2.b)) {
                            a2.b = str;
                        }
                        if (TextUtils.isEmpty(a2.c)) {
                            a2.c = str2;
                        }
                        qRComicManager.b(a2);
                        b.a(4, true, this.d.a);
                    }
                }
            }
        });
        if (g.a()) {
            g.b("QRComicSectionDownloadState", g.d, "onSectionTaskFinish comicId=" + fVar.a + ",sectionIndex=" + fVar.f + ",taskNum=" + i + ",successNum=" + i2);
        }
    }
}
