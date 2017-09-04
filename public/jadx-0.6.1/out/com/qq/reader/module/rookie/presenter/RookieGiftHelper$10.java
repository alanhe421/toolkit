package com.qq.reader.module.rookie.presenter;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class RookieGiftHelper$10 extends ReaderDBTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ int val$giftId;

    RookieGiftHelper$10(a aVar, int i) {
        this.this$0 = aVar;
        this.val$giftId = i;
    }

    public void run() {
        super.run();
        this.this$0.a(a.d, "rookie_gift_table", "giftid= " + this.val$giftId);
    }
}
