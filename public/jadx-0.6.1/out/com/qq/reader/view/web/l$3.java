package com.qq.reader.view.web;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import com.qq.reader.common.d.a;
import com.qq.reader.common.utils.t;
import com.qq.reader.view.r;

/* compiled from: PopRookieDialog */
class l$3 extends r {
    final /* synthetic */ l a;
    final /* synthetic */ OnDismissListener b;
    final /* synthetic */ int c;

    l$3(l lVar, OnDismissListener onDismissListener, int i) {
        this.a = lVar;
        this.b = onDismissListener;
        this.c = i;
    }

    public t a() {
        return this.a.c();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.b != null) {
            this.b.onDismiss(dialogInterface);
        }
        if (this.c == 0) {
            a.a(this.a.b);
        } else {
            a.a(this.a.c);
        }
    }
}
