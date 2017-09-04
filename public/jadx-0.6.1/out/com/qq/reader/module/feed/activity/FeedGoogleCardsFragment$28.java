package com.qq.reader.module.feed.activity;

import android.view.View;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.c;

class FeedGoogleCardsFragment$28 extends c {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$28(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public void a(View view) {
        if (this.a.getActivity() != null) {
            StatisticsManager.a().a(7).c();
            j.a(16, 2);
            i.a("event_F4", null, this.a.getContext());
            o.b(this.a.getActivity(), "", "2", FeedGoogleCardsFragment.access$400(this.a).getText().toString());
        }
    }
}
