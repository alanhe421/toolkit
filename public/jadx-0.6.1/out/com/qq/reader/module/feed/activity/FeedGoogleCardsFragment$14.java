package com.qq.reader.module.feed.activity;

import android.content.Intent;
import com.qq.reader.activity.PersonalityEmptyActivity;
import com.qq.reader.common.login.a;

class FeedGoogleCardsFragment$14 implements a {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$14(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public void a(int i) {
        switch (i) {
            case 1:
                this.a.getActivity().startActivity(new Intent(this.a.getActivity(), PersonalityEmptyActivity.class));
                this.a.getActivity().overridePendingTransition(0, 0);
                return;
            default:
                return;
        }
    }
}
