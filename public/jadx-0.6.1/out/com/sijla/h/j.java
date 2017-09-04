package com.sijla.h;

import android.content.Context;
import android.content.Intent;
import com.sijla.common.a;
import com.sijla.j.b;
import com.sijla.j.b.c;
import org.json.JSONObject;

public class j implements a {
    private Context a;
    private JSONObject b = new JSONObject();

    public j(Context context, JSONObject jSONObject) {
        this.a = context;
        this.b = jSONObject;
    }

    public void a() {
    }

    public void b() {
        f();
    }

    public void c() {
        f();
    }

    public void d() {
        f();
    }

    public void e() {
        f();
    }

    public void a(Intent intent) {
    }

    public void run() {
        f();
    }

    private void f() {
        try {
            String optString = this.b.optString("lgurl", "");
            String optString2 = this.b.optString("lgdata", "");
            if (!(b.a(optString) || b.a(optString2))) {
                h.a(this.a);
            }
            c.a(this.a, this.b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
