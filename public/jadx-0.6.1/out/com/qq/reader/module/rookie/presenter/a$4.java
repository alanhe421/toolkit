package com.qq.reader.module.rookie.presenter;

import android.os.Handler.Callback;
import android.os.Message;
import com.qq.reader.appconfig.b;

/* compiled from: RookieGiftHelper */
class a$4 implements Callback {
    final /* synthetic */ a a;

    a$4(a aVar) {
        this.a = aVar;
    }

    public boolean handleMessage(Message message) {
        if (message.what == a.a(this.a) && b.i) {
            a.b(this.a);
            a.d(this.a).sendEmptyMessageDelayed(a.a(this.a), (long) a.c(this.a));
        }
        return false;
    }
}
