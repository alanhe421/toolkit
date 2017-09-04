package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;

final class d extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ y b;
    final /* synthetic */ Boolean c;
    final /* synthetic */ Handler d;

    d(Context context, y yVar, Boolean bool, Handler handler) {
        this.a = context;
        this.b = yVar;
        this.c = bool;
        this.d = handler;
    }

    public void run() {
        if (z.a().a(true, this.a) == 0) {
            z.a().a(this.a, true);
        }
        f.a(true).a(this.a, false, false, null);
        aq a = aq.a();
        a.a(this.a, this.b);
        if (TbsShareManager.isThirdPartyApp(this.a) && this.c.booleanValue()) {
            if (!TbsShareManager.forceLoadX5FromTBSDemo(this.a.getApplicationContext())) {
                TbsDownloader.a(this.a, false, null);
            }
            if (!(WebView.mSysWebviewCreated || QbSdk.i)) {
                QbSdk.a = false;
            }
        }
        boolean b = a.b();
        this.d.sendEmptyMessage(3);
        if (b) {
            this.d.sendEmptyMessage(1);
        } else {
            this.d.sendEmptyMessage(2);
        }
    }
}
