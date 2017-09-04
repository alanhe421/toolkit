package com.tencent.mid.a;

import android.content.Context;
import com.tencent.mid.api.MidConstants;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.b.g;
import com.tencent.mid.util.Util;
import com.tencent.mid.util.c;
import org.json.JSONObject;

public class f extends e {
    protected f(Context context) {
        super(context);
    }

    private String a(String str) {
        return !Util.isEmpty(str) ? str : "-";
    }

    protected int a() {
        return 2;
    }

    protected void b(JSONObject jSONObject) {
        jSONObject.put("mid", "0");
        jSONObject.put(MidEntity.TAG_IMEI, a(Util.getImei(this.a)));
        jSONObject.put(MidEntity.TAG_IMSI, a(Util.getImsi(this.a)));
        jSONObject.put(MidEntity.TAG_MAC, a(Util.getWifiMacAddress(this.a)));
        jSONObject.put(MidEntity.TAG_TIMESTAMPS, System.currentTimeMillis() / 1000);
        MidEntity a = g.a(this.a);
        if (a != null && a.isMidValid()) {
            jSONObject.put("mid", a.getMid());
        }
        String b = g.a(this.a).b();
        if (Util.isMidValid(b)) {
            jSONObject.put(MidConstants.NEW_MID_TAG, b);
        } else {
            jSONObject.put(MidConstants.NEW_MID_TAG, "0");
        }
        try {
            new c(this.a).a(jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
