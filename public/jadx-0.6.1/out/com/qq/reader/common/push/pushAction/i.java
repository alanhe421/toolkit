package com.qq.reader.common.push.pushAction;

import android.content.Context;
import com.qq.reader.common.push.a;
import org.json.JSONObject;

/* compiled from: MsgAction */
public abstract class i {
    public static String a = "MsgAction";
    String b = a.a;
    private Context c;

    public abstract void a(JSONObject jSONObject);

    public i(Context context) {
        this.c = context;
    }

    public Context a() {
        return this.c;
    }

    public void b(String str) {
        this.b = str;
    }
}
