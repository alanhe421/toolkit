package com.qq.reader.module.bookstore.qnative.b;

import android.text.TextUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: HardCoverChecker */
public class b {
    public static String[] a = new String[]{"qteb", "teb", "txt"};
    public static String[] b = new String[]{"trial", "txt"};
    private String c;
    private String d;
    private String e;
    private String f;
    private int g = 1;
    private int h;
    private String i;
    private int j = -1;

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                a(new JSONObject(str));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            Iterator keys;
            this.i = jSONObject.toString();
            int i = 0;
            for (String str : b) {
                keys = jSONObject.keys();
                while (keys.hasNext()) {
                    if (str.equalsIgnoreCase((String) keys.next())) {
                        this.c = str;
                        this.d = jSONObject.optString(str);
                        i = 1;
                        break;
                    }
                }
                if (i != 0) {
                    break;
                }
            }
            if (this.c == null) {
                this.g = -1;
            } else if ("trial".equalsIgnoreCase(this.c)) {
                this.g = 2;
            } else {
                this.g = 1;
            }
            i = 0;
            for (String str2 : a) {
                keys = jSONObject.keys();
                while (keys.hasNext()) {
                    if (str2.equalsIgnoreCase((String) keys.next())) {
                        this.e = str2;
                        this.f = jSONObject.optString(str2);
                        i = 1;
                        break;
                    }
                }
                if (i != 0) {
                    break;
                }
            }
            if (this.e == null) {
                this.h = -1;
            } else if ("qteb".equalsIgnoreCase(this.e)) {
                this.h = 1;
            } else {
                this.h = 0;
            }
            if (this.e == null) {
                return;
            }
            if ("qteb".equalsIgnoreCase(this.e) || "trial".equalsIgnoreCase(this.e) || "teb".equalsIgnoreCase(this.e)) {
                this.j = 1;
            } else {
                this.j = 0;
            }
        }
    }

    public boolean a() {
        return this.h == 1 || this.g == 2;
    }

    @Deprecated
    public boolean b() {
        return this.g != -1;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public int f() {
        return this.j;
    }

    public String g() {
        return this.f;
    }

    public int h() {
        return this.g;
    }

    public int i() {
        return this.h;
    }

    public String j() {
        return this.i;
    }
}
