package com.qrcomic.activity.reader;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.qrcomic.entity.f;

class QRComicReadingBaseActivity$7 implements OnClickListener {
    final /* synthetic */ f a;
    final /* synthetic */ QRComicReadingBaseActivity b;

    QRComicReadingBaseActivity$7(QRComicReadingBaseActivity qRComicReadingBaseActivity, f fVar) {
        this.b = qRComicReadingBaseActivity;
        this.a = fVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.a(this.b.Z.i.a, this.a.b, this.a.f, "");
    }
}
