package com.qq.reader.module.feed.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;

class FeedGoogleCardsStyleBFragment$3 extends BroadcastReceiver {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;

    FeedGoogleCardsStyleBFragment$3(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment) {
        this.a = feedGoogleCardsStyleBFragment;
    }

    public void onReceive(Context context, Intent intent) {
        int i = 1;
        boolean booleanExtra = intent.getBooleanExtra("loginSuccess", false);
        boolean booleanExtra2 = intent.getBooleanExtra("hasLogin", true);
        if (booleanExtra) {
            Message obtain = Message.obtain();
            obtain.what = 10000001;
            if (booleanExtra2) {
                if (!booleanExtra2) {
                    i = 0;
                }
                obtain.arg1 = i;
                FeedGoogleCardsStyleBFragment.access$1002(this.a, 0);
            }
            FeedGoogleCardsStyleBFragment.access$1100(this.a).sendMessage(obtain);
        }
    }
}
