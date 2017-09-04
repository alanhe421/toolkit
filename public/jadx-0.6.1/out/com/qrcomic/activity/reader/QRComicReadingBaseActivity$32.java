package com.qrcomic.activity.reader;

import android.view.View;
import com.qrcomic.c.c.b;

class QRComicReadingBaseActivity$32 implements b {
    final /* synthetic */ View a;
    final /* synthetic */ QRComicReadingBaseActivity b;

    QRComicReadingBaseActivity$32(QRComicReadingBaseActivity qRComicReadingBaseActivity, View view) {
        this.b = qRComicReadingBaseActivity;
        this.a = view;
    }

    public void a(boolean z) {
        this.a.setVisibility(z ? 0 : 4);
    }
}
