package com.qq.reader.module.feed.activity;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.qq.reader.common.monitor.debug.RookieDebugDialog;

class FeedGoogleCardsFragment$26 implements OnLongClickListener {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$26(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public boolean onLongClick(View view) {
        new RookieDebugDialog(this.a.getActivity()).show();
        return true;
    }
}
