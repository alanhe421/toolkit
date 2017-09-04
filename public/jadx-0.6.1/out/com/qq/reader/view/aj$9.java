package com.qq.reader.view;

import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;

/* compiled from: ShareDialog */
class aj$9 implements c {
    final /* synthetic */ aj a;

    aj$9(aj ajVar) {
        this.a = ajVar;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        f.a("upload", str);
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
    }
}
