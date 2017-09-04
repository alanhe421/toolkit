package com.qq.reader.module.qmessage.loader;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.qmessage.data.b;

class MessageDataLoader$1 extends ReaderDBTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ long val$messageID;

    MessageDataLoader$1(a aVar, long j) {
        this.this$0 = aVar;
        this.val$messageID = j;
    }

    public void run() {
        b.b().a(this.val$messageID);
    }
}
