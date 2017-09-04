package com.qq.reader.module.feed.activity;

import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.module.feed.data.impl.e;
import com.qq.reader.module.feed.loader.c;

class FeedGoogleCardsFragment$20 extends ReaderIOTask {
    final /* synthetic */ FeedGoogleCardsFragment this$0;
    final /* synthetic */ e val$firstDataPackage;

    FeedGoogleCardsFragment$20(FeedGoogleCardsFragment feedGoogleCardsFragment, e eVar) {
        this.this$0 = feedGoogleCardsFragment;
        this.val$firstDataPackage = eVar;
    }

    public void run() {
        super.run();
        c.b().a(this.val$firstDataPackage, FeedGoogleCardsFragment.access$2200(this.this$0));
    }
}
