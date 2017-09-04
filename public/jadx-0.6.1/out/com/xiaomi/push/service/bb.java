package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class bb {
    private static volatile bb a;
    private SharedPreferences b;

    private bb(Context context) {
        this.b = context.getSharedPreferences("mipush", 0);
    }

    public static bb a(Context context) {
        if (a == null) {
            synchronized (bb.class) {
                if (a == null) {
                    a = new bb(context);
                }
            }
        }
        return a;
    }

    public synchronized void a() {
        Editor edit = this.b.edit();
        edit.remove("miid");
        edit.commit();
    }

    public synchronized void a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        Editor edit = this.b.edit();
        edit.putString("miid", str);
        edit.commit();
    }

    public synchronized String b() {
        return this.b.getString("miid", "0");
    }

    public synchronized boolean c() {
        return !TextUtils.equals("0", b());
    }
}
