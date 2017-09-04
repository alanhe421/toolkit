package com.qq.reader.module.feed.activity;

import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.cservice.adv.a;

class FeedGoogleCardsStyleBFragment$12 implements e<String, b> {
    final /* synthetic */ a a;
    final /* synthetic */ FeedGoogleCardsStyleBFragment b;

    FeedGoogleCardsStyleBFragment$12(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment, a aVar) {
        this.b = feedGoogleCardsStyleBFragment;
        this.a = aVar;
    }

    public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
        FeedGoogleCardsStyleBFragment.access$100(this.b).setVisibility(0);
        return true;
    }

    public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
        if (!(bVar instanceof com.bumptech.glide.load.resource.c.b)) {
            return false;
        }
        FeedGoogleCardsStyleBFragment.access$1802(this.b, (com.bumptech.glide.load.resource.c.b) bVar);
        FeedGoogleCardsStyleBFragment.access$100(this.b).setVisibility(0);
        switch (this.a.t()) {
            case 0:
                if (a.b(this.b.getApplicationContext(), "ADV_SHAKE_DATE") || a.e(this.b.getApplicationContext(), "ADV_CLICK_FLAG")) {
                    FeedGoogleCardsStyleBFragment.access$000(this.b);
                    return true;
                }
                FeedGoogleCardsStyleBFragment.access$1900(this.b, this.a.g());
                a.a(this.b.getApplicationContext(), "ADV_SHAKE_DATE");
                a.a(this.b.getApplicationContext(), "ADV_SHAKE_FLAG", false);
                return true;
            case 1:
                if (a.e(this.b.getApplicationContext(), "ADV_SHAKE_FLAG") || a.e(this.b.getApplicationContext(), "ADV_CLICK_FLAG")) {
                    FeedGoogleCardsStyleBFragment.access$000(this.b);
                    return true;
                }
                FeedGoogleCardsStyleBFragment.access$1900(this.b, this.a.g());
                a.a(this.b.getApplicationContext(), "ADV_SHAKE_DATE");
                a.a(this.b.getApplicationContext(), "ADV_SHAKE_FLAG", true);
                return true;
            default:
                FeedGoogleCardsStyleBFragment.access$000(this.b);
                return true;
        }
    }
}
