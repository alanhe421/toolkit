package com.qrcomic.activity.reader;

import com.qrcomic.activity.reader.a.e;
import com.qrcomic.activity.reader.a.f;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.util.g;

class QRComicReadingBaseActivity$13 implements f {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$13(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void a() {
        QRComicReadingBaseActivity qRComicReadingBaseActivity = this.a;
        qRComicReadingBaseActivity.ag++;
        this.a.b(this.a.Z.n, this.a.Z.o.b);
        this.a.Z.d(0);
        this.a.Z.B = ((ComicSectionPicInfo) this.a.Z.r.get(this.a.Z.C)).picId;
        this.a.Z.c = System.currentTimeMillis();
        this.a.Z.e = (int) (this.a.Z.c - this.a.Z.b);
        if (this.a.Z.o.t == 0 || !this.a.Z.A) {
            this.a.u.b(this.a.Z, new e(this) {
                final /* synthetic */ QRComicReadingBaseActivity$13 a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.a.Z.d = System.currentTimeMillis();
                    this.a.a.Z.f = (int) (this.a.a.Z.d - this.a.a.Z.c);
                    this.a.a.Z.g = (int) (this.a.a.Z.d - this.a.a.Z.b);
                    this.a.a.an.sendEmptyMessage(0);
                    this.a.a.z();
                }

                public void a(ComicSectionPicInfo comicSectionPicInfo) {
                    if (g.a()) {
                        g.a(QRComicReadingBaseActivity.Y(), g.d, "request comics error, sectionId is " + comicSectionPicInfo.sectionId + ", picId is" + comicSectionPicInfo.picId);
                    }
                    this.a.a.an.sendEmptyMessage(0);
                }
            });
        } else {
            this.a.an.sendEmptyMessage(0);
        }
    }

    public void b() {
    }

    public void c() {
    }
}
