package com.qq.reader.module.feed.data.impl;

import com.qq.reader.common.readertask.ordinal.ReaderIOTask;

class FeedBaseCard$2 extends ReaderIOTask {
    final /* synthetic */ FeedBaseCard this$0;

    FeedBaseCard$2(FeedBaseCard feedBaseCard) {
        this.this$0 = feedBaseCard;
    }

    public void run() {
        super.run();
        if (FeedBaseCard.access$000(this.this$0) != null) {
            FeedBaseCard.access$000(this.this$0).b(this.this$0);
        }
    }
}
