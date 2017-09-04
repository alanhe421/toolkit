package com.sijla.d;

import android.content.Context;
import com.sijla.j.b;
import com.sijla.j.f;
import com.sijla.j.g.a;
import org.json.JSONArray;
import org.json.JSONObject;

class a$1 implements a {
    final /* synthetic */ Context a;

    a$1(Context context) {
        this.a = context;
    }

    public void a(String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            f.a("url = [" + str + " success]");
            JSONArray optJSONArray = jSONObject.optJSONArray("lgarray");
            f.a("lgarray = " + optJSONArray);
            String d = b.d(this.a);
            f.a("lg2ConfigPath = " + d);
            f.a("writesuccess = " + com.sijla.j.a.b.a(optJSONArray.toString(), d, false));
        }
    }

    public void a(String str) {
    }
}
