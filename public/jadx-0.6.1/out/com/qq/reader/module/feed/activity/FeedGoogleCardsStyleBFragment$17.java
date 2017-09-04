package com.qq.reader.module.feed.activity;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class FeedGoogleCardsStyleBFragment$17 implements AnimationListener {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;

    FeedGoogleCardsStyleBFragment$17(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment) {
        this.a = feedGoogleCardsStyleBFragment;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (animation == this.a.animSet[this.a.curAnimSetIndex]) {
            FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment = this.a;
            feedGoogleCardsStyleBFragment.curAnimSetIndex++;
            if (this.a.curAnimSetIndex >= 0 && this.a.curAnimSetIndex < this.a.animSet.length) {
                this.a.animSet[this.a.curAnimSetIndex].reset();
                FeedGoogleCardsStyleBFragment.access$100(this.a).startAnimation(this.a.animSet[this.a.curAnimSetIndex]);
            }
        }
    }
}
