package com.liveshow;

import android.app.Activity;
import android.content.Context;
import com.liveshow.b.d;
import com.qq.reader.liveshow.b.b;
import com.qq.reader.liveshow.b.e;
import com.qq.reader.liveshow.b.g;
import com.qq.reader.liveshow.b.k;
import com.qq.reader.liveshow.b.n;

/* compiled from: LiveShowHelper */
public class a {
    private static a e;
    private e a = new com.liveshow.b.e();
    private b b = new com.liveshow.b.a();
    private g c = new com.liveshow.b.g();
    private k d = new d();

    public static a a() {
        if (e == null) {
            synchronized (a.class) {
                if (e == null) {
                    e = new a();
                }
            }
        }
        return e;
    }

    private a() {
    }

    public void a(Context context) {
        n.a().a(this.b, this.a, this.c, this.d, context.getApplicationContext());
    }

    public void a(Activity activity, int i) {
        if (activity != null) {
            n.a().a(activity, i);
        }
    }
}
