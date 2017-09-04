package com.tencent.smtt.utils;

class f implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ d b;

    f(d dVar, int i) {
        this.b = dVar;
        this.a = i;
    }

    public void run() {
        this.b.e.setText("已下载" + this.a + "%");
    }
}
