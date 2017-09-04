package com.qrcomic.downloader;

import com.qrcomic.downloader.c.a.a;
import com.qrcomic.downloader.c.b.c;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.manager.b;
import com.qrcomic.util.f;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import qalsdk.n;

/* compiled from: QRComicPicTask */
abstract class h extends a implements a {
    public static AtomicLong f = new AtomicLong(0);
    public ComicSectionPicInfo g;
    public int h = 1;
    public c i;

    h() {
    }

    public String i() {
        if (this.g == null) {
            return null;
        }
        StringBuilder a = com.qrcomic.downloader.a.c.a();
        a.append("type=").append(this.a == 200 ? "offline" : "read");
        a.append("&comid=").append(this.a == 200 ? "offline" : "read");
        a.append("&sectionid=").append(this.g.comicId);
        a.append("&picid=").append(this.g.picId);
        return a.toString();
    }

    public void a(String str) {
        c(2);
        this.i = new c();
        this.i.b = this;
        this.i.c = new com.qrcomic.f.c(com.qrcomic.f.a.b(), 153600);
        this.i.d = str;
        this.i.e = this.g;
        this.i.f = true;
        this.i.g = n.f;
        this.i.h.put("Connection", "Keep-Alive");
        try {
            a(this.i);
            this.i.a(b.a().b().f().e().a());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void a(c cVar) {
        try {
            cVar.h.put("netType", f.b(f.d(b.a().b().b())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void l() {
        if (this.i != null) {
            this.i.a();
        }
    }

    public void c(int i) {
        this.h = i;
    }
}
