package com.qq.reader.module.feed.mypreference;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.common.c.a;

class MyReadingGeneActivity$1 extends BroadcastReceiver {
    final /* synthetic */ MyReadingGeneActivity a;

    MyReadingGeneActivity$1(MyReadingGeneActivity myReadingGeneActivity) {
        this.a = myReadingGeneActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (a.cB.equals(intent.getAction())) {
            MyReadingGeneActivity.a(this.a);
        }
    }
}
