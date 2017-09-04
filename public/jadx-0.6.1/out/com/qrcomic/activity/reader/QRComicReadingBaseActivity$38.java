package com.qrcomic.activity.reader;

import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.widget.reader.QRComicScrollReaderListView.c;

class QRComicReadingBaseActivity$38 implements c {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$38(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void a() {
        this.a.Z.a(c.e().b().e(), null);
    }

    public void b() {
        if (this.a.O() && this.a.aj != null) {
            this.a.aj.a(true, "onFooter");
        }
        this.a.Z.b(c.e().b().e(), null);
    }

    public void a(ComicSectionPicInfo comicSectionPicInfo) {
        if (comicSectionPicInfo != null) {
            try {
                if (comicSectionPicInfo.mComicRecommendPageInfo != null) {
                    this.a.Z.K = true;
                } else {
                    this.a.Z.K = false;
                    QRComicReadingBaseActivity qRComicReadingBaseActivity = this.a;
                    qRComicReadingBaseActivity.ag++;
                    this.a.Z.B = comicSectionPicInfo.picId;
                    int intValue = ((Integer) this.a.Z.v.get(comicSectionPicInfo.sectionId)).intValue();
                    if (intValue != this.a.Z.E) {
                        QRComicReadingBaseActivity.a(this.a, intValue > this.a.Z.E ? 1 : 0);
                    } else if (this.a.Z.r != null) {
                        this.a.Z.d(comicSectionPicInfo.index);
                    }
                    this.a.b(this.a.Z.n, comicSectionPicInfo.sectionId);
                    this.a.y();
                }
                if (this.a.n != null) {
                    this.a.n.c();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
