package com.qq.reader.view.web;

import android.os.Build.VERSION;
import com.qq.reader.common.monitor.debug.c;

/* compiled from: PopRookieDialog */
class l$1 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ l b;

    l$1(l lVar, String str) {
        this.b = lVar;
        this.a = str;
    }

    public void run() {
        c.d("WEBVIEW", "start load web view ");
        if (VERSION.SDK_INT >= 24) {
            this.b.b.loadUrl(this.a);
        } else {
            this.b.b.b(this.a);
        }
    }
}
