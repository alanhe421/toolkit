package com.tencent.qalsdk;

import android.content.Context;

/* compiled from: QALBroadcastReceiver */
class a implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ byte[] c;
    final /* synthetic */ Context d;
    final /* synthetic */ QALOfflinePushListener e;
    final /* synthetic */ QALBroadcastReceiver f;

    a(QALBroadcastReceiver qALBroadcastReceiver, String str, String str2, byte[] bArr, Context context, QALOfflinePushListener qALOfflinePushListener) {
        this.f = qALBroadcastReceiver;
        this.a = str;
        this.b = str2;
        this.c = bArr;
        this.d = context;
        this.e = qALOfflinePushListener;
    }

    public void run() {
        QALOffLineMsg qALOffLineMsg = new QALOffLineMsg();
        qALOffLineMsg.setID(this.a);
        qALOffLineMsg.setCmd(this.b);
        qALOffLineMsg.setBody(this.c);
        qALOffLineMsg.setContext(this.d);
        this.e.onPushMsg(qALOffLineMsg);
    }
}
