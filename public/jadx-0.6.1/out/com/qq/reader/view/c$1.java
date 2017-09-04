package com.qq.reader.view;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;

/* compiled from: BlueCircleBlackBGDialog */
class c$1 implements OnKeyListener {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 4;
    }
}
