package com.tencent.assistant.link.sdk.b;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {
    public String a = "";
    public String b = "";
    public String c = "";
    public int d = 0;

    public a(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public static a a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            Object str = new String(bArr, "UTF-8");
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject != null) {
                    return new a(jSONObject.getString("taskPackageName"), jSONObject.getString("taskVersion"), com.tencent.assistant.link.sdk.a.a.a(jSONObject.getString("taskAction")));
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return null;
    }
}
