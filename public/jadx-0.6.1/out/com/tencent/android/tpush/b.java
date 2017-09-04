package com.tencent.android.tpush;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import com.tencent.android.tpush.a.a;

/* compiled from: ProGuard */
class b implements Runnable {
    Message a = this.b.j.obtainMessage();
    final /* synthetic */ XGDownloadService b;
    private Intent c;
    private int d;

    public b(XGDownloadService xGDownloadService, Intent intent, int i) {
        this.b = xGDownloadService;
        this.c = intent;
        this.d = i;
    }

    public void run() {
        this.a.what = 0;
        this.a.arg1 = this.d;
        Bundle bundle = new Bundle();
        this.a.setData(this.c.getExtras());
        try {
            if (!this.b.d.exists()) {
                this.b.d.mkdirs();
            }
            if (!this.b.e.exists()) {
                this.b.e.createNewFile();
            }
            if (this.b.a(this.b.b, this.b.e, this.d) > 0) {
                this.b.j.sendMessage(this.a);
            }
        } catch (Throwable e) {
            a.c(XGDownloadService.c, "downloadRunnable", e);
            this.b.j.sendMessage(this.a);
        }
    }
}
