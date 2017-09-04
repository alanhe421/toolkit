package com.qrcomic.activity.reader;

import android.content.Intent;
import com.qrcomic.a.d;

class QRComicReadingBaseActivity$27 extends d {
    final /* synthetic */ Intent a;
    final /* synthetic */ QRComicReadingBaseActivity b;

    QRComicReadingBaseActivity$27(QRComicReadingBaseActivity qRComicReadingBaseActivity, Intent intent) {
        this.b = qRComicReadingBaseActivity;
        this.a = intent;
    }

    public void run() {
        if (this.b.a != null) {
            this.b.a.f().f().a(this.a, this.b.getApplicationContext());
        }
    }
}
