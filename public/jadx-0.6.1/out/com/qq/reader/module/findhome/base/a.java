package com.qq.reader.module.findhome.base;

import android.text.TextUtils;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.findhome.b.d;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: FindHomeExpandBaseItem */
public class a {
    private String a;
    private String b;
    private String c;
    private String d;
    private int e;
    private boolean f;
    private boolean g;
    private int h;

    public boolean a(JSONObject jSONObject) {
        try {
            b(jSONObject.optString("title"));
            c(jSONObject.optString(SocialConstants.PARAM_APP_DESC));
            a(jSONObject.optString(SocialConstants.PARAM_IMG_URL));
            d(jSONObject.optString("qurl"));
            a(jSONObject.optInt("type"));
            b(jSONObject.optBoolean("largeMode"));
            if (TextUtils.isEmpty(b()) || TextUtils.isEmpty(c()) || TextUtils.isEmpty(a())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            c.e("FindHomeExpandBaseItem", e.getMessage());
            return false;
        }
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public boolean e() {
        return this.f;
    }

    public int f() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public boolean g() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public int h() {
        return this.h;
    }

    public void b(int i) {
        this.h = i;
    }

    public int a(d dVar) {
        return dVar.a(this);
    }
}
