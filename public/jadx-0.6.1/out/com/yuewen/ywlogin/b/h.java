package com.yuewen.ywlogin.b;

import org.json.JSONObject;

final class h implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ JSONObject b;

    h(b bVar, JSONObject jSONObject) {
        this.a = bVar;
        this.b = jSONObject;
    }

    public void run() {
        if (this.a != null) {
            this.a.a(this.b);
        }
    }
}
