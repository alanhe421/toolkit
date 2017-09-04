package com.tencent.android.tpush.service.channel;

/* compiled from: ProGuard */
class h implements Runnable {
    final /* synthetic */ o a;
    final /* synthetic */ b b;

    h(b bVar, o oVar) {
        this.b = bVar;
        this.a = oVar;
    }

    public void run() {
        this.a.d.a(this.a.c, new a());
    }
}
