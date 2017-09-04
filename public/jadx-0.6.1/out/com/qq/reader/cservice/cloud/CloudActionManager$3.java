package com.qq.reader.cservice.cloud;

import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class CloudActionManager$3 extends ReaderDBTask {
    final /* synthetic */ b this$0;
    final /* synthetic */ String val$bid;
    final /* synthetic */ int val$privateProperty;

    CloudActionManager$3(b bVar, String str, int i) {
        this.this$0 = bVar;
        this.val$bid = str;
        this.val$privateProperty = i;
    }

    public void run() {
        i.c().b(this.val$bid, this.val$privateProperty);
    }
}
