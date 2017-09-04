package com.qrcomic.activity.reader;

import android.os.Message;
import com.qrcomic.activity.reader.a.f;

class QRComicReadingBaseActivity$20 implements f {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$20(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void a() {
        Message.obtain(this.a.an, 9, this.a.Z.o.c).sendToTarget();
        this.a.a(this.a.Z.o);
    }

    public void b() {
    }

    public void c() {
    }
}
