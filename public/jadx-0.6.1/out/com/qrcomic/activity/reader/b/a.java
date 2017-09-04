package com.qrcomic.activity.reader.b;

import android.os.Bundle;
import com.qrcomic.activity.reader.a.b;
import com.qrcomic.entity.f;
import com.qrcomic.util.g;
import java.util.List;

/* compiled from: QRRequestComicAndSectionStep */
public class a extends e {
    b a = new b(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(com.qrcomic.entity.a aVar, List<f> list, boolean z) {
            if (!this.a.b) {
                this.a.f.a((List) list, z);
            } else if (this.a.f.a(aVar)) {
                this.a.f.b();
                e.b(2, this.a.f);
            }
        }
    };
    private boolean b;

    public void a(Bundle bundle) {
        this.b = bundle.getBoolean("requestComicData", false);
        if (this.b) {
            if (g.a()) {
                g.a("comic_reader_startup", g.d, "QRRequestComicAndSectionStep doStep : requestComicData");
            }
            this.h.a(this.f.n, this.f.j, this.f.E, 1, 3, this.a, 0, false, this.f.J);
            return;
        }
        if (g.a()) {
            g.a("comic_reader_startup", g.d, "QRRequestComicAndSectionStep doStep : requestSectionData");
        }
        this.h.a(this.f, this.a, this.f.J);
    }
}
