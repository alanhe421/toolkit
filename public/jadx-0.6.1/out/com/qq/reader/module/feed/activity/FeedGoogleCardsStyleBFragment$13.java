package com.qq.reader.module.feed.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.common.monitor.i;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.qurl.c;

class FeedGoogleCardsStyleBFragment$13 implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ FeedGoogleCardsStyleBFragment b;

    FeedGoogleCardsStyleBFragment$13(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment, a aVar) {
        this.b = feedGoogleCardsStyleBFragment;
        this.a = aVar;
    }

    public void onClick(View view) {
        if (this.b.getActivity() != null) {
            if (this.a.g().toLowerCase().endsWith(".gif") && FeedGoogleCardsStyleBFragment.access$1800(this.b) != null) {
                FeedGoogleCardsStyleBFragment.access$000(this.b);
            }
            a.a(this.b.getApplicationContext(), "ADV_CLICK_FLAG", true);
            String h = this.a.h();
            i.a("event_A183", FeedGoogleCardsStyleBFragment.access$2000(this.b), this.b.getApplicationContext());
            try {
                c.a(this.b.getActivity(), h);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
