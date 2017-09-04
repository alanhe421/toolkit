package com.qrcomic.activity.reader;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;

class QRComicReadingBaseActivity$4 implements OnKeyListener {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$4(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        if (keyEvent.getAction() == 0) {
            this.a.T();
        }
        return true;
    }
}
