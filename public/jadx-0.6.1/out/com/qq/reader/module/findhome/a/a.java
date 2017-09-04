package com.qq.reader.module.findhome.a;

import android.text.TextUtils;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.findhome.b.d;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: FindHomeExpandAudioBookItem */
public class a extends com.qq.reader.module.findhome.base.a {
    private String a;

    public boolean a(JSONObject jSONObject) {
        try {
            b(jSONObject.optString("title"));
            c(jSONObject.optString(SocialConstants.PARAM_APP_DESC));
            String optString = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
            if (TextUtils.isEmpty(optString)) {
                optString = ao.a(jSONObject.optLong("id"), true, 300);
            }
            a(optString);
            d(jSONObject.optString("qurl"));
            e(jSONObject.optString("playtimes"));
            a(jSONObject.optInt("type"));
            b(jSONObject.optBoolean("largeMode"));
            if (TextUtils.isEmpty(b()) || TextUtils.isEmpty(c()) || TextUtils.isEmpty(a())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            c.e("FindHomeExpandAudioBookItem", e.getMessage());
            return false;
        }
    }

    public String i() {
        return this.a;
    }

    public void e(String str) {
        this.a = str;
    }

    public int a(d dVar) {
        return dVar.a(this);
    }
}
