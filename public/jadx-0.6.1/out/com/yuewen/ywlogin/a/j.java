package com.yuewen.ywlogin.a;

import android.graphics.Bitmap;
import org.json.JSONObject;

public class j {
    public int a;
    public long b = -1;
    private boolean c;
    private String d;
    private int e;
    private Bitmap f;

    public j(boolean z, int i) {
        this.c = z;
        this.a = i;
    }

    public j(boolean z, int i, int i2, String str, long j) {
        this.c = z;
        this.a = i;
        this.d = str;
        this.e = i2;
        this.b = j;
    }

    public boolean a() {
        return this.c && ((this.d != null && this.d.length() > 0) || !(this.f == null || this.f.isRecycled()));
    }

    public JSONObject b() {
        try {
            return new JSONObject(this.d);
        } catch (Exception e) {
            this.c = false;
            this.a = -20006;
            return null;
        }
    }
}
