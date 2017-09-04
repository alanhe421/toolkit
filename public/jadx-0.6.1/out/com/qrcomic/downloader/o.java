package com.qrcomic.downloader;

import com.qrcomic.a.h;
import com.qrcomic.entity.i;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.manager.b;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: QRComicSectionPreloadState */
public class o extends s {
    public o(u uVar) {
        super(uVar);
        this.b = 2;
        this.c = "PreloadState";
    }

    public void a(boolean z, boolean z2) {
    }

    public void b(boolean z, boolean z2) {
        d.b().c.remove(this.a);
        this.a.a(this.a.l, z2);
        super.b(z, z2);
    }

    public void a(int i, String str) {
        d b = d.b();
        ConcurrentHashMap g = b.g();
        if (g != null) {
            g.remove(this.a.i());
        }
        b.a(1, false, this.a);
    }

    public void a() {
        String str = this.a.f.a;
        String str2 = this.a.f.b;
        this.a.o.set(1);
        this.a.f.q = null;
        h b = b.a().b();
        i a = ((QRComicManager) b.a(1)).a(str, str2, b.a());
        if (a != null) {
            this.a.u = a.g;
            this.a.t = a.i;
        }
        this.a.e.clear();
        this.a.a(this.a.k, true);
    }
}
