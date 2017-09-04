package com.xiaomi.push.a;

import android.content.SharedPreferences;
import com.tencent.android.tpush.common.Constants;
import com.tencent.util.TimeFormatterUtils;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.d.d;
import com.xiaomi.push.service.v;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class b$c extends b$b {
    String a;
    String d;
    File e;
    int f;
    boolean g;
    boolean h;
    final /* synthetic */ b i;

    b$c(b bVar, String str, String str2, File file, boolean z) {
        this.i = bVar;
        super(bVar);
        this.a = str;
        this.d = str2;
        this.e = file;
        this.h = z;
    }

    private boolean f() {
        int i;
        SharedPreferences sharedPreferences = b.a(this.i).getSharedPreferences("log.timestamp", 0);
        String string = sharedPreferences.getString("log.requst", "");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONObject jSONObject = new JSONObject(string);
            currentTimeMillis = jSONObject.getLong("time");
            i = jSONObject.getInt("times");
        } catch (JSONException e) {
            i = 0;
        }
        if (System.currentTimeMillis() - currentTimeMillis >= TimeFormatterUtils.ONE_DAY) {
            currentTimeMillis = System.currentTimeMillis();
            i = 0;
        } else if (i > 10) {
            return false;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("time", currentTimeMillis);
            jSONObject2.put("times", i + 1);
            sharedPreferences.edit().putString("log.requst", jSONObject2.toString()).commit();
        } catch (JSONException e2) {
            c.c("JSONException on put " + e2.getMessage());
        }
        return true;
    }

    public void b() {
        try {
            if (f()) {
                Map hashMap = new HashMap();
                hashMap.put("uid", v.e());
                hashMap.put(Constants.FLAG_TOKEN, this.d);
                hashMap.put("net", d.l(b.a(this.i)));
                d.a(this.a, hashMap, this.e, "file");
            }
            this.g = true;
        } catch (IOException e) {
        }
    }

    public void c() {
        if (!this.g) {
            this.f++;
            if (this.f < 3) {
                b.b(this.i).add(this);
            }
        }
        if (this.g || this.f >= 3) {
            this.e.delete();
        }
        b.a(this.i, (long) ((1 << this.f) * 1000));
    }

    public boolean d() {
        return d.f(b.a(this.i)) || (this.h && d.d(b.a(this.i)));
    }
}
