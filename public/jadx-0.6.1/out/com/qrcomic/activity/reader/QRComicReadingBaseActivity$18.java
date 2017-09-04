package com.qrcomic.activity.reader;

class QRComicReadingBaseActivity$18 implements Runnable {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$18(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void run() {
        if (!this.a.isFinishing() && QRComicReadingBaseActivity.h(this.a).isShowing()) {
            QRComicReadingBaseActivity.h(this.a).dismiss();
        }
        this.a.A();
    }
}
