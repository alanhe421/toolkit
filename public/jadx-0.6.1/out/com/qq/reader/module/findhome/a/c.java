package com.qq.reader.module.findhome.a;

import android.text.TextUtils;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.findhome.b.d;
import com.qq.reader.module.findhome.base.a;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: FindHomeExpandComicItem */
public class c extends a {
    public boolean a(JSONObject jSONObject) {
        try {
            b(jSONObject.optString("title"));
            c(jSONObject.optString(SocialConstants.PARAM_APP_DESC));
            String optString = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
            if (TextUtils.isEmpty(optString)) {
                optString = ao.a(jSONObject.optLong("id"), 279, 372);
            }
            a(optString);
            d(jSONObject.optString("qurl"));
            a(jSONObject.optInt("type"));
            b(jSONObject.optBoolean("largeMode"));
            if (TextUtils.isEmpty(b()) || TextUtils.isEmpty(c()) || TextUtils.isEmpty(a())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("FindHomeExpandComicItem", e.getMessage());
            return false;
        }
    }

    public int a(d dVar) {
        return dVar.a(this);
    }
}
