package com.sijla.d;

import android.content.Context;
import android.os.Build.VERSION;
import com.sijla.j.a.a;
import com.sijla.j.f;
import com.sijla.j.g;
import com.sijla.j.i;
import com.sijla.j.j;
import java.io.File;

public class b {
    private String a;
    private String b;
    private Context c;

    public b(Context context, String str, String str2) {
        this.c = context;
        String str3 = (String) j.b(context, "cfgver", "");
        String packageName = context.getPackageName();
        String b = i.b(context);
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.append("?app=").append(packageName);
        stringBuilder.append("&uid=").append(b);
        stringBuilder.append("&appver=").append(a.f(context));
        stringBuilder.append("&osver=").append(VERSION.SDK_INT);
        stringBuilder.append("&cver=").append(str3);
        this.a = stringBuilder.toString();
        this.b = str2;
    }

    public File a() {
        if (!a.b(this.c)) {
            return null;
        }
        File a = g.a(this.a, this.b);
        Object obj = (a != null && a.exists() && a.isFile()) ? 1 : null;
        if (obj != null) {
            f.a("config file down" + (obj != null ? " success !!" : " fail !!"));
        }
        return a;
    }
}
