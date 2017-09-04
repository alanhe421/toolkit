package com.tencent;

final class dv implements Runnable {
    private /* synthetic */ TIMUserSearchSucc a;
    private /* synthetic */ aa b;

    dv(aa aaVar, TIMUserSearchSucc tIMUserSearchSucc) {
        this.b = aaVar;
        this.a = tIMUserSearchSucc;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
