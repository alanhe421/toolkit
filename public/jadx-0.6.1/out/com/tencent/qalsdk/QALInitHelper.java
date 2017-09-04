package com.tencent.qalsdk;

import com.tencent.qalsdk.base.remote.IBaseActionListener;
import com.tencent.qalsdk.sdk.e;
import com.tencent.qalsdk.util.QLog;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QALInitHelper {
    private static int MAX_LEN = 5000;
    private static QALInitHelper inst = new QALInitHelper();
    private static String tag = "QALInitHelper";
    private ConcurrentLinkedQueue<QALSendData> waitQueue = new ConcurrentLinkedQueue();

    public static QALInitHelper instance() {
        return inst;
    }

    public void addToQueue(String str, byte[] bArr, IBaseActionListener iBaseActionListener) {
        QLog.d(tag, "init not ok,add to queue:" + str);
        QALSendData qALSendData = new QALSendData();
        qALSendData.cmd = str;
        qALSendData.body = bArr;
        qALSendData.listener = iBaseActionListener;
        if (this.waitQueue.size() > MAX_LEN) {
            QLog.e("QALInitHelper", 1, " queue full:" + MAX_LEN);
        } else if (!this.waitQueue.add(qALSendData)) {
            QLog.e("QALInitHelper", 1, "add queue error");
        }
    }

    public void sendMsg() {
        synchronized (this.waitQueue) {
            while (!this.waitQueue.isEmpty()) {
                QALSendData qALSendData = (QALSendData) this.waitQueue.poll();
                if (qALSendData != null) {
                    QLog.i(tag, 4, "init ok,send saved msg :" + qALSendData.cmd + ":" + qALSendData.body.length);
                    e.b().a(e.b().f(), qALSendData.cmd, qALSendData.body, qALSendData.listener, e.b().c(qALSendData.body.length), true);
                }
            }
        }
    }
}
