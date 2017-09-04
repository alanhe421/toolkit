package com.qq.reader.common.monitor;

import android.text.TextUtils;
import com.qq.reader.common.monitor.a.b;
import java.util.Iterator;
import org.json.JSONObject;

/* compiled from: StatUtils */
public final class l {
    public static String a(String str) {
        return b.a(str);
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                String optString = jSONObject.optString(str2);
                if (optString != null) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append("&");
                    }
                    stringBuilder.append(str2);
                    stringBuilder.append("=");
                    stringBuilder.append(optString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
