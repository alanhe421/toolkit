package com.tencent.android.tpush.common;

import android.content.Context;
import com.tencent.android.tpush.c.a;

/* compiled from: ProGuard */
public class o {
    private static volatile o a = null;
    private boolean b;
    private boolean c;

    private o(Context context) {
        this.b = false;
        this.c = false;
        this.b = j.a();
        this.c = a.a();
    }

    public static o a(Context context) {
        if (a == null) {
            synchronized (o.class) {
                if (a == null) {
                    a = new o(context);
                }
            }
        }
        return a;
    }

    public boolean a() {
        return this.b;
    }

    public boolean b() {
        return this.c;
    }
}
