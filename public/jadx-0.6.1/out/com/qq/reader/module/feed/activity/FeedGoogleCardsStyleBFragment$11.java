package com.qq.reader.module.feed.activity;

import android.view.View;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.c;

class FeedGoogleCardsStyleBFragment$11 extends c {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;

    FeedGoogleCardsStyleBFragment$11(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment) {
        this.a = feedGoogleCardsStyleBFragment;
    }

    public void a(View view) {
        if (this.a.getActivity() != null) {
            StatisticsManager.a().a(7).c();
            j.a(16, 2);
            o.b(this.a.getActivity(), "", "2");
        }
    }
}
