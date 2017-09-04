package com.yuewen.ywlogin.b;

import org.json.JSONObject;

final class e implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ JSONObject b;

    e(b bVar, JSONObject jSONObject) {
        this.a = bVar;
        this.b = jSONObject;
    }

    public void run() {
        if (this.a != null) {
            this.a.b(this.b);
        }
    }
}
