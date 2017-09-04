package com.xiaomi.push.service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import java.util.HashMap;
import java.util.Map;

public class x {
    private static volatile x a;
    private Context b;
    private Handler c = new Handler(Looper.getMainLooper());
    private Map<String, Map<String, String>> d = new HashMap();

    private x(Context context) {
        this.b = context;
    }

    public static x a(Context context) {
        if (a == null) {
            synchronized (x.class) {
                if (a == null) {
                    a = new x(context);
                }
            }
        }
        return a;
    }

    public synchronized boolean a(String str, String str2, boolean z) {
        if (this.d != null) {
            try {
                Map map = (Map) this.d.get(str);
                z = (map == null || TextUtils.isEmpty((CharSequence) map.get(str2))) ? this.b.getSharedPreferences(str, 0).getBoolean(str2, z) : Boolean.parseBoolean((String) map.get(str2));
            } catch (Throwable th) {
                c.a(th);
            }
        }
        return z;
    }
}
