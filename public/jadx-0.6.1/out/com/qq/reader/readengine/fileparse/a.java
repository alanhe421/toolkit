package com.qq.reader.readengine.fileparse;

/* compiled from: BookTxtBuff */
public class a extends c {
    public /* synthetic */ c c() {
        return a();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    public a() {
        this.c = new byte[a];
    }

    public boolean a(long j) {
        return this.d + ((long) this.e) >= j - 1;
    }

    public a a() {
        a aVar = new a();
        aVar.c = this.c;
        aVar.e = this.e;
        aVar.d = this.d;
        aVar.g = this.g;
        aVar.f = this.f;
        aVar.m = this.m;
        aVar.b = this.b;
        aVar.n = this.n;
        return aVar;
    }

    public int b() {
        if (k() <= 1) {
            return 0;
        }
        j().remove(k() - 1);
        return 1;
    }

    public void a(d dVar, boolean z, int i) {
    }
}
