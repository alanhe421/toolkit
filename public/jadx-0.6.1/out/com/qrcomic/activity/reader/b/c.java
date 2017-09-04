package com.qrcomic.activity.reader.b;

import android.os.Bundle;
import com.qrcomic.activity.reader.a.d;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.f;
import com.qrcomic.util.g;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: QRRequestPicInfoStep */
public class c extends e {
    f a;
    f b;
    f c;
    String d;
    d e = new d(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void a(List<ComicSectionPicInfo> list, String str, String str2) {
            if (str.equals(str)) {
                if (this.a.a != null && str2.equals(this.a.a.b)) {
                    this.a.f.r = list;
                } else if (this.a.b != null && str2.equals(this.a.b.b)) {
                    this.a.f.s = list;
                } else if (this.a.c != null && str2.equals(this.a.c.b)) {
                    this.a.f.t = list;
                }
                this.a.i.incrementAndGet();
                a();
            }
        }

        public void a(String str, String str2) {
            if (g.a()) {
                g.a("comic_reader_startup", g.d, "onPicInfoRequestFail : request section pic info fail, comicId = " + str + ", sectionId = " + str2);
            }
            this.a.i.incrementAndGet();
            a();
        }

        private void a() {
            if (b()) {
                if (g.a()) {
                    g.a("comic_reader_startup", g.d, "QRRequestPicInfoStep data ready");
                }
                this.a.f.f();
            }
        }

        private boolean b() {
            int i;
            if (this.a.a != null) {
                i = 1;
            } else {
                i = 0;
            }
            if (this.a.b != null) {
                i++;
            }
            if (this.a.c != null) {
                i++;
            }
            if (this.a.i.get() == i) {
                return true;
            }
            return false;
        }
    };
    private AtomicInteger i = new AtomicInteger(0);

    public void a(Bundle bundle) {
        if (g.a()) {
            g.a("comic_reader_startup", g.d, "QRRequestPicInfoStep doStep");
        }
        this.a = this.f.o;
        this.b = this.f.p;
        this.c = this.f.q;
        this.d = this.f.n;
        if (this.a != null && (this.f.a(this.a) || this.f.k != 0)) {
            this.h.a(this.d, this.a.b, Boolean.valueOf(true), this.f.J, this.e);
        }
        if (this.b != null) {
            if (!this.f.a(this.b)) {
                this.b.t = 1;
            }
            this.h.a(this.d, this.b.b, Boolean.valueOf(false), this.f.J, this.e);
        }
        if (this.c != null) {
            if (!this.f.a(this.c)) {
                this.c.t = 1;
            }
            this.h.a(this.d, this.c.b, Boolean.valueOf(false), this.f.J, this.e);
        }
    }
}
