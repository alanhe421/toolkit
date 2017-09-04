package com.tencent.android.tpush.stat.a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.stat.a;
import com.tencent.android.tpush.stat.event.d;
import com.tencent.qalsdk.sdk.v;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ProGuard */
class c {
    String a;
    String b;
    DisplayMetrics c;
    int d;
    String e;
    String f;
    String g;
    String h;
    String i;
    String j;
    int k;
    String l;
    Context m;
    long n;
    private String o;
    private String p;

    private c(Context context, long j) {
        this.b = "2.0.6";
        this.d = VERSION.SDK_INT;
        this.e = Build.MODEL;
        this.f = Build.MANUFACTURER;
        this.g = Locale.getDefault().getLanguage();
        this.k = 0;
        this.l = null;
        this.m = null;
        this.o = null;
        this.p = null;
        this.n = 0;
        this.m = context.getApplicationContext();
        this.c = e.b(this.m);
        this.a = e.b(this.m, j);
        this.h = e.c(this.m);
        this.i = TimeZone.getDefault().getID();
        this.j = e.g(this.m);
        this.l = this.m.getPackageName();
        this.o = e.i(this.m);
        this.p = e.d();
        this.n = j;
    }

    void a(JSONObject jSONObject, Thread thread) {
        if (thread == null) {
            if (this.c != null) {
                jSONObject.put("sr", this.c.widthPixels + v.n + this.c.heightPixels);
                jSONObject.put("dpi", this.c.xdpi + v.n + this.c.ydpi);
            }
            if (a.a(this.m).b()) {
                JSONObject jSONObject2 = new JSONObject();
                h.a(jSONObject2, "bs", h.h(this.m));
                h.a(jSONObject2, "ss", h.i(this.m));
                if (jSONObject2.length() > 0) {
                    h.a(jSONObject, "wf", jSONObject2.toString());
                }
            }
            JSONArray a = h.a(this.m, 10);
            if (a != null && a.length() > 0) {
                h.a(jSONObject, "wflist", a.toString());
            }
        } else {
            h.a(jSONObject, "thn", thread.getName());
            if (e.b(this.o) && this.o.split("/").length == 2) {
                h.a(jSONObject, "fram", this.o.split("/")[0]);
            }
            if (e.b(this.p) && this.p.split("/").length == 2) {
                h.a(jSONObject, "from", this.p.split("/")[0]);
            }
            jSONObject.put("ui", h.e(this.m));
            h.a(jSONObject, "mid", XGPushConfig.getToken(this.m));
        }
        h.a(jSONObject, "pcn", e.h(this.m));
        h.a(jSONObject, "osn", VERSION.RELEASE);
        h.a(jSONObject, "av", this.a);
        h.a(jSONObject, "ch", d.f);
        h.a(jSONObject, "mf", this.f);
        if (this.n > 0) {
            h.a(jSONObject, "sv", e.a(this.m, this.n));
        }
        h.a(jSONObject, "osd", Build.DISPLAY);
        h.a(jSONObject, "prod", Build.PRODUCT);
        h.a(jSONObject, "tags", Build.TAGS);
        h.a(jSONObject, "id", Build.ID);
        h.a(jSONObject, "fng", Build.FINGERPRINT);
        h.a(jSONObject, "ov", Integer.toString(this.d));
        jSONObject.put("os", 1);
        h.a(jSONObject, "op", this.h);
        h.a(jSONObject, "lg", this.g);
        h.a(jSONObject, "md", this.e);
        h.a(jSONObject, "tz", this.i);
        if (this.k != 0) {
            jSONObject.put("jb", this.k);
        }
        h.a(jSONObject, "sd", this.j);
        h.a(jSONObject, "abi", Build.CPU_ABI);
        h.a(jSONObject, "ram", this.o);
        h.a(jSONObject, "rom", this.p);
    }
}
