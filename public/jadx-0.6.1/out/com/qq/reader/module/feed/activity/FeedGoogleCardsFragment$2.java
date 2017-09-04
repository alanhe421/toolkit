package com.qq.reader.module.feed.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.appconfig.a.d;

class FeedGoogleCardsFragment$2 implements OnClickListener {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$2(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public void onClick(View view) {
        if (this.a.getActivity() != null) {
            ((MainActivity) this.a.getActivity()).a().d(3);
            d.d(this.a.getApplicationContext(), false);
        }
    }
}
