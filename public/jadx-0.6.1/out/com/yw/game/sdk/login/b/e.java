package com.yw.game.sdk.login.b;

import android.text.TextUtils;
import com.yuewen.ywlogin.b;
import com.yw.game.sdk.login.BaseGameLoginActivity;
import com.yw.game.sdk.login.util.c;
import org.json.JSONObject;

/* compiled from: YWLoginWraper */
class e {

    /* compiled from: YWLoginWraper */
    private static class a {
        private static final e a = new e();
    }

    e() {
    }

    public static e a() {
        return a.a;
    }

    void a(BaseGameLoginActivity baseGameLoginActivity, String str, String str2, String str3, int i, int i2, String str4) {
        final BaseGameLoginActivity baseGameLoginActivity2 = baseGameLoginActivity;
        final String str5 = str;
        final int i3 = i2;
        final String str6 = str4;
        b.a(str3, str2, i, new com.yuewen.ywlogin.b.b(this) {
            final /* synthetic */ e e;

            public void a(JSONObject jSONObject) {
                c.a("qqNativeLoginBySdk：onSuccess:");
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                Object optString = optJSONObject.optString("ywGuid");
                Object optString2 = optJSONObject.optString("ywKey");
                if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                    d.a().a(baseGameLoginActivity2, null);
                } else {
                    d.a().a(baseGameLoginActivity2, str5, 1, optString, optString2, i3, str6);
                }
            }

            public void a(int i, String str) {
                c.a("qqNativeLoginBySdk：onError:" + i + " msg:" + str);
                d.a().a(baseGameLoginActivity2, null);
            }

            public void a(String str, String str2) {
                c.a("qqNativeLoginBySdk：onVerifyCodeLogin:" + str + " s:" + str2);
                d.a().a(baseGameLoginActivity2, null);
            }
        });
    }

    void a(BaseGameLoginActivity baseGameLoginActivity, String str, String str2, String str3, int i, String str4) {
        final BaseGameLoginActivity baseGameLoginActivity2 = baseGameLoginActivity;
        final String str5 = str;
        final int i2 = i;
        final String str6 = str4;
        b.b(str2, str3, new com.yuewen.ywlogin.b.b(this) {
            final /* synthetic */ e e;

            public void a(JSONObject jSONObject) {
                c.a("weixinConnectionLoginBySdk:onSuccess:");
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                Object optString = optJSONObject.optString("ywGuid");
                Object optString2 = optJSONObject.optString("ywKey");
                if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                    d.a().a(baseGameLoginActivity2, null);
                } else {
                    d.a().a(baseGameLoginActivity2, str5, 2, optString, optString2, i2, str6);
                }
            }

            public void a(int i, String str) {
                c.a("weixinConnectionLoginBySdk:onError:" + i + " msg:" + str);
                d.a().a(baseGameLoginActivity2, null);
            }

            public void a(String str, String str2) {
                c.a("weixinConnectionLoginBySdk:onVerifyCodeLogin:" + str + " s:" + str2);
                d.a().a(baseGameLoginActivity2, null);
            }
        });
    }
}
