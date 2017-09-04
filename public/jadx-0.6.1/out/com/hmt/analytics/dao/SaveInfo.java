package com.hmt.analytics.dao;

import android.content.Context;
import org.json.JSONObject;

public class SaveInfo extends Thread {
    public Context a;
    public JSONObject b;
    public String c;

    public SaveInfo(Context context, JSONObject jSONObject, String str) {
        this.b = jSONObject;
        this.a = context;
        this.c = str;
    }

    public void run() {
        super.run();
        SaveInfoExec.saveExec(this.a, this.b, this.c);
    }
}
