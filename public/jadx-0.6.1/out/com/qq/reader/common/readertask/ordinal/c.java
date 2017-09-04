package com.qq.reader.common.readertask.ordinal;

/* compiled from: ReaderJSONNetTaskListener */
public interface c {
    void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception);

    void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j);
}
