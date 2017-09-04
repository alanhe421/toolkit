package com.qq.reader.module.bookstore.qnative.item;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.c.a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: RankItem */
public class ae extends s {
    c a = null;
    String[] b = new String[]{"event_C133", "event_C134", "event_C135", "event_C136", "event_C146", "event_C137", "event_C138", "event_C139"};
    private String c;
    private String d;
    private long e;
    private long f;
    private String g;
    private String h;
    private int i;

    public void a(int i) {
        this.i = i;
    }

    public void parseData(JSONObject jSONObject) {
        this.c = jSONObject.optString("title");
        this.e = jSONObject.optLong("actionId");
        this.h = jSONObject.optString("actionTag");
        this.d = jSONObject.optString("intro");
        this.g = jSONObject.optString("rId");
        this.f = jSONObject.optLong("bid");
    }

    public String a() {
        return this.c;
    }

    public long b() {
        return this.e;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.g;
    }

    public String e() {
        return this.h;
    }

    public boolean a(a aVar) {
        o.b(aVar.getFromActivity(), this.c, String.valueOf(this.e), this.h, null);
        Map hashMap = new HashMap();
        hashMap.put("rankboard", "abtest_A");
        hashMap.put("actionId", String.valueOf(this.e));
        hashMap.put("pre", String.valueOf(d.aS(aVar.getFromActivity())));
        i.a("event_B246", hashMap, aVar.getFromActivity());
        return true;
    }

    public long f() {
        return this.f;
    }
}
