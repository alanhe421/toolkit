package com.yuewen.ywlogin.a;

class f implements Runnable {
    final /* synthetic */ j a;
    final /* synthetic */ d b;

    f(d dVar, j jVar) {
        this.b = dVar;
        this.a = jVar;
    }

    public void run() {
        if (this.b.b != null) {
            if (this.a.a()) {
                this.b.b.c(this.a);
            } else {
                this.b.b.a(this.a);
            }
        }
        h.a(this.b);
    }
}
