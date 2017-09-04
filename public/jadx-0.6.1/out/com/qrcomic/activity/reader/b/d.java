package com.qrcomic.activity.reader.b;

import android.os.Bundle;
import com.qrcomic.activity.reader.a.e;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.util.g;

/* compiled from: QRRequestPicsStep */
public class d extends e {
    e a = new e(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.f.d = System.currentTimeMillis();
            this.a.f.f = (int) (this.a.f.d - this.a.f.c);
            this.a.f.g = (int) (this.a.f.d - this.a.f.b);
            if (g.a()) {
                g.a("comic_reader_startup", g.d, "All data is ready, begin to read");
            }
            this.a.f.a();
        }

        public void a(ComicSectionPicInfo comicSectionPicInfo) {
            if (g.a() && comicSectionPicInfo != null) {
                g.a("comic_reader_startup", g.d, "pic download fail, comidId = " + comicSectionPicInfo.comicId + ", sectionId = " + comicSectionPicInfo.sectionId + ", picId = " + comicSectionPicInfo.picId + ",picUrl = " + comicSectionPicInfo.picUrl);
            }
        }
    };

    public void a(Bundle bundle) {
        if (g.a()) {
            g.a("comic_reader_startup", g.d, "QRRequestPicsStep doStep");
        }
        this.h.a(this.f, this.a);
    }
}
