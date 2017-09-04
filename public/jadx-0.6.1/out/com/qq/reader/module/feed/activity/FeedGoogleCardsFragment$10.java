package com.qq.reader.module.feed.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;

class FeedGoogleCardsFragment$10 extends BroadcastReceiver {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$10(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
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
                FeedGoogleCardsFragment.access$1102(this.a, 0);
            }
            FeedGoogleCardsFragment.access$2100(this.a).sendMessage(obtain);
        }
    }
}
