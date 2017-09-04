package com.tencent.android.tpush.b;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.p;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class e {
    public int a = 1;
    public String b = "";
    public f c = new f();
    public String d = "";
    public String e = "";
    public String f = "";
    public int g = 0;
    public String h = "";
    public String i = "";
    public String j = "";

    private void a(String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject(str);
        if (!jSONObject2.isNull(Constants.FLAG_ACTION_TYPE)) {
            this.a = jSONObject2.getInt(Constants.FLAG_ACTION_TYPE);
        }
        if (!jSONObject2.isNull(Constants.FLAG_ACTIVITY_NAME)) {
            this.b = jSONObject2.getString(Constants.FLAG_ACTIVITY_NAME);
        }
        if (!jSONObject2.isNull("aty_attr")) {
            String optString = jSONObject2.optString("aty_attr");
            if (!p.b(optString)) {
                try {
                    JSONObject jSONObject3 = new JSONObject(optString);
                    this.c.a = jSONObject3.optInt("if");
                    this.c.b = jSONObject3.optInt(com.tencent.connect.common.Constants.PARAM_PLATFORM_ID);
                } catch (Throwable e) {
                    a.c(Constants.LogTag, "decode activityAttribute error", e);
                }
            }
        }
        if (!jSONObject2.isNull("intent")) {
            this.d = jSONObject2.getString("intent");
        }
        if (!jSONObject2.isNull("browser")) {
            this.e = jSONObject2.getString("browser");
            jSONObject = new JSONObject(this.e);
            if (!jSONObject.isNull(SocialConstants.PARAM_URL)) {
                this.f = jSONObject.getString(SocialConstants.PARAM_URL);
            }
            if (!jSONObject.isNull("confirm")) {
                this.g = jSONObject.getInt("confirm");
            }
        }
        if (!jSONObject2.isNull("package_name")) {
            this.i = jSONObject2.getString("package_name");
            jSONObject = new JSONObject(this.i);
            if (!jSONObject.isNull(Constants.FLAG_PACKAGE_DOWNLOAD_URL)) {
                this.j = jSONObject.getString(Constants.FLAG_PACKAGE_DOWNLOAD_URL);
            }
            if (!jSONObject.isNull(Constants.FLAG_PACKAGE_NAME)) {
                this.h = jSONObject.getString(Constants.FLAG_PACKAGE_NAME);
            }
            if (!jSONObject.isNull("confirm")) {
                this.g = jSONObject.getInt("confirm");
            }
        }
    }
}
