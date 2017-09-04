package com.tencent.android.tpush.stat.event;

import android.content.Context;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a extends d {
    protected b a = new b();
    private long k = -1;

    public b a() {
        return this.a;
    }

    public a(Context context, String str, JSONObject jSONObject, String str2) {
        super(context, str2);
        this.a.a = str;
        this.a.c = jSONObject;
    }

    public a(Context context, int i, String str, long j, long j2) {
        super(context, i, j);
        this.a.a = str;
        this.i = j2;
    }

    public EventType b() {
        return EventType.CUSTOM;
    }

    public boolean a(JSONObject jSONObject) {
        jSONObject.put("ei", this.a.a);
        if (this.k > 0) {
            jSONObject.put("du", this.k);
        }
        if (this.a.b == null) {
            jSONObject.put("kv", this.a.c);
        } else {
            jSONObject.put("ar", this.a.b);
        }
        return true;
    }
}
