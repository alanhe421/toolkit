package com.xiaomi.push.service;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import com.xiaomi.channel.commonutils.android.j;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.d.h;
import com.xiaomi.network.f.b;
import com.xiaomi.smack.d.d;
import java.net.URL;

class p$a implements b {
    p$a() {
    }

    public String a(String str) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("sdkver", String.valueOf(27));
        buildUpon.appendQueryParameter("osver", String.valueOf(VERSION.SDK_INT));
        buildUpon.appendQueryParameter("os", d.a(Build.MODEL + ":" + VERSION.INCREMENTAL));
        buildUpon.appendQueryParameter("mi", String.valueOf(j.c()));
        String builder = buildUpon.toString();
        c.c("fetch bucket from : " + builder);
        URL url = new URL(builder);
        int port = url.getPort() == -1 ? 80 : url.getPort();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String a = com.xiaomi.channel.commonutils.d.d.a(j.a(), url);
            h.a(url.getHost() + ":" + port, (int) (System.currentTimeMillis() - currentTimeMillis), null);
            return a;
        } catch (Exception e) {
            h.a(url.getHost() + ":" + port, -1, e);
            throw e;
        }
    }
}
