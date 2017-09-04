package com.qq.reader.module.findhome.a;

import android.text.TextUtils;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.findhome.base.a;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: FindHomeExpandLiveItem */
public class d extends a {
    private String a;
    private String b;
    private int c;

    public boolean a(JSONObject jSONObject) {
        try {
            b(jSONObject.optString("title"));
            c(jSONObject.optString(SocialConstants.PARAM_APP_DESC));
            a(jSONObject.optString(SocialConstants.PARAM_IMG_URL));
            d(jSONObject.optString("qurl"));
            e(jSONObject.optString("playtimes"));
            f(jSONObject.optString("videolength"));
            c(jSONObject.optInt("livestatus"));
            a(jSONObject.optInt("type"));
            b(jSONObject.optBoolean("largeMode"));
            if (TextUtils.isEmpty(b()) || TextUtils.isEmpty(c()) || TextUtils.isEmpty(a())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            c.e("FindHomeExpandLiveItem", e.getMessage());
            return false;
        }
    }

    public String i() {
        return this.a;
    }

    public void e(String str) {
        this.a = str;
    }

    public String j() {
        return this.b;
    }

    public void f(String str) {
        this.b = str;
    }

    public int k() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public int a(com.qq.reader.module.findhome.b.d dVar) {
        return dVar.a(this);
    }
}
