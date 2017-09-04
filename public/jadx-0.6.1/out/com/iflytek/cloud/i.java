package com.iflytek.cloud;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class i extends Handler {
    final /* synthetic */ c a;

    i(c cVar, Looper looper) {
        this.a = cVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        if (this.a.a != null) {
            this.a.a.a(0);
        }
    }
}
