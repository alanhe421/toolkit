package com.qrcomic.activity.reader;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import b.a.a.a.a.a.e;
import com.qrcomic.c.c;

class QRComicReadingBaseActivity$30 implements OnClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ View b;
    final /* synthetic */ QRComicReadingBaseActivity c;

    QRComicReadingBaseActivity$30(QRComicReadingBaseActivity qRComicReadingBaseActivity, c cVar, View view) {
        this.c = qRComicReadingBaseActivity;
        this.a = cVar;
        this.b = view;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        QRComicReadingBaseActivity.a(this.c, this.a, this.b.findViewById(e.reader_secret_state));
    }
}
