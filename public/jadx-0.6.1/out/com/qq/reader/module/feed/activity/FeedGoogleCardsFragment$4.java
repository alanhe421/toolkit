package com.qq.reader.module.feed.activity;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.feed.card.FeedColumnPersonalRecommendCard;
import com.qq.reader.module.feed.loader.f;
import com.qq.reader.view.pullupdownlist.XListView$a;

class FeedGoogleCardsFragment$4 implements XListView$a {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$4(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public void a() {
        if (this.a.mFeedAdapter.i() instanceof FeedColumnPersonalRecommendCard) {
            String bB = d.bB(ReaderApplication.getApplicationImp());
            if (!TextUtils.isEmpty(bB)) {
                f fVar;
                if (bB.contains("-")) {
                    String[] split = bB.split("-");
                    fVar = new f();
                    fVar.a = split[0];
                    fVar.b = Integer.parseInt(split[1]);
                    FeedGoogleCardsFragment.access$1300(this.a, fVar, 1);
                    return;
                }
                fVar = new f();
                fVar.a = bB;
                fVar.b = 0;
                FeedGoogleCardsFragment.access$1300(this.a, fVar, 1);
                return;
            }
            return;
        }
        FeedGoogleCardsFragment.access$1300(this.a, this.a.mFeedAdapter.f(), 0);
        i.a("event_C112", null, ReaderApplication.getApplicationImp());
    }
}
