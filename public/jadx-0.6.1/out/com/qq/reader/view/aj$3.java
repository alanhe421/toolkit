package com.qq.reader.view;

import com.qq.reader.common.monitor.debug.c;

/* compiled from: ShareDialog */
class aj$3 implements Runnable {
    final /* synthetic */ aj a;

    aj$3(aj ajVar) {
        this.a = ajVar;
    }

    public void run() {
        try {
            aj.b(this.a, aj.g(this.a));
        } catch (Exception e) {
            c.e("ShareDialog", e.getMessage());
        }
    }
}
