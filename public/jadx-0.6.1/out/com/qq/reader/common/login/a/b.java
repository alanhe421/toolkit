package com.qq.reader.common.login.a;

import android.content.Context;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.login.b.c;

/* compiled from: OtherLoginHelper */
public class b extends a {
    private static volatile b e;

    public b(Context context) {
        this.a = context.getApplicationContext();
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (e == null) {
                synchronized (b.class) {
                    if (e == null) {
                        e = new b(context);
                    }
                }
            }
            bVar = e;
        }
        return bVar;
    }

    public boolean b() {
        return false;
    }

    public a e() {
        if (d == null) {
            d = new c();
        }
        return d;
    }

    public void g() {
    }
}
