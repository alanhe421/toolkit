package com.qq.reader.view;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;

/* compiled from: ShareDialog */
class aj$5 implements c {
    final /* synthetic */ aj a;

    aj$5(aj ajVar) {
        this.a = ajVar;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        aj.b(this.a, readerProtocolTask, str);
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        aj.c(this.a).sendEmptyMessage(100006);
    }
}
