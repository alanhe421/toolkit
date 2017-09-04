package com.qq.reader.module.feed.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.feed.loader.d$b;
import java.util.ArrayList;

class FeedGoogleCardsStyleBFragment$7 implements OnClickListener {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;

    FeedGoogleCardsStyleBFragment$7(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment) {
        this.a = feedGoogleCardsStyleBFragment;
    }

    public void onClick(View view) {
        d.u(this.a.getApplicationContext(), "");
        Bundle bundle = new Bundle();
        bundle.putString("KEY_ACTION", "column");
        bundle.putString("KEY_ACTIONID", (String) FeedGoogleCardsStyleBFragment.access$1500(this.a).get(0));
        bundle.putString("KEY_JUMP_PAGENAME", "feed_column_list_b");
        bundle.putString("LOCAL_STORE_IN_TITLE", ((d$b) ((ArrayList) FeedGoogleCardsStyleBFragment.access$1600(this.a).get(0)).get(0)).c);
        bundle.putInt("function_type", 0);
        new c(bundle).a(this.a);
    }
}
