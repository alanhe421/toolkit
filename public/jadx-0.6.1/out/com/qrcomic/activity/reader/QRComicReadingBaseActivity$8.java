package com.qrcomic.activity.reader;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.qrcomic.entity.f;

class QRComicReadingBaseActivity$8 implements OnClickListener {
    final /* synthetic */ f a;
    final /* synthetic */ QRComicReadingBaseActivity b;

    QRComicReadingBaseActivity$8(QRComicReadingBaseActivity qRComicReadingBaseActivity, f fVar) {
        this.b = qRComicReadingBaseActivity;
        this.a = fVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.c(this.a, 0);
        this.b.E();
        this.b.ai = false;
        if (this.b.ak != null) {
            this.b.ak.setTouchEventEnabled(true);
        }
    }
}
