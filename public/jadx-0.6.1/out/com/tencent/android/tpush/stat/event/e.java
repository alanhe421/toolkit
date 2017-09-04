package com.tencent.android.tpush.stat.event;

import android.content.Context;
import com.tencent.android.tpush.stat.a.a;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class e extends d {
    private a a;
    private JSONObject k = null;

    public e(Context context, int i, JSONObject jSONObject, long j) {
        super(context, i, j);
        this.a = new a(context, j);
        this.k = jSONObject;
    }

    public EventType b() {
        return EventType.SESSION_ENV;
    }

    public boolean a(JSONObject jSONObject) {
        jSONObject.put("ut", 1);
        if (this.k != null) {
            jSONObject.put("cfg", this.k);
        }
        if (com.tencent.android.tpush.stat.a.e.j(this.j)) {
            jSONObject.put("ncts", 1);
        }
        this.a.a(jSONObject, null);
        return true;
    }
}
