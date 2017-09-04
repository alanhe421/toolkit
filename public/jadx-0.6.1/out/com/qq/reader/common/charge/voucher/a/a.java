package com.qq.reader.common.charge.voucher.a;

import com.qq.reader.common.charge.voucher.b;

/* compiled from: UserBalance */
public final class a {
    public boolean a = false;
    public int b;
    public int c;
    public int d;
    public String e = "";
    public int f;
    public b g;

    public int a() {
        return (this.c + this.d) + this.b;
    }

    public String b() {
        return b.a(this.b, this.c, this.d);
    }

    public void c() {
        this.c = 0;
        this.d = 0;
        this.b = 0;
        if (!(this.g == null || this.g.e == null)) {
            this.g.e.clear();
        }
        this.a = false;
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.b = aVar.b;
            this.d = aVar.d;
            this.c = aVar.c;
            this.e = aVar.e;
            this.f = aVar.f;
            this.g = aVar.g;
            if (aVar.g != null) {
                this.g.d = aVar.g.c - aVar.g.b;
            }
            this.a = true;
        }
    }
}
