package com.yuewen.ywlogin;

import android.view.View;
import android.view.View.OnClickListener;

class h implements OnClickListener {
    final /* synthetic */ YWBrowserActivity a;

    h(YWBrowserActivity yWBrowserActivity) {
        this.a = yWBrowserActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
