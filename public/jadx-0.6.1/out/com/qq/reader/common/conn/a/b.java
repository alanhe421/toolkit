package com.qq.reader.common.conn.a;

/* compiled from: OnlineTgwIp */
public class b {
    private String a;
    private String b;
    private String c;
    private boolean d = false;
    private boolean e = false;
    private long f = -1;
    private int g = 1800000;

    public b(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        if ("dwn.reader.qq.com".equals(this.c)) {
            this.e = true;
            this.d = false;
        } else if ("dwn.3g.qq.com".equals(this.c)) {
            this.e = false;
            this.d = true;
            this.f = System.currentTimeMillis() + ((long) this.g);
        } else {
            this.f = System.currentTimeMillis() + ((long) this.g);
        }
    }

    public String a() {
        return this.c;
    }

    public boolean b() {
        if (this.a == null || this.a.indexOf("wifi") == -1) {
            return false;
        }
        return true;
    }

    public boolean c() {
        return this.e;
    }

    public boolean d() {
        return this.d;
    }

    public boolean e() {
        if (g() || f() || h()) {
            return true;
        }
        return false;
    }

    private boolean f() {
        return this.e;
    }

    private boolean g() {
        if (!this.d || System.currentTimeMillis() >= this.f) {
            return false;
        }
        return true;
    }

    private boolean h() {
        if (this.e || this.d || System.currentTimeMillis() >= this.f) {
            return false;
        }
        return true;
    }
}
