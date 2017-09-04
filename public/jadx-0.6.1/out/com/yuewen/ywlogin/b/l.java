package com.yuewen.ywlogin.b;

import android.content.ContentValues;
import android.os.Handler;
import com.tencent.android.tpush.common.Constants;
import com.yuewen.ywlogin.a.g;

class l implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ Handler c;
    final /* synthetic */ b d;
    final /* synthetic */ i e;

    l(i iVar, String str, String str2, Handler handler, b bVar) {
        this.e = iVar;
        this.a = str;
        this.b = str2;
        this.c = handler;
        this.d = bVar;
    }

    public void run() {
        ContentValues a = this.e.b();
        a.put(Constants.FLAG_TOKEN, this.a);
        a.put("openid", this.b);
        c.a(new g().a(com.yuewen.ywlogin.g.d(), a), this.c, this.d);
    }
}
