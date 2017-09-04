package com.qq.reader.module.feed.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.common.c.a;
import com.qq.reader.module.bookshelf.j;

class FeedGoogleCardsFragment$12 extends BroadcastReceiver {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$12(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (a.ct.equals(intent.getAction())) {
            j.b(FeedGoogleCardsFragment.access$000(this.a));
        }
    }
}
