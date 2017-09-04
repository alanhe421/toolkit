package com.qq.reader.module.feed.activity;

import android.widget.AbsListView;
import com.bumptech.glide.i;
import com.qq.reader.common.imageloader.core.a.a;

class FeedGoogleCardsFragment$5 extends a {
    final /* synthetic */ FeedGoogleCardsFragment a;
    private int b;

    FeedGoogleCardsFragment$5(FeedGoogleCardsFragment feedGoogleCardsFragment, i iVar, boolean z, boolean z2) {
        this.a = feedGoogleCardsFragment;
        super(iVar, z, z2);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (FeedGoogleCardsFragment.access$1400(this.a) != null) {
            FeedGoogleCardsFragment.access$1400(this.a).onScrollStateChanged(absListView, i);
        }
        if (FeedGoogleCardsFragment.access$1500(this.a) != null) {
            FeedGoogleCardsFragment.access$1500(this.a).onScrollStateChanged(absListView, i);
        }
        super.onScrollStateChanged(absListView, i);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        super.onScroll(absListView, i, i2, i3);
        if (i > 0 || !FeedGoogleCardsFragment.access$1600(this.a).g()) {
            FeedGoogleCardsFragment.access$1600(this.a).d();
        } else {
            FeedGoogleCardsFragment.access$1600(this.a).c();
        }
        if (this.b < i) {
        }
        if (this.b > i && i3 >= 50 && i >= 48 - i2) {
            FeedGoogleCardsFragment.access$1700(this.a);
        }
        this.b = i;
        FeedGoogleCardsFragment.access$1800(this.a, absListView, i);
    }
}
