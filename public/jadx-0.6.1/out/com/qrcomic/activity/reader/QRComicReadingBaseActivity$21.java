package com.qrcomic.activity.reader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class QRComicReadingBaseActivity$21 extends BroadcastReceiver {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$21(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (!"com.qq.reader.openVip".equals(intent.getAction()) && "com.qq.reader.loginok".equals(intent.getAction())) {
            this.a.t();
            QRComicReadingBaseActivity.i(this.a);
            if (QRComicReadingBaseActivity.j(this.a) != null) {
                QRComicReadingBaseActivity.j(this.a).a(true);
                QRComicReadingBaseActivity.a(this.a, null);
            }
        }
    }
}
