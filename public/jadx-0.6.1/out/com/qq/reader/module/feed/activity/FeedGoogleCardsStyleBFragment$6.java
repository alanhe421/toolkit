package com.qq.reader.module.feed.activity;

import com.qq.reader.common.login.a;

class FeedGoogleCardsStyleBFragment$6 implements a {
    final /* synthetic */ String a;
    final /* synthetic */ FeedGoogleCardsStyleBFragment b;

    FeedGoogleCardsStyleBFragment$6(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment, String str) {
        this.b = feedGoogleCardsStyleBFragment;
        this.a = str;
    }

    public void a(int i) {
        if (i == 1) {
            this.b.qurlForRookieLogin = this.a;
            FeedGoogleCardsStyleBFragment.access$1300(this.b).removeMessages(10000003);
            FeedGoogleCardsStyleBFragment.access$1400(this.b).sendEmptyMessage(10000003);
        }
    }
}
