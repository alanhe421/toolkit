package com.tencent.beacon.b;

import android.content.Context;
import com.tencent.beacon.e.b;
import com.tencent.beacon.e.c;
import com.tencent.beacon.event.UserAction;
import com.tencent.beacon.net.a;

/* compiled from: ProGuard */
public final class j {
    private static j s;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private boolean k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;

    private j(Context context) {
        String e;
        this.a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.k = false;
        this.l = "";
        this.m = "";
        this.n = "";
        this.o = "";
        this.p = "";
        this.q = "";
        this.r = "";
        this.b = d.m().i();
        f.a(context);
        this.c = f.e(context);
        this.d = f.c(context);
        this.e = f.d(context);
        if ("".equals(this.c)) {
            e = a.e("/sys/class/net/eth0/address");
            if (e.trim().equals("") || e.length() < 17) {
                e = "";
            } else {
                e = e.toLowerCase().substring(0, 17);
            }
            this.f = e;
        }
        this.g = UserAction.getQQ();
        this.h = f.b();
        this.i = f.m();
        this.j = f.a();
        this.k = c.a().b();
        f.l(context);
        e = b.a(context, "f_non_empty_qimei_v2", "f_non_empty_qimei_v3", "");
        String[] split = e.split("\\|\\|\\|");
        if (split.length == 7) {
            this.l = split[0];
            this.m = split[1];
            this.n = split[2];
            this.o = split[3];
            this.p = split[4];
            this.q = split[5];
            this.r = split[6];
        }
        if (!this.b.equals("") && this.l.trim().equals("")) {
            this.l = this.b;
        }
        if (!this.c.equals("") && this.m.trim().equals("")) {
            this.m = this.c;
        }
        if (!this.d.equals("") && this.n.trim().equals("")) {
            this.n = this.d;
        }
        if (!this.h.equals("") && this.o.trim().equals("")) {
            this.o = this.h;
        }
        if (!this.f.equals("") && this.p.trim().equals("")) {
            this.p = this.f;
        }
        if (!this.e.equals("") && this.q.trim().equals("")) {
            this.q = this.e;
        }
        if (!this.i.equals("") && this.r.trim().equals("")) {
            this.r = this.i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.l);
        stringBuilder.append("|||");
        stringBuilder.append(this.m);
        stringBuilder.append("|||");
        stringBuilder.append(this.n);
        stringBuilder.append("|||");
        stringBuilder.append(this.o);
        stringBuilder.append("|||");
        stringBuilder.append(this.p);
        stringBuilder.append("|||");
        stringBuilder.append(this.q);
        stringBuilder.append("|||");
        stringBuilder.append(this.r);
        if (!stringBuilder.toString().equals(e)) {
            b.c(context, "f_non_empty_qimei_v3", stringBuilder.toString());
        }
        try {
            e = new b(context).a();
            if (e != null && !"".equals(e)) {
                this.a = e;
            }
        } catch (Exception e2) {
        }
    }

    public static j a(Context context) {
        if (s == null) {
            s = new j(context);
        }
        return s;
    }

    public final String a() {
        if (this.a != null && !"".equals(this.a)) {
            return this.a;
        }
        if ("".equals(this.b)) {
            return this.e;
        }
        return this.b;
    }

    public final String b() {
        return this.a;
    }

    public final void a(String str) {
        this.a = str;
    }

    public final String c() {
        return this.b;
    }

    public final String d() {
        return this.c;
    }

    public final String e() {
        return this.d;
    }

    public final String f() {
        return this.e;
    }

    public final String g() {
        return this.j;
    }

    public final boolean h() {
        return this.k;
    }

    public final String i() {
        return this.l;
    }

    public final String j() {
        return this.m;
    }

    public final String k() {
        return this.n;
    }

    public final String l() {
        return this.o;
    }

    public final String m() {
        return this.p;
    }

    public final String n() {
        return this.q;
    }

    public final String o() {
        return this.r;
    }

    public final String p() {
        return this.g;
    }

    public final String q() {
        return this.h;
    }

    public final String r() {
        return this.i;
    }
}
