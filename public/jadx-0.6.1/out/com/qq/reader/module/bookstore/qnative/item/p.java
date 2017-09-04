package com.qq.reader.module.bookstore.qnative.item;

import android.text.TextUtils;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.open.SocialConstants;
import java.text.SimpleDateFormat;
import org.json.JSONObject;

/* compiled from: FreeItem */
public class p extends g {
    private JSONObject a;
    protected String b;
    protected String c;
    protected String d;
    protected String h;
    protected String i;
    protected String j;
    protected String k;

    public void parseData(JSONObject jSONObject) {
        super.parseData(jSONObject);
        this.a = jSONObject.optJSONObject("discount");
        if (this.a != null) {
            this.b = this.a.optString("beginTime");
            this.c = this.a.optString("endTime");
            this.d = this.a.optString(SocialConstants.PARAM_APP_DESC);
            this.h = this.a.optString("priority");
            this.i = this.a.optString("userGrade");
            this.k = this.a.optString("discount");
        }
        this.j = jSONObject.optString("showPrice");
    }

    public int b() {
        try {
            return Integer.parseInt(this.k);
        } catch (Exception e) {
            c.e("Err", e.getMessage());
            return 100;
        }
    }

    public boolean c() {
        boolean z = (this.a == null || this.k == null || this.k.trim().equalsIgnoreCase("") || this.b.equalsIgnoreCase("")) ? false : true;
        if (z) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long time = simpleDateFormat.parse(this.b).getTime();
                long time2 = simpleDateFormat.parse(this.c).getTime();
                if (currentTimeMillis > time && currentTimeMillis < time2) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public String k() {
        if (TextUtils.isEmpty(this.f)) {
            return this.j;
        }
        return this.f;
    }

    public String a() {
        if (!TextUtils.isEmpty(this.g)) {
            return this.g;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (this.k == null || this.k.trim().equalsIgnoreCase("") || this.k.equalsIgnoreCase("0")) {
            stringBuffer.append("免费");
        } else if (Integer.valueOf(this.k).intValue() < 10) {
            stringBuffer.append("0.").append(this.k).append("折");
        } else if (Integer.valueOf(this.k).intValue() != 100) {
            if (this.k.endsWith("0")) {
                stringBuffer.append(this.k.substring(0, this.k.length() - 1)).append("折");
            } else {
                stringBuffer.append(this.k).append("折");
            }
        }
        return stringBuffer.toString();
    }
}
