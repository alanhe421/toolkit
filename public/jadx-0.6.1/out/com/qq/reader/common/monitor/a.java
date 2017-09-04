package com.qq.reader.common.monitor;

import android.content.Context;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BookCityLog */
public class a {
    private static a a = null;
    private long b;
    private long c;
    private long d;
    private int e;
    private String f;
    private int g;
    private String h;
    private Context i;

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public void a(int i, String str, Context context) {
        this.e = i;
        this.f = str;
        this.i = context;
        this.b = System.currentTimeMillis();
        this.c = 0;
        this.d = 0;
    }

    public void a(String str) {
        a("bookcity_all_", true);
    }

    public void a(String str, int i, String str2) {
        this.f = str;
        this.g = i;
        this.h = str2;
        if (this.d == 0) {
            a("bookcity_first_", false);
        } else if (this.c == 0) {
            a("bookcity_page_", false);
        } else {
            a("bookcity_all_", false);
        }
    }

    public void a(int i, Context context) {
        if (i >= 40 && this.c == 0) {
            this.i = context;
            this.c = System.currentTimeMillis();
            a("bookcity_page_", true);
        } else if (i > 0 && this.d == 0) {
            this.i = context;
            this.d = System.currentTimeMillis();
            if (this.d - this.b > 20) {
                a("bookcity_first_", true);
            } else {
                this.d = 0;
            }
        }
    }

    private void a(String str, boolean z) {
        String str2 = str + this.e;
        long currentTimeMillis = System.currentTimeMillis() - this.b;
        Map hashMap = new HashMap();
        if (!z) {
            hashMap.put("param_FailCode", "" + this.g);
            hashMap.put(SocialConstants.PARAM_URL, this.f);
            hashMap.put("error", this.g + ":" + this.h);
        }
        i.a(str2, z, currentTimeMillis, 0, hashMap, this.i);
    }
}
