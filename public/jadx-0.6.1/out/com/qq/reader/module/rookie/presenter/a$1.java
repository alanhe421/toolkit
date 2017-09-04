package com.qq.reader.module.rookie.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;

/* compiled from: RookieGiftHelper */
class a$1 extends BroadcastReceiver {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (a.cI.equals(intent.getAction())) {
            int bF = d.bF(ReaderApplication.getApplicationImp());
            if (bF >= 1 && bF <= 60) {
                this.a.b();
            }
        }
    }
}
