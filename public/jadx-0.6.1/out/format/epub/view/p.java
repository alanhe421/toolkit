package format.epub.view;

import android.text.TextUtils;
import format.epub.common.image.c;

/* compiled from: ZLTextImageElement */
public final class p extends g {
    public final String a;
    public final c b;
    public final String h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public String l = "";
    public int m;
    public String n;
    public String o;
    public int p;
    public byte q;
    public int r;
    public int s;
    public int t;
    private boolean u;
    private String v;

    p(String str, c cVar, String str2, boolean z, String str3, boolean z2, boolean z3) {
        this.a = str;
        this.b = cVar;
        this.h = str2;
        this.i = z;
        this.l = str3;
        this.j = z2;
        this.k = z3;
    }

    public boolean a() {
        return this.u;
    }

    public void a(boolean z) {
        this.u = z;
    }

    public void a(int i) {
        this.p = i;
    }

    public boolean b() {
        return (this.p & 8) == 8;
    }

    public boolean c() {
        return (this.p & 2) == 2;
    }

    public boolean d() {
        return (this.p & 1) == 1;
    }

    public byte e() {
        return this.q;
    }

    public void a(byte b) {
        this.q = b;
    }

    public int f() {
        return this.t;
    }

    public void b(int i) {
        this.t = i;
    }

    public String g() {
        return this.v;
    }

    public void a(String str) {
        this.v = str;
    }

    public boolean h() {
        return !TextUtils.isEmpty(this.v);
    }
}
