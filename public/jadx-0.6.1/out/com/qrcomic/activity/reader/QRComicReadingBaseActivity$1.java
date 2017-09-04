package com.qrcomic.activity.reader;

import android.os.Vibrator;

class QRComicReadingBaseActivity$1 extends QRComicReadingBaseActivity$b {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$1(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
        super();
    }

    public void run() {
        QRComicReadingBaseActivity.aV = true;
        if (!this.a.Z.K) {
            ((Vibrator) this.a.getSystemService("vibrator")).vibrate(30);
            this.a.a(this.b);
            this.a.ar = false;
        }
    }
}
