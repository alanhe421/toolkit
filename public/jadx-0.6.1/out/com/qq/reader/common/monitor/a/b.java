package com.qq.reader.common.monitor.a;

import android.text.TextUtils;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import org.json.JSONObject;

/* compiled from: ChannelConfig */
public class b {
    private static a a = null;

    public static synchronized String a(String str) {
        String str2;
        synchronized (b.class) {
            if (a == null || str == null || !str.equals(a.a())) {
                str2 = "";
            } else if (TextUtils.isEmpty(str) || !str.equals(a.dg)) {
                str2 = a.b();
            } else {
                try {
                    str2 = b(a.b());
                } catch (Exception e) {
                    c.e("ChannelConfig", e.getMessage());
                }
            }
        }
        return str2;
    }

    public static synchronized void a(a aVar) {
        synchronized (b.class) {
            a = aVar;
        }
    }

    public static synchronized void a() {
        synchronized (b.class) {
            a = null;
        }
    }

    public static synchronized String b(String str) {
        synchronized (b.class) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String str2 = s.ALG;
                    String str3 = "itemid";
                    CharSequence optString = jSONObject.optString(str2);
                    CharSequence optString2 = jSONObject.optString(str3);
                    if (!TextUtils.isEmpty(optString)) {
                        jSONObject.remove(str2);
                    }
                    if (!TextUtils.isEmpty(optString2)) {
                        jSONObject.remove(str3);
                    }
                    str = jSONObject.toString();
                } catch (Exception e) {
                    c.e("ChannelConfig", e.getMessage());
                }
            }
        }
        return str;
    }
}
