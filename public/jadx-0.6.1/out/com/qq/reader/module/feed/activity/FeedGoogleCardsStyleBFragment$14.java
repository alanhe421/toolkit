package com.qq.reader.module.feed.activity;

import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.module.feed.data.impl.e;
import com.qq.reader.module.feed.loader.d;

class FeedGoogleCardsStyleBFragment$14 extends ReaderIOTask {
    final /* synthetic */ FeedGoogleCardsStyleBFragment this$0;
    final /* synthetic */ e val$firstDataPackage;

    FeedGoogleCardsStyleBFragment$14(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment, e eVar) {
        this.this$0 = feedGoogleCardsStyleBFragment;
        this.val$firstDataPackage = eVar;
    }

    public void run() {
        super.run();
        d.b().a(this.val$firstDataPackage, FeedGoogleCardsStyleBFragment.access$1200(this.this$0));
    }
}
