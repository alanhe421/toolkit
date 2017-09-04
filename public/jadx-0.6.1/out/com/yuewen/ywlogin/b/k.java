package com.yuewen.ywlogin.b;

import android.content.ContentValues;
import android.os.Handler;
import com.tencent.connect.common.Constants;
import com.yuewen.ywlogin.a.g;

class k implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ int c;
    final /* synthetic */ Handler d;
    final /* synthetic */ b e;
    final /* synthetic */ i f;

    k(i iVar, String str, String str2, int i, Handler handler, b bVar) {
        this.f = iVar;
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = handler;
        this.e = bVar;
    }

    public void run() {
        ContentValues a = this.f.b();
        a.put("skey", this.a);
        a.put("uin", this.b);
        a.put(Constants.PARAM_KEY_TYPE, String.valueOf(this.c));
        c.a(new g().a(com.yuewen.ywlogin.g.c(), a), this.d, this.e);
    }
}
