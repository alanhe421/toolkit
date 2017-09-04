package com.qrcomic.activity.reader;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.qrcomic.c.c;

class QRComicReadingBaseActivity$2 implements OnClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ QRComicReadingBaseActivity b;

    QRComicReadingBaseActivity$2(QRComicReadingBaseActivity qRComicReadingBaseActivity, c cVar) {
        this.b = qRComicReadingBaseActivity;
        this.a = cVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.a.f().c().a("event_F287", null, this.b.getApplicationContext());
        this.a.a(this.b.Z, this.b.ac);
        this.b.A();
    }
}
