package com.qq.reader.module.feed.activity;

import android.widget.AbsListView;
import com.bumptech.glide.i;
import com.qq.reader.common.imageloader.core.a.a;

class FeedGoogleCardsStyleBFragment$20 extends a {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;
    private int b;

    FeedGoogleCardsStyleBFragment$20(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment, i iVar, boolean z, boolean z2) {
        this.a = feedGoogleCardsStyleBFragment;
        super(iVar, z, z2);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        super.onScrollStateChanged(absListView, i);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        super.onScroll(absListView, i, i2, i3);
        if (i > 0 || !FeedGoogleCardsStyleBFragment.access$700(this.a).g()) {
            FeedGoogleCardsStyleBFragment.access$700(this.a).d();
        } else {
            FeedGoogleCardsStyleBFragment.access$700(this.a).c();
        }
        if (this.b < i) {
        }
        if (this.b > i && i3 >= 50 && i >= 48 - i2) {
            FeedGoogleCardsStyleBFragment.access$800(this.a);
        }
        this.b = i;
        FeedGoogleCardsStyleBFragment.access$900(this.a, i);
    }
}
