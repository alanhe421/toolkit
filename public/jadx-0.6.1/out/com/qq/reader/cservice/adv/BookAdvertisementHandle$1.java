package com.qq.reader.cservice.adv;

import com.qq.reader.common.readertask.ordinal.ReaderIOTask;

class BookAdvertisementHandle$1 extends ReaderIOTask {
    final /* synthetic */ e this$0;
    final /* synthetic */ String val$bid;

    BookAdvertisementHandle$1(e eVar, String str) {
        this.this$0 = eVar;
        this.val$bid = str;
    }

    public void run() {
        super.run();
        this.this$0.b(this.val$bid);
        this.this$0.c(this.val$bid);
    }
}
