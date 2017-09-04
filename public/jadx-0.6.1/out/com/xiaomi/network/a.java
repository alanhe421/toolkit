package com.xiaomi.network;

import com.tencent.mid.api.MidEntity;
import org.json.JSONObject;

public class a {
    private int a;
    private long b;
    private long c;
    private String d;
    private long e;

    public a() {
        this(0, 0, 0, null);
    }

    public a(int i, long j, long j2, Exception exception) {
        this.a = i;
        this.b = j;
        this.e = j2;
        this.c = System.currentTimeMillis();
        if (exception != null) {
            this.d = exception.getClass().getSimpleName();
        }
    }

    public int a() {
        return this.a;
    }

    public a a(JSONObject jSONObject) {
        this.b = jSONObject.getLong("cost");
        this.e = jSONObject.getLong("size");
        this.c = jSONObject.getLong(MidEntity.TAG_TIMESTAMPS);
        this.a = jSONObject.getInt("wt");
        this.d = jSONObject.optString("expt");
        return this;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cost", this.b);
        jSONObject.put("size", this.e);
        jSONObject.put(MidEntity.TAG_TIMESTAMPS, this.c);
        jSONObject.put("wt", this.a);
        jSONObject.put("expt", this.d);
        return jSONObject;
    }
}
