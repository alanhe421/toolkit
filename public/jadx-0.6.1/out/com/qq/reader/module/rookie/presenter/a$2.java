package com.qq.reader.module.rookie.presenter;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.view.af;

/* compiled from: RookieGiftHelper */
class a$2 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    a$2(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public void run() {
        if (!TextUtils.isEmpty(this.a)) {
            af.a(ReaderApplication.getApplicationImp().getApplicationContext(), this.a, 0).a();
        }
    }
}
