package com.qrcomic.activity.reader;

import com.qrcomic.util.h.a;
import com.qrcomic.util.h.b;

class QRComicReadingBaseActivity$9 implements Runnable {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$9(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void run() {
        try {
            QRComicReadingBaseActivity.e(this.a);
            if (this.a.s != null && this.a.s.getVisibility() == 0) {
                this.a.s.setVisibility(8);
                a.a(b.a, false, this.a.Z.n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
