package com.sijla.j;

import com.sijla.j.g.a;
import org.json.JSONObject;

class g$1 implements a {
    final /* synthetic */ String a;

    g$1(String str) {
        this.a = str;
    }

    public void a(String str, JSONObject jSONObject) {
        f.a("upload lgdata(" + str + ")success res:" + jSONObject.toString());
    }

    public void a(String str) {
        f.a("upload lgdata(" + this.a + ")error res:" + str);
    }
}
