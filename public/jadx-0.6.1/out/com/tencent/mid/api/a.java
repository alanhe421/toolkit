package com.tencent.mid.api;

import android.content.Context;
import android.content.SharedPreferences;

public class a {
    private static a a = null;
    private Context b = null;
    private SharedPreferences c = null;
    private String d = "__QQ_MID_STR__";

    private a(Context context) {
        this.b = context.getApplicationContext();
        this.c = this.b.getSharedPreferences(this.b.getPackageName() + ".mid.world.ro", 1);
    }

    public static a a(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    public SharedPreferences a() {
        return this.c;
    }

    public void a(String str) {
        if (str == null || !str.equals(b())) {
            this.c.edit().putString(this.d, str).commit();
        }
    }

    public String b() {
        return this.c.getString(this.d, null);
    }
}
