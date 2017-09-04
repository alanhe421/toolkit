package com.qrcomic.downloader;

/* compiled from: QRComicSectionSelectState */
public class r extends s {
    public r(u uVar) {
        super(uVar);
        this.b = 1;
        this.c = "SelectState";
    }

    public void a(boolean z, boolean z2) {
        try {
            d b = d.b();
            this.a.r.set(0);
            this.a.p.set(0);
            this.a.q.set(0);
            b.c.add(this.a);
            this.a.a(this.a.i, z2);
            super.a(z, z2);
        } catch (Exception e) {
            e.printStackTrace();
            this.a.a(201, "startDownload Exception msg=" + e.getMessage());
        }
    }

    public void b(boolean z, boolean z2) {
        d.b().c.remove(this.a);
        this.a.a(this.a.l, z2);
        super.b(z, z2);
    }

    public void a(int i, String str) {
        super.a(i, str);
    }
}
