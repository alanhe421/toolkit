package com.tencent;

final class gb implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ aa c;

    gb(aa aaVar, int i, String str) {
        this.c = aaVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a(this.a, this.b);
    }
}
