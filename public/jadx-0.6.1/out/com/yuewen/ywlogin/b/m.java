package com.yuewen.ywlogin.b;

import android.content.ContentValues;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.util.TimeFormatterUtils;
import com.yuewen.ywlogin.a.g;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class m implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ String b;
    final /* synthetic */ Handler c;
    final /* synthetic */ b d;
    final /* synthetic */ i e;

    m(i iVar, long j, String str, Handler handler, b bVar) {
        this.e = iVar;
        this.a = j;
        this.b = str;
        this.c = handler;
        this.d = bVar;
    }

    public void run() {
        try {
            Object obj;
            String str = (String) this.e.b("YWLogin_AutoLoginSessionKey", "");
            long longValue = ((Long) this.e.b("LastAutoLoginTime", Long.valueOf(0))).longValue();
            Log.d("YWLoginSDK", "lastAutoLoginTime :" + String.valueOf(longValue) + " ;autoLoginSessionKey :" + str);
            long currentTimeMillis = System.currentTimeMillis() - longValue;
            if (longValue <= 0) {
                obj = 1;
            } else if (currentTimeMillis >= TimeFormatterUtils.ONE_DAY) {
                int i = 1;
            } else {
                obj = null;
            }
            if (TextUtils.isEmpty(str)) {
                obj = null;
            }
            if (obj != null) {
                ContentValues a = this.e.b();
                try {
                    a.put("ywguid", Long.valueOf(this.a));
                    a.put("ywkey", this.b);
                    a.put("alk", URLEncoder.encode(str, "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e("YWLoginSDK", "The method of autoCheckLoginStatus UnsupportedEncodingException");
                }
                c.a(this.a, this.b, new g().a(com.yuewen.ywlogin.g.e(), a), this.c, this.d);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
