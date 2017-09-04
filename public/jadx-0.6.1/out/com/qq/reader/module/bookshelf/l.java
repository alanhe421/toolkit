package com.qq.reader.module.bookshelf;

import android.text.TextUtils;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.debug.c;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: UserReadTimeData */
public class l {
    private static long g = 1800000;
    private boolean a;
    private int b;
    private long c;
    private long d;
    private long[] e = new long[]{g, g, g};
    private int f;
    private long h = (g * 3);

    public int a() {
        return (((int) (this.c + a.W)) / 1000) / 60;
    }

    private long f() {
        return this.c + a.W;
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!"0".equals(jSONObject.optString("code"))) {
                return false;
            }
            int length;
            this.a = jSONObject.optBoolean("hasFinishExchange");
            this.b = jSONObject.optInt("rankRatio", 0);
            this.c = jSONObject.optLong("weekReadTime", 0);
            this.d = jSONObject.optLong("exchangedTime", 0);
            JSONArray optJSONArray = jSONObject.optJSONArray("exchangeTimeList");
            if (optJSONArray != null) {
                length = optJSONArray.length();
            } else {
                length = 0;
            }
            if (length > 0) {
                this.e = new long[length];
                this.h = 0;
                for (int i = 0; i < length; i++) {
                    this.e[i] = optJSONArray.optLong(i, g);
                    this.h += this.e[i];
                }
            } else {
                this.h = g * 3;
            }
            this.f = jSONObject.optInt("exchangedNum", 0);
            return true;
        } catch (Exception e) {
            c.e("UserReadTimeData", e.getMessage());
            return false;
        }
    }

    public int b() {
        return this.b;
    }

    public boolean c() {
        return this.d < this.h;
    }

    public int[] d() {
        int[] iArr = new int[]{(int) ((g / 1000) / 60), 0};
        int length = this.e.length;
        long f = f();
        long j = 0;
        int i = 0;
        while (i < length) {
            j += this.e[i];
            if (f <= j) {
                iArr[0] = (int) ((this.e[i] / 1000) / 60);
                iArr[1] = (int) ((f / 1000) / 60);
                break;
            } else if (i == length - 1) {
                iArr[0] = (int) ((this.e[i] / 1000) / 60);
                iArr[1] = iArr[0];
                break;
            } else if (f < this.e[i + 1] + j) {
                iArr[0] = (int) ((this.e[i + 1] / 1000) / 60);
                iArr[1] = (int) (((f - j) / 1000) / 60);
                break;
            } else {
                i++;
            }
        }
        return iArr;
    }

    public boolean e() {
        if (this.f >= this.e.length) {
            return false;
        }
        try {
            if (f() - this.d >= this.e[this.f]) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
