package com.qq.reader.module.feed.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.common.c.a;

class FeedGoogleCardsFragment$18 extends BroadcastReceiver {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$18(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (a.cs.equalsIgnoreCase(intent.getAction())) {
            FeedGoogleCardsFragment.access$2600(this.a).sendEmptyMessage(8012);
        }
    }
}
