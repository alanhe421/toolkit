package com.qq.reader.module.feed.activity;

import android.os.Message;
import android.view.View;
import com.qq.reader.common.monitor.i;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.module.bookstore.qnative.c.c;

class FeedGoogleCardsFragment$21 extends c {
    final /* synthetic */ a a;
    final /* synthetic */ FeedGoogleCardsFragment b;

    FeedGoogleCardsFragment$21(FeedGoogleCardsFragment feedGoogleCardsFragment, a aVar) {
        this.b = feedGoogleCardsFragment;
        this.a = aVar;
    }

    public void a(View view) {
        i.a("event_F99", null, this.b.getApplicationContext());
        if (com.qq.reader.common.login.c.b() || !this.a.p()) {
            FeedGoogleCardsFragment.access$2800(this.b, this.a);
            return;
        }
        AnonymousClass1 anonymousClass1 = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ FeedGoogleCardsFragment$21 a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        FeedGoogleCardsFragment.access$2800(this.a.b, this.a.a);
                        return;
                    default:
                        return;
                }
            }
        };
        Message obtainMessage = FeedGoogleCardsFragment.access$2900(this.b).obtainMessage();
        obtainMessage.obj = anonymousClass1;
        obtainMessage.what = 65542;
        FeedGoogleCardsFragment.access$3000(this.b).sendMessage(obtainMessage);
    }
}
