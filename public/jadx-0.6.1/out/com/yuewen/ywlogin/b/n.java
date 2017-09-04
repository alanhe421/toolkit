package com.yuewen.ywlogin.b;

import android.content.ContentValues;
import android.os.Handler;
import android.util.Log;
import com.yuewen.ywlogin.a.g;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class n implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ Handler e;
    final /* synthetic */ b f;
    final /* synthetic */ i g;

    n(i iVar, String str, String str2, String str3, String str4, Handler handler, b bVar) {
        this.g = iVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = handler;
        this.f = bVar;
    }

    public void run() {
        ContentValues a = this.g.b();
        a.put("sessionkey", this.a);
        a.put("code", this.b);
        try {
            a.put("username", URLEncoder.encode(this.c, "utf-8"));
            a.put("password", URLEncoder.encode(this.d, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("YWLoginSDK", "The method of imageVerifyLogin UnsupportedEncodingException");
        }
        c.a(new g().a(com.yuewen.ywlogin.g.b(), a), this.e, this.f);
    }
}
