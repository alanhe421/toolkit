package com.tencent.android.tpush.d;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import com.pay.http.APPluginErrorCode;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.d.f;
import com.tencent.android.tpush.stat.a.h;
import com.tencent.qalsdk.sdk.v;
import java.util.Locale;

/* compiled from: ProGuard */
public class a {
    private String a;
    private String b = "Axg%lu";
    private int c = 0;
    private String d = "%d";
    private String e;
    private String f;
    private int g;
    private int h;
    private int i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;

    public a(Context context) {
        this.a = f.d(context);
        this.e = h.f(context);
        this.f = CacheManager.getToken(context);
        this.g = APPluginErrorCode.ERROR_APP_SYSTEM;
        this.h = 0;
        this.i = f.c();
        DisplayMetrics c = h.c(context);
        this.j = c.widthPixels + v.n + c.heightPixels;
        this.k = Build.MODEL;
        this.l = Locale.getDefault().getLanguage();
        this.m = "2.47";
        this.n = Build.MANUFACTURER;
        this.o = "%s";
    }

    public String a() {
        StringBuffer stringBuffer = new StringBuffer("[{");
        stringBuffer.append("\"idx\":").append(this.c);
        stringBuffer.append(",\"ts\":").append(this.d);
        stringBuffer.append(",\"et\":").append(this.g);
        stringBuffer.append(",\"si\":").append(this.h);
        if (this.a != null) {
            stringBuffer.append(",\"ui\":\"").append(this.a).append("\"");
        }
        if (this.b != null) {
            stringBuffer.append(",\"ky\":\"").append(this.b).append("\"");
        }
        if (this.f != null) {
            stringBuffer.append(",\"mid\":\"").append(this.f).append("\"");
        }
        if (this.e != null) {
            stringBuffer.append(",\"mc\":\"").append(this.e).append("\"");
        }
        stringBuffer.append(",\"ev\":{");
        stringBuffer.append("\"ov\":\"").append(this.i).append("\"");
        if (this.j != null) {
            stringBuffer.append(",\"sr\":\"").append(this.j).append("\"");
        }
        if (this.k != null) {
            stringBuffer.append(",\"md\":\"").append(this.k).append("\"");
        }
        if (this.l != null) {
            stringBuffer.append(",\"lg\":\"").append(this.l).append("\"");
        }
        if (this.m != null) {
            stringBuffer.append(",\"sv\":\"").append(this.m).append("\"");
        }
        if (this.n != null) {
            stringBuffer.append(",\"mf\":\"").append(this.n).append("\"");
        }
        if (this.o != null) {
            stringBuffer.append(",\"apn\":\"").append(this.o).append("\"");
        }
        stringBuffer.append("}}]");
        return stringBuffer.toString();
    }
}
