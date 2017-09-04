package com.qq.reader.module.feed.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.common.c.a;

class FeedGoogleCardsStyleBFragment$10 extends BroadcastReceiver {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;

    FeedGoogleCardsStyleBFragment$10(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment) {
        this.a = feedGoogleCardsStyleBFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (a.cs.equalsIgnoreCase(intent.getAction())) {
            FeedGoogleCardsStyleBFragment.access$1700(this.a).sendEmptyMessage(8012);
        }
    }
}
