package com.qrcomic.activity.reader;

import b.a.a.a.a.a.g;
import com.qrcomic.a.g.a;
import com.qrcomic.activity.reader.b.e;
import com.qrcomic.entity.QRComicBuyReqInfo;
import com.qrcomic.manager.b;
import java.util.ArrayList;

class QRComicReadingBaseActivity$35 extends a {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$35(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    protected void a() {
    }

    protected void b() {
        b.a().a(this.a.getResources().getString(g.mobile_net), 2);
        if (!this.a.Z.l) {
            if (this.a.Z.P != null) {
                this.a.Z.P.a(this.a.Z, null, true);
            } else {
                e.a(0, this.a.Z).a(null);
            }
        }
        QRComicReadingBaseActivity.a(this.a);
        this.a.Z.k();
        QRComicReadingBaseActivity.b(this.a);
    }

    protected void c() {
        if (!this.a.Z.l) {
            if (this.a.Z.P != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new QRComicBuyReqInfo(this.a.Z.n));
                this.a.Z.P.a(this.a.Z, arrayList);
            } else {
                e.a(0, this.a.Z).a(null);
            }
        }
        QRComicReadingBaseActivity.a(this.a);
        this.a.Z.k();
        QRComicReadingBaseActivity.b(this.a);
    }

    protected void d() {
        super.d();
    }
}
