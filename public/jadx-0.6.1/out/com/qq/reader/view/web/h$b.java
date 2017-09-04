package com.qq.reader.view.web;

import android.os.Handler;
import android.os.Message;

/* compiled from: OpenMonthlyDialog */
protected class h$b extends Handler {
    final /* synthetic */ h a;

    public h$b(h hVar) {
        this.a = hVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.a.a(message);
    }
}
