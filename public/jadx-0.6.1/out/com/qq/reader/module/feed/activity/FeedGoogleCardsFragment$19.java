package com.qq.reader.module.feed.activity;

import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.cservice.adv.a;

class FeedGoogleCardsFragment$19 implements e<String, b> {
    final /* synthetic */ a a;
    final /* synthetic */ FeedGoogleCardsFragment b;

    FeedGoogleCardsFragment$19(FeedGoogleCardsFragment feedGoogleCardsFragment, a aVar) {
        this.b = feedGoogleCardsFragment;
        this.a = aVar;
    }

    public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
        return false;
    }

    public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
        switch (this.a.t()) {
            case 0:
                FeedGoogleCardsFragment.access$2700(this.b);
                i.a("event_F98", null, this.b.getApplicationContext());
                break;
        }
        return false;
    }
}
