package com.qq.reader.module.comic.inject;

import android.app.Activity;
import android.content.Context;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.login.c;
import com.qq.reader.common.utils.o;
import com.qrcomic.c.b;

/* compiled from: ComicAccountImpl */
public class a implements b {
    public void a(Activity activity) {
        o.a(activity, null);
    }

    public boolean a(Context context) {
        return c.b();
    }

    public String b(Context context) {
        if (c.b()) {
            return c.c().c();
        }
        return "";
    }

    public boolean a() {
        return d.n;
    }

    public void a(Context context, boolean z) {
        d.n = z;
        d.i(context, z);
    }
}
