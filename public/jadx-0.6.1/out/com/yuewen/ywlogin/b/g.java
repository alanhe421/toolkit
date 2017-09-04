package com.yuewen.ywlogin.b;

import org.json.JSONObject;

final class g implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ JSONObject b;

    g(b bVar, JSONObject jSONObject) {
        this.a = bVar;
        this.b = jSONObject;
    }

    public void run() {
        if (this.a != null && this.b != null) {
            JSONObject optJSONObject = this.b.optJSONObject("data");
            if (optJSONObject != null) {
                this.a.a(optJSONObject.optString("sessionKey"), optJSONObject.optString("imgSrc"));
            }
        }
    }
}
