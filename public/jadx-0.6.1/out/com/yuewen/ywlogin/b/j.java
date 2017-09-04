package com.yuewen.ywlogin.b;

import android.content.ContentValues;
import android.os.Handler;
import android.util.Log;
import com.yuewen.ywlogin.a.g;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class j implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ Handler c;
    final /* synthetic */ b d;
    final /* synthetic */ i e;

    j(i iVar, String str, String str2, Handler handler, b bVar) {
        this.e = iVar;
        this.a = str;
        this.b = str2;
        this.c = handler;
        this.d = bVar;
    }

    public void run() {
        ContentValues a = this.e.b();
        try {
            a.put("username", URLEncoder.encode(this.a, "utf-8"));
            a.put("password", URLEncoder.encode(this.b, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("YWLoginSDK", "The method of pwdLogin UnsupportedEncodingException");
        }
        c.a(new g().a(com.yuewen.ywlogin.g.a(), a), this.c, this.d);
    }
}
