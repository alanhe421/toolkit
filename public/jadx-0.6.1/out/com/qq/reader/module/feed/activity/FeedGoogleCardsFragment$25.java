package com.qq.reader.module.feed.activity;

import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.rookie.presenter.a;

class FeedGoogleCardsFragment$25 extends c {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$25(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public void a(View view) {
        int i = 0;
        try {
            if (d.ap(ReaderApplication.getApplicationImp())) {
                d.r(ReaderApplication.getApplicationImp(), false);
            } else if (!a.a().c()) {
                i = 1;
            }
            o.a(this.a.getActivity(), i, null, "A");
            i.a("event_A262", null, ReaderApplication.getApplicationImp());
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
    }
}
