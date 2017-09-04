package com.tencent.smtt.utils;

import android.widget.Toast;

class g implements Runnable {
    final /* synthetic */ d a;

    g(d dVar) {
        this.a = dVar;
    }

    public void run() {
        Toast.makeText(this.a.b, "下载失败，请检查网络", 0).show();
    }
}
