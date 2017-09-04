package com.qq.reader.module.feed.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;

class FeedGoogleCardsStyleBFragment$2 extends BroadcastReceiver {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;

    FeedGoogleCardsStyleBFragment$2(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment) {
        this.a = feedGoogleCardsStyleBFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (a.cH.equals(intent.getAction())) {
            i.a("event_A188", null, this.a.getApplicationContext());
            StatisticsManager.a().a("event_A188", null);
            i.a("event_A184", null, this.a.getApplicationContext());
            StatisticsManager.a().a("event_A184", null);
            i.a("event_A189", null, this.a.getApplicationContext());
            StatisticsManager.a().a("event_A189", null);
        }
    }
}
