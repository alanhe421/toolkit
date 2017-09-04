package com.qrcomic.activity.reader;

import com.qrcomic.a.d;
import com.qrcomic.manager.b;
import com.qrcomic.util.g;

class QRComicReadingBaseActivity$34 extends d {
    final /* synthetic */ String a;
    final /* synthetic */ QRComicReadingBaseActivity b;

    QRComicReadingBaseActivity$34(QRComicReadingBaseActivity qRComicReadingBaseActivity, String str) {
        this.b = qRComicReadingBaseActivity;
        this.a = str;
    }

    public void run() {
        try {
            if (this.b.v != null) {
                this.b.ab = this.b.v.c(b.a().b().a(), this.a);
                if (!g.a()) {
                    return;
                }
                if (this.b.ab != null) {
                    g.b(QRComicReadingBaseActivity.Y(), g.d, "collection status: " + this.b.ab.k);
                } else {
                    g.b(QRComicReadingBaseActivity.Y(), g.d, "this comic is not in collection table.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
