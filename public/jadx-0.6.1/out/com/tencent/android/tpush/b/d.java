package com.tencent.android.tpush.b;

import com.tencent.android.tpush.common.MessageKey;

/* compiled from: ProGuard */
public class d extends a {
    private int d = 0;
    private int e = 1;
    private int f = 1;
    private int g = 1;
    private int h = 0;
    private int i = 0;
    private String j = "";
    private int k = 1;
    private String l = "";
    private String m = "";
    private int n = 0;
    private int o = 0;
    private e p = new e();

    public d(String str) {
        super(str);
    }

    public int c() {
        return 1;
    }

    public int h() {
        return this.d;
    }

    public int i() {
        return this.e;
    }

    public int j() {
        return this.f;
    }

    public int k() {
        return this.g;
    }

    public int l() {
        return this.h;
    }

    public e m() {
        return this.p;
    }

    public int n() {
        return this.i;
    }

    public int o() {
        return this.k;
    }

    public String p() {
        return this.l;
    }

    public String q() {
        return this.j;
    }

    public String r() {
        return this.m;
    }

    public int s() {
        return this.n;
    }

    public int t() {
        return this.o;
    }

    protected void d() {
        this.d = this.a.optInt(MessageKey.MSG_BUILDER_ID);
        this.e = this.a.optInt(MessageKey.MSG_RING, 1);
        this.l = this.a.optString(MessageKey.MSG_RING_RAW);
        this.j = this.a.optString(MessageKey.MSG_ICON_RES);
        this.m = this.a.optString(MessageKey.MSG_SMALL_ICON);
        this.k = this.a.optInt(MessageKey.MSG_LIGHTS, 1);
        this.f = this.a.optInt(MessageKey.MSG_VIBRATE, 1);
        this.i = this.a.optInt(MessageKey.MSG_ICON);
        this.n = this.a.optInt(MessageKey.MSG_ICON_TYPE, 0);
        this.h = this.a.optInt(MessageKey.MSG_NOTIFY_ID);
        this.o = this.a.optInt(MessageKey.MSG_STYLE_ID, 0);
        if (this.a.isNull(MessageKey.MSG_CLEARABLE)) {
            this.g = 1;
        } else {
            this.g = this.a.optInt(MessageKey.MSG_CLEARABLE);
        }
        if (!this.a.isNull("action")) {
            this.p.a(this.a.getString("action"));
        }
    }
}
