package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.webkit.WebView;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.QbSdk.PreInitCallback;

final class c extends Handler {
    final /* synthetic */ Context a;
    final /* synthetic */ PreInitCallback b;
    final /* synthetic */ y c;

    c(Looper looper, Context context, PreInitCallback preInitCallback, y yVar) {
        this.a = context;
        this.b = preInitCallback;
        this.c = yVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                ar c = aq.a().c();
                if (c != null) {
                    this.c.a("init_x5_webview", (byte) 1);
                    IX5WebViewBase a = c.a(this.a);
                    this.c.a("init_x5_webview", (byte) 2);
                    if (!(a == null || a.getView() == null)) {
                        this.c.a("init_all", (byte) 2);
                        this.c.a(a.hashCode());
                    }
                }
                if (this.b != null) {
                    this.b.onViewInitFinished(true);
                    return;
                }
                return;
            case 2:
                WebView webView = new WebView(this.a);
                if (this.b != null) {
                    this.b.onViewInitFinished(false);
                    return;
                }
                return;
            case 3:
                if (this.b != null) {
                    this.b.onCoreInitFinished();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
